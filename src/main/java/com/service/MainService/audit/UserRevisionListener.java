package com.service.MainService.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity auditEntity = (AuditRevisionEntity) revisionEntity;

        // Récupération de l'agent connecté
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            // auth.getName() récupère l'identifiant (subject ou username) du token JWT
            auditEntity.setAgentUsername(auth.getName());
        } else {
            auditEntity.setAgentUsername("SYSTEM_PROCESS");
        }
    }
}
