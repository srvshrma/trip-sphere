// booking-service/src/main/java/com/tripsphere/booking/dto/BookingRequest.java
package com.tripsphere.booking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BookingRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Experience ID is required")
    private Long experienceId;
    
    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "Number of guests must be at least 1")
    private Integer numberOfGuests;
    
    @NotNull(message = "Total price is required")
    private BigDecimal totalPrice;
    
    // Constructors
    public BookingRequest() {}
    
    public BookingRequest(Long userId, Long experienceId, Integer numberOfGuests, BigDecimal totalPrice) {
        this.userId = userId;
        this.experienceId = experienceId;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
    }
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getExperienceId() { return experienceId; }
    public void setExperienceId(Long experienceId) { this.experienceId = experienceId; }
    
    public Integer getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(Integer numberOfGuests) { this.numberOfGuests = numberOfGuests; }
    
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}