package com.service.MainService.mapper;

import com.service.MainService.dto.ProgrammeSocialDTO;
import com.service.MainService.entity.ProgrammeSocial;

public class ProgrammeSocialMapper {

    public static ProgrammeSocialDTO toDTO(ProgrammeSocial entity) {
        if (entity == null) return null;
        return ProgrammeSocialDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .description(entity.getDescription())
                .scoreMaximumEligible(entity.getScoreMaximumEligible())
                .dateLancement(entity.getDateLancement())
                .build();
    }

    public static ProgrammeSocial toEntity(ProgrammeSocialDTO dto) {
        if (dto == null) return null;
        ProgrammeSocial ps = new ProgrammeSocial();
        ps.setId(dto.getId());
        ps.setNom(dto.getNom());
        ps.setDescription(dto.getDescription());
        ps.setScoreMaximumEligible(dto.getScoreMaximumEligible());
        ps.setDateLancement(dto.getDateLancement());
        return ps;
    }
}