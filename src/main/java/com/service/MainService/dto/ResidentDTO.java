package com.service.MainService.dto;

import com.service.MainService.entity.TrancheSalariale;

import java.time.LocalDate;

public record ResidentDTO(
        String cni,
        String nom,
        String prenom,
        String nationalite,
        String diplomePlusEleve,
        boolean estLettre,
        LocalDate dateNaissance,
        boolean estChef,
        TrancheSalariale trancheSalariale,
        String menageId
)
{ }
