package org.ratamigo.happynails.controller;

import java.util.List;

import org.ratamigo.happynails.dto.ServiceTypeDto;
import org.ratamigo.happynails.service.ServiceTypeService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ServiceTypeController {

    private ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping("services")
    public ResponseEntity<List<ServiceTypeDto>> getServices() {
        return new ResponseEntity<>(serviceTypeService.getAllServices(), HttpStatus.OK);
    }

    @GetMapping("services/{id}")
    public ResponseEntity<ServiceTypeDto> serviceTypeDetail(@PathVariable("id") int serviceId) {
        return ResponseEntity.ok(serviceTypeService.getServiceTypeById(serviceId));
    }

    @PostMapping("services/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ServiceTypeDto> createService(@RequestBody ServiceTypeDto serviceTypeDto) {
        return new ResponseEntity<>(serviceTypeService.createServiceType(serviceTypeDto), HttpStatus.CREATED);
    }

    // start working on update. Start on ServiceTypeService.java add the method, then implement in the impl, then controller
    @PutMapping("services/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ServiceTypeDto> updateService(@PathVariable("id") int serviceId, 
                                                     @RequestBody ServiceTypeDto serviceTypeDto) {
        return new ResponseEntity<>(serviceTypeService.updateServiceType(serviceTypeDto, serviceId), HttpStatus.OK);
    }

    @DeleteMapping("services/{id}/delete") 
    public ResponseEntity<String> deleteService(@PathVariable("id") int serviceId) {
        serviceTypeService.deleteServiceType(serviceId);
        return new ResponseEntity<>("Service deleted", HttpStatus.OK);
    }
}
