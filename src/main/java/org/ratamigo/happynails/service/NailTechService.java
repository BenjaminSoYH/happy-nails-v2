package org.ratamigo.happynails.service;

import java.util.List;

import org.ratamigo.happynails.dto.NailTechDto;

public interface NailTechService {

    NailTechDto createNailTech(NailTechDto nailTechDto);
    List<NailTechDto> getAllTechs();
    NailTechDto getNailTechById(int id);
    NailTechDto updateNailTech(NailTechDto nailTechDto, int id);
    void deleteNailTech(int id);
}
