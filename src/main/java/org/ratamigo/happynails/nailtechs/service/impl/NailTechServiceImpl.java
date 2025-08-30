package org.ratamigo.happynails.nailtechs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.ratamigo.happynails.exceptions.NailTechNotFoundException;
import org.ratamigo.happynails.nailtechs.dto.NailTechDto;
import org.ratamigo.happynails.nailtechs.model.NailTech;
import org.ratamigo.happynails.nailtechs.repository.NailTechRepository;
import org.ratamigo.happynails.nailtechs.service.NailTechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NailTechServiceImpl implements NailTechService {

    private NailTechRepository techRepo;

    @Autowired
    public NailTechServiceImpl(NailTechRepository nailTechRepository) {
        this.techRepo = nailTechRepository;
    }

    public NailTechDto mapToDto(NailTech nailTech) {
        NailTechDto nailTechDto = new NailTechDto();
        nailTechDto.setId(nailTech.getId());
        nailTechDto.setName(nailTech.getName());
        nailTechDto.setEmail(nailTech.getEmail());
        nailTechDto.setPhone(nailTech.getPhone());

        return nailTechDto;
    }

    public NailTech mapToEntity(NailTechDto nailTechDto) {
        NailTech nailTech = new NailTech();
        nailTech.setEmail(nailTechDto.getEmail());
        nailTech.setPhone(nailTechDto.getPhone());
        nailTech.setName(nailTechDto.getName());

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
        NailTech updatedNailTech = techRepo.save(nailTech);

        return mapToDto(updatedNailTech);
    }

    @Override
    public void deleteNailTech(int id) {
        NailTech nailTech = techRepo.findById(id).orElseThrow(()
                -> new NailTechNotFoundException("Nail Tech could not be deleted"));
        techRepo.delete(nailTech);
    }
    
}
