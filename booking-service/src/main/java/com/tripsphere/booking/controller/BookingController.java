// booking-service/src/main/java/com/tripsphere/booking/controller/BookingController.java
package com.tripsphere.booking.controller;

import com.tripsphere.booking.dto.BookingRequest;
import com.tripsphere.booking.model.Booking;
import com.tripsphere.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.createBooking(bookingRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }
    
    @GetMapping("/experience/{experienceId}")
    public List<Booking> getBookingsByExperienceId(@PathVariable Long experienceId) {
        return bookingService.getBookingsByExperienceId(experienceId);
    }
}