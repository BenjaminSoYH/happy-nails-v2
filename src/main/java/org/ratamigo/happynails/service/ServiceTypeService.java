package org.ratamigo.happynails.service;

import java.util.List;

import org.ratamigo.happynails.dto.ServiceTypeDto;

public interface ServiceTypeService {
    ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto);

    List<ServiceTypeDto> getAllServices();

    ServiceTypeDto getServiceTypeById(int id);

    ServiceTypeDto updateServiceType(ServiceTypeDto serviceTypeDto, int id);

    void deleteServiceType(int id);
}
