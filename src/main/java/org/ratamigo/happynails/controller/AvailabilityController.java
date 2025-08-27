package org.ratamigo.happynails.controller;

import java.util.List;

import org.ratamigo.happynails.dto.AvailabilityDto;
import org.ratamigo.happynails.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AvailabilityController {
    private AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("availabilities")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilities() {
        return new ResponseEntity<>(availabilityService.getAllAvailabilities(), HttpStatus.OK);
    }

    @GetMapping("availabilities/{id}")
    public ResponseEntity<AvailabilityDto> availabilityDetail(
            @PathVariable("id") int availabilityId) {
        return ResponseEntity.ok(availabilityService.getAvailabilityById(availabilityId));
    }

    @PostMapping("availabilities/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AvailabilityDto> createAvailability(
            @RequestBody AvailabilityDto availabilityDto) {
        return new ResponseEntity<>(availabilityService.createAvailability(availabilityDto),
                                    HttpStatus.OK);
    }

    @PutMapping("availabilities/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AvailabilityDto> updateAvailability(
            @RequestBody AvailabilityDto availabilityDto, 
            @PathVariable("id") int availabilityId) {
        return new ResponseEntity<>(
                availabilityService.updateAvailability(availabilityDto, availabilityId),
                HttpStatus.OK);
    }

    @DeleteMapping("availabilities/{id}/delete")
    public ResponseEntity<String> deleteAvailability(@PathVariable("id") int availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
        return new ResponseEntity<>("Availability deleted", HttpStatus.OK);
    }
    
}
