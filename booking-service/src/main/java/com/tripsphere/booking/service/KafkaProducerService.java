package com.tripsphere.booking.service;

import com.tripsphere.booking.dto.BookingEvent;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    
    // private static final String BOOKING_TOPIC = "booking-events";
    
    // @Autowired
    // private KafkaTemplate<String, BookingEvent> kafkaTemplate;
    
    // public void sendBookingEvent(BookingEvent bookingEvent) {
    //     try {
    //         kafkaTemplate.send(BOOKING_TOPIC, bookingEvent);
    //         System.out.println("Sent booking event to Kafka: " + bookingEvent.getEventType() + " for booking " + bookingEvent.getBookingId());
    //     } catch (Exception e) {
    //         System.err.println("Error sending event to Kafka: " + e.getMessage());
    //     }
    // }

    public void sendBookingEvent(BookingEvent bookingEvent) {
        // Mock implementation - just log the event
        System.out.println("=== MOCK KAFKA EVENT ===");
        System.out.println("Event Type: " + bookingEvent.getEventType());
        System.out.println("Booking ID: " + bookingEvent.getBookingId());
        System.out.println("User ID: " + bookingEvent.getUserId());
        System.out.println("Experience ID: " + bookingEvent.getExperienceId());
        System.out.println("Number of Guests: " + bookingEvent.getNumberOfGuests());
        System.out.println("Total Price: " + bookingEvent.getTotalPrice());
        System.out.println("=== END MOCK KAFKA EVENT ===");
    }
}