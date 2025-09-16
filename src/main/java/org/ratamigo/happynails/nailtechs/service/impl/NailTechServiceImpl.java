package org.ratamigo.happynails.nailtechs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.ratamigo.happynails.exceptions.NailTechNotFoundException;
import org.ratamigo.happynails.exceptions.ServiceTypeNotFoundException;
import org.ratamigo.happynails.nailtechs.dto.NailTechDto;
import org.ratamigo.happynails.nailtechs.model.NailTech;
import org.ratamigo.happynails.nailtechs.repository.NailTechRepository;
import org.ratamigo.happynails.nailtechs.service.NailTechService;
import org.ratamigo.happynails.servicetypes.model.ServiceType;
import org.ratamigo.happynails.servicetypes.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NailTechServiceImpl implements NailTechService {

    private NailTechRepository techRepo;
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public NailTechServiceImpl(NailTechRepository nailTechRepository,
                               ServiceTypeRepository serviceTypeRepository) {
        this.techRepo = nailTechRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public NailTechDto mapToDto(NailTech nailTech) {
        NailTechDto nailTechDto = new NailTechDto();
        nailTechDto.setId(nailTech.getId());
        nailTechDto.setName(nailTech.getName());
        nailTechDto.setEmail(nailTech.getEmail());
        nailTechDto.setPhone(nailTech.getPhone());
        nailTechDto.setDescription(nailTech.getDescription());
        nailTechDto.setPath(nailTech.getPath());

        return nailTechDto;
    }

    public NailTech mapToEntity(NailTechDto nailTechDto) {
        NailTech nailTech = new NailTech();
        nailTech.setEmail(nailTechDto.getEmail());
        nailTech.setPhone(nailTechDto.getPhone());
        nailTech.setName(nailTechDto.getName());
        nailTech.setDescription(nailTechDto.getDescription());
        nailTech.setPath(nailTechDto.getPath());

        return nailTech;
    }

    @Override
    public NailTechDto createNailTech(NailTechDto nailTechDto) {
        NailTech nailTech = mapToEntity(nailTechDto);
        NailTech newNailTech = techRepo.save(nailTech);
        NailTechDto nailTechResponse = mapToDto(newNailTech);
        return nailTechResponse;
    }

    @Override
    public List<NailTechDto> getAllTechs() {
        List<NailTech> nailTechs = techRepo.findAll();
        return nailTechs.stream().map(t -> mapToDto(t)).collect(Collectors.toList());
    }

    @Override
    public NailTechDto getNailTechById(int id) {
        NailTech nailTech = techRepo.findById(id).orElseThrow(()
                -> new NailTechNotFoundException("Nail Tech could not be found"));

        return mapToDto(nailTech);
    }

    @Override
    public NailTechDto updateNailTech(NailTechDto nailTechDto, int id) {
        NailTech nailTech = techRepo.findById(id).orElseThrow(()
                -> new NailTechNotFoundException("Nail Tech could not be updated"));
        if (nailTechDto.getEmail() != null) {
            nailTech.setEmail(nailTechDto.getEmail());
        }
        if(nailTechDto.getName() != null) {
            nailTech.setName(nailTechDto.getName());
        }
        if(nailTechDto.getPhone() != null) {
            nailTech.setPhone(nailTechDto.getPhone());
        }
        if(nailTechDto.getDescription() != null) {
            nailTech.setDescription(nailTechDto.getDescription());
        }
        if(nailTechDto.getPath() != null) {
            nailTech.setPath(nailTechDto.getPath());
        }

        NailTech updatedNailTech = techRepo.save(nailTech);

        return mapToDto(updatedNailTech);
    }

    @Override
    public void deleteNailTech(int id) {
        NailTech nailTech = techRepo.findById(id).orElseThrow(()
                -> new NailTechNotFoundException("Nail Tech could not be deleted"));
        techRepo.delete(nailTech);
    }

    @Transactional
    public void assignServiceToNailTech(int techId, int serviceId) {
        NailTech nailTech = techRepo.findById(techId).orElseThrow(()
                -> new NailTechNotFoundException("Nail Tech could not be found"));
        ServiceType service = serviceTypeRepository.findById(serviceId).orElseThrow(() 
                -> new ServiceTypeNotFoundException("Service could not be found"));

        if (!nailTech.getServices().contains(service)) {
            nailTech.getServices().add(service);
        }
        techRepo.save(nailTech);
    }

    @Transactional
    public void removeServiceFromNailTech(int techId, int serviceId) {
        NailTech nailTech = techRepo.findById(techId).orElseThrow(()
                -> new NailTechNotFoundException("Nail Tech could not be found"));
        nailTech.getServices().removeIf(s -> s.getId() == serviceId);
        techRepo.save(nailTech);
    }

    @Override
    public List<NailTechDto> getNailTechsByServiceId(int serviceId) {
        List<NailTech> nailTechs = techRepo.findByServicesId(serviceId);
        if (nailTechs.isEmpty()) {
                throw new NailTechNotFoundException("No nail techs found for this service");
        }
        return nailTechs.stream().map(s -> mapToDto(s)).collect(Collectors.toList());
    }


}
