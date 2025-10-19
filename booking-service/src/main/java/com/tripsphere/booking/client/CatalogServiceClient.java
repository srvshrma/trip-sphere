// booking-service/src/main/java/com/tripsphere/booking/client/CatalogServiceClient.java
package com.tripsphere.booking.client;

import com.tripsphere.booking.model.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class CatalogServiceClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${catalog.service.url:http://localhost:8082}")
    private String catalogServiceUrl;
    
    public Optional<Experience> getExperienceById(Long experienceId) {
        try {
            String url = catalogServiceUrl + "/api/catalog/experiences/" + experienceId;
            Experience experience = restTemplate.getForObject(url, Experience.class);
            return Optional.ofNullable(experience);
        } catch (Exception e) {
            System.err.println("Error fetching experience from catalog service: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    public boolean updateExperienceSlots(Long experienceId, Integer slotsToReduce) {
        try {
            // This would call a PATCH endpoint in catalog service
            // For now, we'll just return true as we don't have that endpoint yet
            System.out.println("Would update slots for experience " + experienceId + " by " + slotsToReduce);
            return true;
        } catch (Exception e) {
            System.err.println("Error updating experience slots: " + e.getMessage());
            return false;
        }
    }
}
