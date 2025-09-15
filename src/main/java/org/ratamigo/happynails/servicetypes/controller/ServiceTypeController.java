package org.ratamigo.happynails.servicetypes.controller;

import java.util.List;

import org.ratamigo.happynails.servicetypes.dto.ServiceTypeDto;
import org.ratamigo.happynails.servicetypes.dto.ServiceTypeGetAllResponse;
import org.ratamigo.happynails.servicetypes.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping("services")
    public ResponseEntity<ServiceTypeGetAllResponse> getServices(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(serviceTypeService.getAllServices(pageNo, pageSize), 
                HttpStatus.OK);
    }

    @GetMapping("services/{id}")
    public ResponseEntity<ServiceTypeDto> serviceTypeDetail(@PathVariable("id") int serviceId) {
        return ResponseEntity.ok(serviceTypeService.getServiceTypeById(serviceId));
    }

    @PostMapping("services/create")
    public ResponseEntity<ServiceTypeDto> createService(
                @RequestBody ServiceTypeDto serviceTypeDto) {
        return new ResponseEntity<>(serviceTypeService.createServiceType(serviceTypeDto), 
                HttpStatus.CREATED);
    }

    @PutMapping("services/{id}/update")
    public ResponseEntity<ServiceTypeDto> updateService(@PathVariable("id") int serviceId, 
                                                     @RequestBody ServiceTypeDto serviceTypeDto) {
        return new ResponseEntity<>(serviceTypeService.updateServiceType
                (serviceTypeDto, serviceId), HttpStatus.OK);
    }

    @DeleteMapping("services/{id}/delete") 
    public ResponseEntity<String> deleteService(@PathVariable("id") int serviceId) {
        serviceTypeService.deleteServiceType(serviceId);
        return new ResponseEntity<>("Service deleted", HttpStatus.OK);
    }

    @GetMapping("nailtechs/{nailTechId}/services")
    public ResponseEntity<List<ServiceTypeDto>> getServicesByNailTechId(
                @PathVariable("nailTechId") int nailTechId) {
        return ResponseEntity.ok(serviceTypeService.getServicesByNailTechId(nailTechId));
    }
}
