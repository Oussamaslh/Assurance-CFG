package com.example.Gestion.Assurence.Bancaire.Web;
import com.example.Gestion.Assurence.Bancaire.dao.entities.Contrat;
import com.example.Gestion.Assurence.Bancaire.metier.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/print")
public class PrintController {
@Autowired
    private final ContratService contratService; // Assurez-vous d'avoir un service pour accéder aux contrats

    @Autowired
    public PrintController(ContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping("/{id}")
    public String showPrintPage(@PathVariable("id") String id, Model model) {
        Contrat contrat = contratService.getById(id); // Utilise l'ID String
        if (contrat == null) {
            // Gérer le cas où le contrat n'est pas trouvé
            return "error";  // Redirige vers une page d'erreur si nécessaire
        }
        model.addAttribute("contrat", contrat);
        return "print";  // Assurez-vous que print.html existe
    }
}



