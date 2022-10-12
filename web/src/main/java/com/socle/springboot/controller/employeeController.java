package com.socle.springboot.controller;

import com.socle.springboot.dto.EmployeeDto;
import io.swagger.annotations.Api;
import models.Employees;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.EmployeeService;
import services.EtablissementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@Api(tags = "Employee", value = "Employee-API")
public class employeeController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EtablissementService etablissementService;


    public employeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> index() {
        List<EmployeeDto> Empdto = employeeService.findAll().stream().map(
                employees -> modelMapper.map(
                        employees, EmployeeDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(Empdto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findByID(@PathVariable("id") Long id) {
        Employees emp = employeeService.findOneById(id);
        EmployeeDto employeeResponce = modelMapper.map(emp, EmployeeDto.class);

        return new ResponseEntity(employeeResponce, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> add(@RequestBody EmployeeDto employeeDto) {
        // convert DTO to entity
        Employees employeeRequest = modelMapper.map(employeeDto, Employees.class);
        employeeRequest.setEtablissements(etablissementService.findOneById(employeeDto.getEtablissement_id()));

        Employees emp = employeeService.addEmployee(employeeRequest);
        // convert entity to DTO
        EmployeeDto employeeResponce = modelMapper.map(emp, EmployeeDto.class);
        employeeResponce.setEtablissement_id(emp.getEtablissements().getId());
        return new ResponseEntity<>(employeeResponce, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> update(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        // convert DTO to Entity
        Employees employeeRequest = modelMapper.map(employeeDto, Employees.class);
        Employees empl = employeeService.updateEmployee(id, employeeRequest);
        // entity to DTO
        EmployeeDto employeeResponce = modelMapper.map(empl, EmployeeDto.class);
        return new ResponseEntity<>(employeeResponce, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
