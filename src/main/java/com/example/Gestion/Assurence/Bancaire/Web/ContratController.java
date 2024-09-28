package com.example.Gestion.Assurence.Bancaire.Web;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Contrat;
import com.example.Gestion.Assurence.Bancaire.dao.entities.Employee;
import com.example.Gestion.Assurence.Bancaire.metier.service.ContratService;
import com.example.Gestion.Assurence.Bancaire.metier.service.EmployeeService;
import com.example.Gestion.Assurence.Bancaire.metier.service.ExcelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ContratController {

    private static final Logger logger = LoggerFactory.getLogger(ContratController.class);

    @Autowired
    private ContratService contratService;
    @Autowired
    private ExcelService excelService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/Contrat")
    public String showContratForm(Model model, @RequestParam(name = "id", defaultValue = "") String id) {
        if (!id.isEmpty()) {
            Optional<Contrat> contrat = contratService.getContratById(id);
            if (contrat.isPresent()) {
                model.addAttribute("contrat", contrat.get());
            } else {
                model.addAttribute("contrat", new Contrat());
                model.addAttribute("error", "Contrat non trouvé pour l'ID: " + id);
            }
        } else {
            model.addAttribute("contrat", new Contrat());
        }
        return "Contrat";
    }

    @PostMapping("/Contrat")
    public String submitContratForm(@Valid @ModelAttribute Contrat contrat, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Contrat";
        }

        String contratId = contrat.getId();
        if (contratId != null) {
            Optional<Contrat> existingContrat = contratService.getContratById(contratId);
            if (existingContrat.isPresent()) {
                model.addAttribute("contrat", contrat);
                model.addAttribute("errorMessage", "Un contrat avec cet ID existe déjà. Veuillez utiliser un autre ID.");
                return "Contrat";
            }
        }

        double montantAssurance = contrat.getMontantAssurance();
        double fraisAcquisition = contrat.getFraisAcquisition();

        double montantCommission;
        if (fraisAcquisition != 0) {
            montantCommission = Math.floor(montantAssurance * (fraisAcquisition / 100) * 100) / 100;
        } else {
            montantCommission = Math.floor(montantAssurance * (fraisAcquisition / 100) * 100) / 100;
        }
        contrat.setMontantCommission(montantCommission);

        double montantTva;
        if (fraisAcquisition == 99) {
            montantTva = Math.floor(montantAssurance * (fraisAcquisition / 100) * 0.1071 * 100) / 100;
        } else {
            montantTva = Math.floor(montantCommission * 0.1071 * 100) / 100;
        }
        contrat.setMontantTva(montantTva);

        double montantNet = montantAssurance - montantCommission;
        contrat.setMontantNet(montantNet);

        double montantAVirer = montantNet + montantTva;
        contrat.setMontantAVirer(montantAVirer);

        contratService.saveContrat(contrat);
        return "success";
    }

    @GetMapping("/contrats")
    public String listContrats(Model model) {
        model.addAttribute("contrats", contratService.getAllContrats());
        return "ContratList";
    }

    @GetMapping("/contrats/search")
    public String searchContrats(@RequestParam(required = false) String id,
                                 @RequestParam(required = false) String cin,
                                 @RequestParam(required = false) String prelevement,
                                 Model model) {
        List<Contrat> contrats;

        if (prelevement != null && !prelevement.isEmpty()) {
            contrats = contratService.getContratsByDatePrelevement(prelevement);
        } else if (id != null && !id.isEmpty()) {
            Optional<Contrat> optionalContrat = contratService.getContratById(id);
            contrats = optionalContrat.map(Collections::singletonList)
                    .orElseGet(Collections::emptyList);
        } else if (cin != null && !cin.isEmpty()) {
            contrats = contratService.findByCin(cin);
        } else {
            contrats = contratService.findAll();
        }

        model.addAttribute("contrats", contrats);
        return "ContratList";
    }

    @PostMapping("/import")
    public String importExcel(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner un fichier à télécharger.");
            return "redirect:/";
        }

        try {
            excelService.importExcelFile(file);
            redirectAttributes.addFlashAttribute("message", "Fichier importé avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Échec de l'importation du fichier: " + e.getMessage());
        }

        return "redirect:/";
    }
}








