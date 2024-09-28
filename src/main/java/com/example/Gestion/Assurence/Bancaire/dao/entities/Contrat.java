package com.example.Gestion.Assurence.Bancaire.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name ="contrat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contrat {


    @Id



    private String id;


    private String cin;

    @NotNull
    private String codeClient;

    @NotNull
    private String partenaireCommercial;

    @NotNull
    private String cei;

    @NotNull
    private String agence;

    @NotNull
    private String produitPack;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEtablissement;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebutAbonnement;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinAbonnement;

    @NotNull
    private String numeroPolice;

    @NotNull
    private String periodicite;

    @NotNull
    private Double montantAssurance;

    @NotNull
    private Double versementInitial;

    @NotNull
    private String origineDesFonds;

    @NotNull
    private String origineDesFondsPrecisee;

    @NotNull
    private Double fraisAcquisition;

    @NotNull
    private String fraisAcquisitionPrecisee;

    @NotNull
    private String typeDePrelevement;

    @NotNull
    private String garantieFacultative;

    @NotNull
    private String rib;

    @NotNull
    private String numContratReformate;

    @NotNull
    private String jourPrelevement;

    @NotNull
    private Double montantCommission;

    @NotNull
    private Double montantTva;

    @NotNull
    private Double montantNet;

    @NotNull
    private Double montantAVirer;


    // Getters and setters
    @OneToMany(mappedBy ="contrat")
    private Collection<Prelevement> Prelevement;
}
