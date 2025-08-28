package org.ratamigo.happynails.service;

import java.util.List;

import org.ratamigo.happynails.dto.AvailabilityDto;

public interface AvailabilityService {
    AvailabilityDto createAvailability(AvailabilityDto availabilityDto);
    List<AvailabilityDto> getAllAvailabilities();
    AvailabilityDto getAvailabilityById(int id);
    AvailabilityDto updateAvailability(AvailabilityDto availabilityDto, int id);
    void deleteAvailability(int id);
    List<AvailabilityDto> getAvailabilitiesByNailTechId(int nailTechId);
}
