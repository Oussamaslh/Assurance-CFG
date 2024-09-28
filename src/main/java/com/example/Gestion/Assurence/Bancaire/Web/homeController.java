package com.example.Gestion.Assurence.Bancaire.Web;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Employee;
import com.example.Gestion.Assurence.Bancaire.metier.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {


    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            // Handle the case where the employee is not found in the session
            // For example, redirect to a login page
            return "redirect:/login";
        }
        model.addAttribute("employee", employee);
        return "home";
    }

    @GetMapping("/contrat")
    public String contrat() {
        return "Contrat"; // This serves contrat.html
    }

    @GetMapping("/search-contrat")
    public String searchContrat() {
        return "ContratList"; // This serves search-contrat.html
    }
}
