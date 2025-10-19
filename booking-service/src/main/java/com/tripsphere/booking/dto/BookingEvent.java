package com.tripsphere.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingEvent {
    private String eventId;
    private String eventType; // BOOKING_CREATED, BOOKING_CONFIRMED
    private Long bookingId;
    private Long userId;
    private Long experienceId;
    private Integer numberOfGuests;
    private BigDecimal totalPrice;
    private LocalDateTime eventTimestamp;
    
    // Constructors
    public BookingEvent() {
        this.eventTimestamp = LocalDateTime.now();
    }
    
    public BookingEvent(String eventType, Long bookingId, Long userId, Long experienceId, 
                       Integer numberOfGuests, BigDecimal totalPrice) {
        this();
        this.eventType = eventType;
        this.bookingId = bookingId;
        this.userId = userId;
        this.experienceId = experienceId;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
    }
    
    // Getters and Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getExperienceId() { return experienceId; }
    public void setExperienceId(Long experienceId) { this.experienceId = experienceId; }
    
    public Integer getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(Integer numberOfGuests) { this.numberOfGuests = numberOfGuests; }
    
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    
    public LocalDateTime getEventTimestamp() { return eventTimestamp; }
    public void setEventTimestamp(LocalDateTime eventTimestamp) { this.eventTimestamp = eventTimestamp; }
}