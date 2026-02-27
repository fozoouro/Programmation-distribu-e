package com.service.MainService.dto;

import java.util.List;
import java.util.UUID;

public record MenageDTO(
        UUID id,
        boolean aTelevision,
        boolean aRadio,
        boolean aMoto,
        boolean aVoiture,
        String statutOccupation,
        Integer scoreSocial,
        String categorieSociale,
        List<ResidentDTO> residents // Ajout indispensable
) { }
