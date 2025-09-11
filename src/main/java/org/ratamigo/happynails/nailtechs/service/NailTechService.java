package org.ratamigo.happynails.nailtechs.service;

import java.util.List;

import org.ratamigo.happynails.nailtechs.dto.NailTechDto;

public interface NailTechService {

    NailTechDto createNailTech(NailTechDto nailTechDto);
    List<NailTechDto> getAllTechs();
    NailTechDto getNailTechById(int id);
    NailTechDto updateNailTech(NailTechDto nailTechDto, int id);
    void deleteNailTech(int id);
    void assignServiceToNailTech(int techId, int serviceId);
    void removeServiceFromNailTech(int techId, int serviceId);
    List<NailTechDto> getNailTechsByServiceId(int serviceId);
}
