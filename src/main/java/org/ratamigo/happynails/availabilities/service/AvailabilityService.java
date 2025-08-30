package org.ratamigo.happynails.availabilities.service;

import java.util.List;

import org.ratamigo.happynails.availabilities.dto.AvailabilityDto;
import org.ratamigo.happynails.availabilities.dto.AvailabilityGetAllResponse;

public interface AvailabilityService {
    AvailabilityDto createAvailability(AvailabilityDto availabilityDto);
    AvailabilityGetAllResponse getAllAvailabilities(int pageNo, int pageSize);
    AvailabilityDto getAvailabilityById(int id);
    AvailabilityDto updateAvailability(AvailabilityDto availabilityDto, int id);
    void deleteAvailability(int id);
    List<AvailabilityDto> getAvailabilitiesByNailTechId(int nailTechId);
}
