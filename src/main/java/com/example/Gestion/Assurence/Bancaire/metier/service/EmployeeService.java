package com.example.Gestion.Assurence.Bancaire.metier.service;





import com.example.Gestion.Assurence.Bancaire.dao.Repository.EmployeeRepository;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Méthode pour authentifier un employé
    public Employee authenticate(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    // Méthode pour inscrire un nouvel employé
    public void registerUser(Employee employee) {
        employee.setRole("EMPLOYEE");
        // Enregistrer l'employé sans chiffrement du mot de passe
        employeeRepository.save(employee);
    }

    // Méthode pour créer un administrateur
    public void createAdmin(Employee employee) {
        // Assigner un rôle d'administrateur à l'employé (si applicable)
        employee.setRole("ADMIN");
        // Enregistrer l'employé sans chiffrement du mot de passe
        employeeRepository.save(employee);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(Integer.valueOf(id));
    }



}



