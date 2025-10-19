package com.tripsphere.catalog.controller;

import com.tripsphere.catalog.model.Experience;
import com.tripsphere.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    
    @Autowired
    private CatalogService catalogService;
    
    @GetMapping("/experiences")
    public List<Experience> getAllExperiences() {
        return catalogService.getAllExperiences();
    }
    
    @GetMapping("/experiences/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) {
        Optional<Experience> experience = catalogService.getExperienceById(id);
        return experience.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/experiences")
    public Experience createExperience(@RequestBody Experience experience) {
        return catalogService.createExperience(experience);
    }
}