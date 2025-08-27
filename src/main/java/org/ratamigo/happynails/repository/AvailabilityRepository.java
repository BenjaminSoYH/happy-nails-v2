package org.ratamigo.happynails.repository;

import org.ratamigo.happynails.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer>{
    
} 
