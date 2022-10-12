package com.socle.springboot.controller;

import com.socle.springboot.dto.EtablissementDto;
import io.swagger.annotations.Api;
import models.Etablissements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.EtablissementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/etablissement")
@Api(tags = "etablissementController", value = "Etablissement-api")
public class etablissementController {
    @Autowired
    private ModelMapper modelMapper;
    private final EtablissementService etablissementService;

    public etablissementController(EtablissementService etablissementService) {
        this.etablissementService = etablissementService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EtablissementDto>> index() {
        List<EtablissementDto> etabdto = etablissementService.findAll().stream().map(
                etablissements -> modelMapper.map(
                        etablissements, EtablissementDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(etabdto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtablissementDto> findByID(@PathVariable("id") Long id) {
        Etablissements etabdto = etablissementService.findOneById(id);
        EtablissementDto etablissementDto = modelMapper.map(etabdto, EtablissementDto.class);
        return new ResponseEntity(etablissementDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EtablissementDto> add(@RequestBody EtablissementDto etablissementDto) {
        // convert DTO to entity
        Etablissements etablissementRequest = modelMapper.map(etablissementDto, Etablissements.class);

        Etablissements et = etablissementService.addEtablissement(etablissementRequest);
        // convert entity to DTO
        EtablissementDto etablissementresponce = modelMapper.map(et, EtablissementDto.class);
        return new ResponseEntity<>(etablissementresponce, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        etablissementService.deleteEtablissemt(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/{etablissements_id}/add-employee/{employees_id}")
    public ResponseEntity<EtablissementDto> addEmployeeEtudiant(@PathVariable("etablissements_id") long etablissements_id,
                                                                @PathVariable("employees_id") long employees_id) {
        etablissementService.assignerEtablissementAuEmployee(etablissements_id, employees_id);
        return new ResponseEntity<EtablissementDto>(HttpStatus.OK);
    }


}
