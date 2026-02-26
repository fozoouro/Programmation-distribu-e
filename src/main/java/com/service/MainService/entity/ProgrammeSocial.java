package com.service.MainService.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Audited
@Getter
@Setter
@Table(name = "programmes_sociaux")
public class ProgrammeSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    // Le seuil de score pour être éligible (ex: 45 pour "Vulnérable")
    private Integer scoreMaximumEligible;

    private LocalDate dateLancement;
}
