package com.socle.springboot.dto;

import lombok.Data;

import java.util.ArrayList;

import java.util.List;

@Data
public class EtablissementDto {

    private Long id;
    private String nameEtablissement;
    private List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
}
