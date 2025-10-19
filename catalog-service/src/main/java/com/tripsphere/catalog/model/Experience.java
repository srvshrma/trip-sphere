// catalog-service/src/main/java/com/tripsphere/catalog/model/Experience.java
package com.tripsphere.catalog.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;

@Entity
@Table(name = "experiences")
public class Experience implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private String location;
    private BigDecimal price;
    private Integer duration; // in hours
    private Integer availableSlots;
    
    // Constructors
    public Experience() {}
    
    public Experience(String title, String description, String location, BigDecimal price, Integer duration, Integer availableSlots) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.price = price;
        this.duration = duration;
        this.availableSlots = availableSlots;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    
    public Integer getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(Integer availableSlots) { this.availableSlots = availableSlots; }
}