package com.service.MainService.audit;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "revinfo_custom") // Nom de la table en base de données
@RevisionEntity(UserRevisionListener.class) // On lie le Listener ci-dessous
public class AuditRevisionEntity extends DefaultRevisionEntity {

    private String agentUsername;

    public String getAgentUsername() {
        return agentUsername;
    }

    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }
}
