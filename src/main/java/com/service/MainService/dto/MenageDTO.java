package com.service.MainService.dto;

import java.util.UUID;

public record MenageDTO(
        UUID id,
        boolean aTelevision,
        boolean aRadio,
        boolean aMoto,
        boolean aVoiture,
        String statutOccupation,
        Integer scoreSocial,
        String categorieSociale
) { }
