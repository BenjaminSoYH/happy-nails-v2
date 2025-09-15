package org.ratamigo.happynails.servicetypes.service;
import java.util.List;

import org.ratamigo.happynails.nailtechs.dto.NailTechDto;
import org.ratamigo.happynails.servicetypes.dto.ServiceTypeDto;
import org.ratamigo.happynails.servicetypes.dto.ServiceTypeGetAllResponse;

public interface ServiceTypeService {
    ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto);

    ServiceTypeGetAllResponse getAllServices(int pageNo, int pageSize);

    ServiceTypeDto getServiceTypeById(int id);

    ServiceTypeDto updateServiceType(ServiceTypeDto serviceTypeDto, int id);

    void deleteServiceType(int id);

    List<ServiceTypeDto> getServicesByNailTechId(int nailTechId);
}
