package com.service.MainService.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Audited // Active l'audit pour cette table
@Getter
@Setter
@Table(name = "menages")
public class Menage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean aTelevision;
    private boolean aRadio;
    private boolean aMoto;
    private boolean aVoiture;

    @Enumerated(EnumType.STRING)
    private StatutOccupation statutOccupation; // PROPRIETAIRE, LOCATAIRE

    private Integer scoreSocial;
    private String categorieSociale;

    @OneToMany(mappedBy = "menage", cascade = CascadeType.ALL)
    private List<Resident> residents = new ArrayList<>();
}
