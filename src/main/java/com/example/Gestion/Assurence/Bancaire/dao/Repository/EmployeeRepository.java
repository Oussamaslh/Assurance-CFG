package com.example.Gestion.Assurence.Bancaire.dao.Repository;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
    Employee findByEmailAndPassword(String email, String password);
    Optional<Employee> findById(Integer id);

}