// booking-service/src/main/java/com/tripsphere/booking/service/BookingService.java
package com.tripsphere.booking.service;

import com.tripsphere.booking.client.CatalogServiceClient;
import com.tripsphere.booking.dto.BookingEvent;
import com.tripsphere.booking.dto.BookingRequest;
import com.tripsphere.booking.model.Booking;
import com.tripsphere.booking.model.Experience;
import com.tripsphere.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private CatalogServiceClient catalogServiceClient;
    
    @Autowired
    private KafkaProducerService kafkaProducerService;
    
    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) {
        // Validate experience exists and has available slots
        Optional<Experience> experienceOpt = catalogServiceClient.getExperienceById(bookingRequest.getExperienceId());
        if (experienceOpt.isEmpty()) {
            throw new RuntimeException("Experience not found with ID: " + bookingRequest.getExperienceId());
        }
        
        Experience experience = experienceOpt.get();
        
        // Check available slots
        if (experience.getAvailableSlots() < bookingRequest.getNumberOfGuests()) {
            throw new RuntimeException("Not enough available slots. Available: " + experience.getAvailableSlots() + ", Requested: " + bookingRequest.getNumberOfGuests());
        }
        
        // Create booking
        Booking booking = new Booking(
            bookingRequest.getUserId(),
            bookingRequest.getExperienceId(),
            bookingRequest.getNumberOfGuests(),
            bookingRequest.getTotalPrice()
        );
        
        Booking savedBooking = bookingRepository.save(booking);
        
        // Update available slots in catalog service
        boolean slotsUpdated = catalogServiceClient.updateExperienceSlots(
            bookingRequest.getExperienceId(), 
            bookingRequest.getNumberOfGuests()
        );
        
        if (slotsUpdated) {
            // Send booking event to Kafka
            BookingEvent bookingEvent = new BookingEvent(
                "BOOKING_CREATED",
                savedBooking.getId(),
                savedBooking.getUserId(),
                savedBooking.getExperienceId(),
                savedBooking.getNumberOfGuests(),
                savedBooking.getTotalPrice()
            );
            
            kafkaProducerService.sendBookingEvent(bookingEvent);
            
            // Update booking status to CONFIRMED
            savedBooking.setStatus("CONFIRMED");
            savedBooking = bookingRepository.save(savedBooking);
        } else {
            throw new RuntimeException("Failed to update available slots. Booking rolled back.");
        }
        
        return savedBooking;
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    public List<Booking> getBookingsByExperienceId(Long experienceId) {
        return bookingRepository.findByExperienceId(experienceId);
    }
}