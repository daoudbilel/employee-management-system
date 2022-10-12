package services;

import models.Employees;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employees addEmployee(Employees E) {
        return employeeRepository.save(E);
    }

    public List<Employees> findAll() {
        return employeeRepository.findAll();
    }

    public Employees findOneById(Long id) {
        Optional<Employees> result = employeeRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }
    }


//    public Optional<Employees> findOneById(Long id) {
//
//        return employeeRepository.findById(id);
//
//    }

    public Employees updateEmployee(Long id, Employees employeeRequest) {
        Employees empl = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        empl.setLastName(employeeRequest.getLastName());
        empl.setMail(employeeRequest.getMail());
        empl.setPassword(employeeRequest.getPassword());
        return employeeRepository.save(empl);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

//    public void patchEmployee(Employees emp, Long id){
//        if( id == emp.get)
//
//    }


}
