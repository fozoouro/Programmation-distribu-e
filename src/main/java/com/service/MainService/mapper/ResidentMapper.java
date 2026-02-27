package com.service.MainService.mapper;

import com.service.MainService.dto.ResidentDTO;
import com.service.MainService.entity.Resident;

public class ResidentMapper {

    public static ResidentDTO toDTO(Resident resident) {
        return new ResidentDTO(
                resident.getCni(),
                resident.getNom(),
                resident.getPrenom(),
                resident.getNationalite(),
                resident.getDiplomePlusEleve(),
                resident.isEstLettre(),
                resident.getDateNaissance(),
                resident.isEstChef(),
                resident.getTrancheSalariale(),
                resident.getMenage() != null ? resident.getMenage().getId().toString() : null
        );
    }

    public static Resident toEntity(ResidentDTO dto) {
        Resident resident = new Resident();
        resident.setCni(dto.cni());
        resident.setNom(dto.nom());
        resident.setPrenom(dto.prenom());
        resident.setNationalite(dto.nationalite());
        resident.setDiplomePlusEleve(dto.diplomePlusEleve());
        resident.setEstLettre(dto.estLettre());
        resident.setDateNaissance(dto.dateNaissance());
        resident.setEstChef(dto.estChef());
        resident.setTrancheSalariale(dto.trancheSalariale());
        // menage devra être géré dans le service
        return resident;
    }
}
