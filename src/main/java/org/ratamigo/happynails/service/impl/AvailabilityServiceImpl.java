package org.ratamigo.happynails.service.impl;

import java.util.List;

import org.ratamigo.happynails.dto.AvailabilityDto;
import org.ratamigo.happynails.model.Availability;
import org.ratamigo.happynails.model.NailTech;
import org.ratamigo.happynails.model.TimeSlot;
import org.ratamigo.happynails.repository.AvailabilityRepository;
import org.ratamigo.happynails.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{
    private AvailabilityRepository availabilityRepo;

    @Autowired
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepo) {
        this.availabilityRepo = availabilityRepo;
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createAvailability'");
	}

	@Override
	public List<AvailabilityDto> getAllAvailabilities() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAllAvailabilities'");
	}

	@Override
	public AvailabilityDto getAvailabilityById(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAvailabilityById'");
	}

	@Override
	public AvailabilityDto updateAvailability(AvailabilityDto availabilityDto, int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateAvailability'");
	}

	@Override
	public void deleteAvailability(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteAvailability'");
	}
}
