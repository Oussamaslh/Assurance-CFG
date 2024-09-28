package com.example.Gestion.Assurence.Bancaire.Web;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Employee;
import com.example.Gestion.Assurence.Bancaire.metier.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String showLoginSignupForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("employee") Employee employee, Model model, HttpSession session) {
        // Authenticate the employee
        Employee foundEmployee = employeeService.authenticate(employee.getEmail(), employee.getPassword());

        if (foundEmployee != null) {
            // Store the authenticated employee in the session
            session.setAttribute("employee", foundEmployee);

            // Redirect based on the role of the employee
            if ("ADMIN".equals(foundEmployee.getRole())) {
                return "redirect:/admin/dashboard";
            } else if ("EMPLOYEE".equals(foundEmployee.getRole())) {
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Role non reconnu.");
                return "login";
            }
        } else {
            // Display an error message if authentication fails
            model.addAttribute("error", "Identifiants incorrects.");
            return "login";
        }
    }



    @PostMapping("/signup")
    public String signup(@ModelAttribute("employee") Employee employee) {
        employeeService.registerUser(employee);
        return "redirect:/login";
    }


    @GetMapping("/admin/signup")
    public String showAdminSignupForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin_signup";
    }

    @PostMapping("/admin/signup")
    public String adminSignup(@ModelAttribute("employee") Employee employee) {
        employeeService.createAdmin(employee);
        return "redirect:/login";
    }



}


