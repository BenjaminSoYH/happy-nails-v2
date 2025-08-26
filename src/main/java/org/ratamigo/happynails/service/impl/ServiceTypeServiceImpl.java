package org.ratamigo.happynails.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.ratamigo.happynails.dto.ServiceTypeDto;
import org.ratamigo.happynails.exceptions.ServiceTypeNotFoundException;
import org.ratamigo.happynails.model.ServiceType;
import org.ratamigo.happynails.repository.ServiceTypeRepository;
import org.ratamigo.happynails.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: only update fields that are non-null in the incoming DTO.
// ServiceType existing = repository.findById(id).orElseThrow(...);

    // if (dto.getName() != null) {
    //     existing.setName(dto.getName());
    // }
    // if (dto.getDescription() != null) {
    //     existing.setDescription(dto.getDescription());
    // }
    // if (dto.getDuration() != null) {
    //     existing.setDuration(dto.getDuration());
    // }

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService{

    private ServiceTypeRepository serviceTypeRepo;

    @Autowired
    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepo = serviceTypeRepository;
    }

    public ServiceTypeDto mapToDto(ServiceType serviceType) {
        ServiceTypeDto serviceTypeDto = new ServiceTypeDto();
        serviceTypeDto.setDescription(serviceType.getDescription());
        serviceTypeDto.setDuration(serviceType.getDuration());
        serviceTypeDto.setId(serviceType.getId());
        serviceTypeDto.setName(serviceType.getName());

        return serviceTypeDto;
    }
    
    public ServiceType mapToEntity(ServiceTypeDto serviceTypeDto) {
        ServiceType serviceType = new ServiceType();
        serviceType.setDescription(serviceTypeDto.getDescription());
        serviceType.setDuration(serviceTypeDto.getDuration());
        serviceType.setName(serviceTypeDto.getName());

        return serviceType;
    }

    @Override
    public ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto) {
        ServiceType serviceType = mapToEntity(serviceTypeDto);
        ServiceType newServiceType = serviceTypeRepo.save(serviceType);
        ServiceTypeDto serviceTypeResponse = mapToDto(newServiceType);
        return serviceTypeResponse;
    }

    @Override
    public List<ServiceTypeDto> getAllServices() {
        List<ServiceType> serviceTypes = serviceTypeRepo.findAll();
        return serviceTypes.stream().map(s -> mapToDto(s)).collect(Collectors.toList());
    }

    @Override
    public ServiceTypeDto getServiceTypeById(int id) {
        ServiceType serviceType = serviceTypeRepo.findById(id).orElseThrow(() 
                -> new ServiceTypeNotFoundException("Service could not be found"));

        return mapToDto(serviceType);
    }

    @Override
    public ServiceTypeDto updateServiceType(ServiceTypeDto serviceTypeDto, int id) {
        ServiceType serviceType = serviceTypeRepo.findById(id).orElseThrow(() 
                -> new ServiceTypeNotFoundException("Service could not be updated"));
        if (serviceTypeDto.getDescription() != null) {
            serviceType.setDescription(serviceTypeDto.getDescription());
        }
        if (serviceTypeDto.getName() != null) {
            serviceType.setName(serviceTypeDto.getName());
        }
        serviceType.setDuration(serviceTypeDto.getDuration());

        ServiceType updatedServiceType = serviceTypeRepo.save(serviceType);

        return mapToDto(updatedServiceType);
    }

    @Override
    public void deleteServiceType(int id) {
        ServiceType serviceType = serviceTypeRepo.findById(id).orElseThrow(() 
                -> new ServiceTypeNotFoundException("Service could not be deleted"));
        
        serviceTypeRepo.delete(serviceType);
    }
}
