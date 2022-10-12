package services;

import models.Employees;
import models.Etablissements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;
import repository.EtablissementRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {

    @Autowired
    EtablissementRepository etablissementRepository;
    EmployeeRepository employeeRepository;


    public EtablissementService(EtablissementRepository etablissementRepository) {

        this.etablissementRepository = etablissementRepository;
    }

    public Etablissements addEtablissement(Etablissements ET) {
        return etablissementRepository.save(ET);
    }

    public List<Etablissements> findAll() {
        return etablissementRepository.findAll();
    }

    public Etablissements findOneById(Long id) {
        Optional<Etablissements> resultat = etablissementRepository.findById(id);
        if (resultat.isPresent()) {
            return resultat.get();
        } else {
            throw new ResourceNotFoundException("Etablissement", "id", id);
        }
    }


    public Etablissements updateEtablissement(long id, Etablissements etablissementRequest) {
        Etablissements etabl = etablissementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", id));
        etabl.setNameEtablissement(etablissementRequest.getNameEtablissement());
//        etabl.setEmployees(etablissementRequest.getEmployees());
        return etablissementRepository.save(etabl);
    }

    public void deleteEtablissemt(Long id) {
        etablissementRepository.deleteById(id);
    }


    public Etablissements assignerEtablissementAuEmployee(long etablissements_id, long employees_id) {
        Employees foundedEmployee = employeeRepository.findById(employees_id)
                .orElseThrow(() -> new NotFoundException("Employee not  found !"));
        Etablissements foundedEtablissements = findOneById(etablissements_id);
        foundedEtablissements.setEmployees(foundedEmployee);
        foundedEmployee.add(foundedEmployee);
        employeeRepository.save(foundedEmployee);
        return etablissementRepository.save(foundedEtablissements);
    }

}
