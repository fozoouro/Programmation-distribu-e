package com.service.MainService.dto;

import lombok.Getter;

import java.time.Instant;

@Getter
public class AuditHistoryDTO<T> {

    private Integer revisionId;
    private Instant dateRevision;
    private String agentUsername;
    private String typeModification;
    private T donnees;

    public AuditHistoryDTO(Integer revisionId,
                           Instant dateRevision,
                           String agentUsername,
                           String typeModification,
                           T donnees) {
        this.revisionId = revisionId;
        this.dateRevision = dateRevision;
        this.agentUsername = agentUsername;
        this.typeModification = typeModification;
        this.donnees = donnees;
    }
}