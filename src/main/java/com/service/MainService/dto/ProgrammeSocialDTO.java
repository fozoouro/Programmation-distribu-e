package com.service.MainService.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgrammeSocialDTO {
    private Long id;
    private String nom;
    private String description;
    private Integer scoreMaximumEligible;
    private LocalDate dateLancement;
}