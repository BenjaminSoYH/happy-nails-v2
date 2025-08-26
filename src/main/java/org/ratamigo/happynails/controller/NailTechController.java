package org.ratamigo.happynails.controller;

import java.util.List;

import org.ratamigo.happynails.dto.NailTechDto;
import org.ratamigo.happynails.service.NailTechService;
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
public class NailTechController {

    private NailTechService nailTechService;

    @Autowired
    public NailTechController(NailTechService nailTechService) {
        this.nailTechService = nailTechService;
    }

    @GetMapping("nailtechs")
    public ResponseEntity<List<NailTechDto>> getTechs() {
        return new ResponseEntity<>(nailTechService.getAllTechs(), HttpStatus.OK);
    }

    @GetMapping("nailtechs/{id}")
    public ResponseEntity<NailTechDto> nailTechDetail(@PathVariable("id") int techId) {
        return ResponseEntity.ok(nailTechService.getNailTechById(techId));
    }

    @PostMapping("nailtechs/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NailTechDto> createTech(@RequestBody NailTechDto nailTechDto) {
        return new ResponseEntity<>(nailTechService.createNailTech(nailTechDto), HttpStatus.CREATED);
    }

    @PutMapping("nailtechs/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NailTechDto> updateTech(@RequestBody NailTechDto nailTechDto, 
                                                  @PathVariable("id") int techId) {
        return new ResponseEntity<>(nailTechService.updateNailTech(nailTechDto, techId), 
                                     HttpStatus.OK);
    }

    @DeleteMapping("nailtechs/{id}/delete")
    public ResponseEntity<String> deleteTech(@PathVariable("id") int techId) {
        nailTechService.deleteNailTech(techId);
        return new ResponseEntity<>("Nail Tech deleted", HttpStatus.OK);
    }

}
