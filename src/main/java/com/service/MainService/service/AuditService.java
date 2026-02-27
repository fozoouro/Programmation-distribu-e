package com.service.MainService.service;


import com.service.MainService.audit.AuditRevisionEntity;
import com.service.MainService.dto.AuditHistoryDTO;
import com.service.MainService.entity.Menage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuditService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AuditHistoryDTO<Menage>> getMenageHistory(UUID id) {

        AuditReader reader = AuditReaderFactory.get(entityManager);

        List<Object[]> results = reader.createQuery()
                .forRevisionsOfEntity(Menage.class, false, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();

        List<AuditHistoryDTO<Menage>> history = new ArrayList<>();

        for (Object[] row : results) {

            Menage menage = (Menage) row[0];
            AuditRevisionEntity revision = (AuditRevisionEntity) row[1];
            RevisionType revisionType = (RevisionType) row[2];

            history.add(
                    new AuditHistoryDTO<>(
                            revision.getId(),
                            Instant.ofEpochMilli(revision.getTimestamp()),
                            revision.getAgentUsername(),
                            revisionType.name(),
                            menage
                    )
            );
        }

        return history;
    }
}