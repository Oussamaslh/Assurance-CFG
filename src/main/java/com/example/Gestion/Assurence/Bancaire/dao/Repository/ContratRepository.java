package com.example.Gestion.Assurence.Bancaire.dao.Repository;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Contrat;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ContratRepository extends JpaRepository<Contrat, String> {
    Optional<Contrat> findById(String id);

    List<Contrat> findAll();
    List<Contrat> findByCin(String cin);
    List<Contrat> findByJourPrelevement(String jourPrelevement);
}
