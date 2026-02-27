package com.service.MainService.mapper;

import com.service.MainService.dto.MenageDTO;
import com.service.MainService.entity.Menage;

public class MenageMapper {

    public static MenageDTO toDTO(Menage entity) {
        if (entity == null) return null;

        return new MenageDTO(
                entity.getId(),
                entity.isATelevision(),
                entity.isARadio(),
                entity.isAMoto(),
                entity.isAVoiture(),
                entity.getStatutOccupation() != null ? entity.getStatutOccupation().name() : null,
                entity.getScoreSocial(),
                entity.getCategorieSociale()
        );
    }

    public static Menage toEntity(MenageDTO dto) {
        if (dto == null) return null;

        Menage entity = new Menage();
        entity.setATelevision(dto.aTelevision());
        entity.setARadio(dto.aRadio());
        entity.setAMoto(dto.aMoto());
        entity.setAVoiture(dto.aVoiture());
        entity.setScoreSocial(dto.scoreSocial());
        entity.setCategorieSociale(dto.categorieSociale());

        return entity;
    }
}