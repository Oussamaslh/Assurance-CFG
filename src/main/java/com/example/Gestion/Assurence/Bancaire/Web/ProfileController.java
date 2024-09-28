package com.example.Gestion.Assurence.Bancaire.Web;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Employee;
import com.example.Gestion.Assurence.Bancaire.metier.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/profile")
    public String profile(@RequestParam("id") Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return "error"; // Handle this in error.html
        }
        model.addAttribute("employee", employee);
        return "profile";
    }
}

