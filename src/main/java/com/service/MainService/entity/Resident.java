package com.service.MainService.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Audited
@Getter
@Setter
@Table(name = "residents")
public class Resident {
    @Id
    private String cni;

    private String nom;
    private String prenom;
    private String nationalite;
    private String diplomePlusEleve;
    private boolean estLettre;
    private LocalDate dateNaissance;
    private boolean estChef;

    @Enumerated(EnumType.STRING)
    private TrancheSalariale trancheSalariale;

    @ManyToOne
    @JoinColumn(name = "menage_id")
    private Menage menage;
}