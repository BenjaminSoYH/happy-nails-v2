package org.ratamigo.happynails.availabilities.repository;

import java.util.List;

import org.ratamigo.happynails.availabilities.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer>{
    List<Availability> findByNailTechId(int nailTechId);
} 
