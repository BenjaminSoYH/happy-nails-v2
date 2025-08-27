package org.ratamigo.happynails.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.ratamigo.happynails.dto.AvailabilityDto;
import org.ratamigo.happynails.exceptions.AvailabilityNotFoundException;
import org.ratamigo.happynails.exceptions.NailTechNotFoundException;
import org.ratamigo.happynails.model.Availability;
import org.ratamigo.happynails.model.NailTech;
import org.ratamigo.happynails.model.TimeSlot;
import org.ratamigo.happynails.repository.AvailabilityRepository;
import org.ratamigo.happynails.repository.NailTechRepository;
import org.ratamigo.happynails.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{
    private AvailabilityRepository availabilityRepo;
    private NailTechRepository techRepo;

    @Autowired
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepo,
                                   NailTechRepository techRepo) {
        this.availabilityRepo = availabilityRepo;
        this.techRepo = techRepo;
    }

    public AvailabilityDto mapToDto(Availability availability) {
        AvailabilityDto dto = new AvailabilityDto();
        dto.setId(availability.getId());
        if (availability.getTimeSlot() != null) {
            dto.setEndTime(availability.getTimeSlot().getEndTime());
            dto.setStartTime(availability.getTimeSlot().getStartTime());
        }
        if (availability.getNailTech() != null) {
            dto.setNailTechId(availability.getNailTech().getId());
        }
        return dto; 
    }

    public Availability mapToEntity(AvailabilityDto dto, NailTech nailTech) {
        Availability availability = new Availability();
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(dto.getStartTime());
        timeSlot.setEndTime(dto.getEndTime());
        availability.setTimeSlot(timeSlot);
        availability.setNailTech(nailTech);

        return availability;
    }

	@Override
	public AvailabilityDto createAvailability(AvailabilityDto availabilityDto) {
        NailTech nailTech = techRepo.findById(availabilityDto.getNailTechId()).orElseThrow(()
                -> new NailTechNotFoundException("Corresponding nail tech could not be found"));
		Availability availability = mapToEntity(availabilityDto, nailTech);
        Availability newAvailability = availabilityRepo.save(availability);
        AvailabilityDto availabilityResponse = mapToDto(newAvailability);
        return availabilityResponse;
	}

	@Override
	public List<AvailabilityDto> getAllAvailabilities() {
		List<Availability> availabilities = availabilityRepo.findAll();
        return availabilities.stream().map(a -> mapToDto(a)).collect(Collectors.toList());
	}

	@Override
	public AvailabilityDto getAvailabilityById(int id) {
		Availability availability = availabilityRepo.findById(id).orElseThrow(()
                -> new AvailabilityNotFoundException("Availability could not be found"));
        return mapToDto(availability);
	}

	@Override
	public AvailabilityDto updateAvailability(AvailabilityDto availabilityDto, int id) {
		Availability availability = availabilityRepo.findById(id).orElseThrow(()
                -> new AvailabilityNotFoundException("Availability could not be updated"));
        NailTech nailTech = techRepo.findById(availabilityDto.getNailTechId()).orElseThrow(()
                -> new NailTechNotFoundException("Corresponding nail tech could not be found"));;
        availability.getTimeSlot().setStartTime(availabilityDto.getStartTime());
        availability.getTimeSlot().setEndTime(availabilityDto.getEndTime());
        availability.setNailTech(nailTech);
        Availability updatedAvailability = availabilityRepo.save(availability);
        return mapToDto(updatedAvailability);
	}

	@Override
	public void deleteAvailability(int id) {
		Availability availability = availabilityRepo.findById(id).orElseThrow(()
                -> new AvailabilityNotFoundException("Availability could not be found"));
        availabilityRepo.delete(availability);
	}
}
