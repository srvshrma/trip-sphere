package com.tripsphere.catalog.service;

import com.tripsphere.catalog.model.Experience;
import com.tripsphere.catalog.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
    
    @Autowired
    private ExperienceRepository experienceRepository;
    
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }
    
    @Cacheable(value = "experiences", key = "#id")
    public Optional<Experience> getExperienceById(Long id) {
        System.out.println(">>> Fetching from database - Experience ID: " + id);
        // Simulate slow database call to demonstrate caching
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return experienceRepository.findById(id);
    }
    
    @CacheEvict(value = "experiences", key = "#experience.id")
    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }
    
    @CachePut(value = "experiences", key = "#experienceId")
    public boolean updateAvailableSlots(Long experienceId, Integer slots) {
        Optional<Experience> experienceOpt = experienceRepository.findById(experienceId);
        if (experienceOpt.isPresent()) {
            Experience experience = experienceOpt.get();
            experience.setAvailableSlots(experience.getAvailableSlots() - slots);
            experienceRepository.save(experience);
            return true;
        }
        return false;
    }

    @CacheEvict(value = "experiences", key = "#id")
    public void evictExperienceFromCache(Long id) {
        System.out.println(">>> Evicted experience from cache: " + id);
    }
}