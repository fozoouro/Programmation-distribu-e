package com.service.MainService.ressource;

import com.service.MainService.dto.AuditHistoryDTO;
import com.service.MainService.entity.Menage;
import com.service.MainService.service.AuditService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/menages")
public class MenageAuditController {

    private final AuditService auditService;

    public MenageAuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/{id}/history")
    @PreAuthorize("hasRole('AGENT')")
    public List<AuditHistoryDTO<Menage>> getHistory(@PathVariable UUID id) {
        return auditService.getMenageHistory(id);
    }
}