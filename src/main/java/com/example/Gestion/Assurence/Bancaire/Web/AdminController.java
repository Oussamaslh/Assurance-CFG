package com.example.Gestion.Assurence.Bancaire.Web;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Contrat;
import com.example.Gestion.Assurence.Bancaire.dao.entities.Employee;
import com.example.Gestion.Assurence.Bancaire.metier.service.ContratService;
import com.example.Gestion.Assurence.Bancaire.metier.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ContratService contratService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("contrats", contratService.getAllContrats());
        return "admin_dashboard";
    }



    @GetMapping("/employee/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id.intValue());
        model.addAttribute("employee", employee);
        return "edit_employee";
    }

    @PostMapping("/employee/edit")
    public String editEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id.intValue());
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/contrat/edit/{id}")
    public String editContratForm(@PathVariable String id, Model model) {
        Contrat contrat = contratService.getContratById(id).orElse(null);
        model.addAttribute("contrat", contrat);
        return "edit_contrat";
    }

    @PostMapping("/contrat/edit")
    public String editContrat(@ModelAttribute Contrat contrat) {
        contratService.updateContrat(contrat);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/contrat/delete/{id}")
    public String deleteContrat(@PathVariable String id) {
        contratService.deleteContratById(id);
        return "redirect:/admin/dashboard";
    }

}





