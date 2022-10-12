package models;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@Getter
@Setter
@Entity
@Table(name = "etablissements")
public class Etablissements implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nameEtablissement")
    @Getter
    @Setter
    private String nameEtablissement;

    @OneToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Employees> employees = new ArrayList<Employees>();


//    public Etablissements(String nameEtablissement) {
//        this.nameEtablissement = nameEtablissement;
//        this.employees = new ArrayList<Employees>();
//    }


    public Etablissements(Long id, String nameEtablissement) {
        this.id = id;
        this.nameEtablissement = nameEtablissement;
    }


    public Etablissements() {

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEtablissement() {
        return nameEtablissement;
    }

    public void setNameEtablissement(String nameEtablissement) {
        this.nameEtablissement = nameEtablissement;
    }

    public Collection<Employees> getEmployees() {
        return employees;
    }


    public void setEmployees(Employees employees) {
        this.employees = (List<Employees>) employees;
    }

    public void addEmployee(Employees employees) {
        this.employees.add(employees);
    }


}
