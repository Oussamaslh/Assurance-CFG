package com.example.Gestion.Assurence.Bancaire.dao.Repository;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Prelevement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrelevementRepository extends JpaRepository<Prelevement, Integer> {
}
