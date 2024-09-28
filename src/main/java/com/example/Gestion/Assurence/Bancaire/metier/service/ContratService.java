package com.example.Gestion.Assurence.Bancaire.metier.service;



import com.example.Gestion.Assurence.Bancaire.dao.Repository.ContratRepository;
import com.example.Gestion.Assurence.Bancaire.dao.entities.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;


    public Contrat saveContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public Optional<Contrat> getContratById(String id) {
        return contratRepository.findById(id);
    }

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public Optional<Contrat> findById(String id) {
        return contratRepository.findById(id);
    }

    public List<Contrat> findByCin(String cin) {
        return contratRepository.findByCin(cin);
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    public void updateContrat(Contrat contrat) {
        contratRepository.save(contrat);
    }

    public void deleteContratById(String id) {
        contratRepository.deleteById(id);
    }

    public List<Contrat> getContratsByDatePrelevement(String datePrelevement) {
        return contratRepository.findByJourPrelevement(datePrelevement);
    }
    public void saveAll(List<Contrat> contrats) {
        contratRepository.saveAll(contrats);
    }

    public Contrat getById(String id) {
        return contratRepository.findById(id).orElse(null); // Assurez-vous que cela renvoie bien un Contrat ou null
    }
}





