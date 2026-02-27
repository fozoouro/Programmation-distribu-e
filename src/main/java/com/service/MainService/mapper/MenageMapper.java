package com.service.MainService.mapper;

import com.service.MainService.dto.MenageDTO;
import com.service.MainService.entity.Menage;
import com.service.MainService.entity.StatutOccupation;
import java.util.stream.Collectors;

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
                entity.getCategorieSociale(),
                entity.getResidents() != null ?
                        entity.getResidents().stream().map(ResidentMapper::toDTO).collect(Collectors.toList()) : null
        );
    }

    public static Menage toEntity(MenageDTO dto) {
        if (dto == null) return null;
        Menage entity = new Menage();

        // Si l'ID est présent, c'est une modification
        if (dto.id() != null) {
            entity.setId(dto.id());
        }

        entity.setATelevision(dto.aTelevision());
        entity.setARadio(dto.aRadio());
        entity.setAMoto(dto.aMoto());
        entity.setAVoiture(dto.aVoiture());

        if (dto.statutOccupation() != null) {
            entity.setStatutOccupation(StatutOccupation.valueOf(dto.statutOccupation()));
        }

        // On ne mappe pas manuellement le score ici,
        // c'est le Scoring Service qui s'en chargera via RabbitMQ.
        return entity;
    }
}