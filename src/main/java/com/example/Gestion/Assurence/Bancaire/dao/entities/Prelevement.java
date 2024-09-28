package com.example.Gestion.Assurence.Bancaire.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name ="prelevement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prelevement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private Date date;
    private float montant;
    private String contratid;



    @ManyToOne
    @JoinColumn(name = "contrat_id", referencedColumnName = "id", nullable = false)
    private Contrat contrat;
}
