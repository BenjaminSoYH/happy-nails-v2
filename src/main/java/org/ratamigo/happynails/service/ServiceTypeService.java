package org.ratamigo.happynails.service;
import org.ratamigo.happynails.dto.ServiceTypeDto;
import org.ratamigo.happynails.dto.ServiceTypeGetAllResponse;

public interface ServiceTypeService {
    ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto);

    ServiceTypeGetAllResponse getAllServices(int pageNo, int pageSize);

    ServiceTypeDto getServiceTypeById(int id);

    ServiceTypeDto updateServiceType(ServiceTypeDto serviceTypeDto, int id);

    void deleteServiceType(int id);
}
