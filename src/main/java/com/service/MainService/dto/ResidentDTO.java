package com.service.MainService.dto;

public record ResidentDTO(
        String cni,
        String nom,
        String prenom,
        String trancheSalarialeLabel, // On envoie le texte lisible
        boolean estChef
)
{ }
