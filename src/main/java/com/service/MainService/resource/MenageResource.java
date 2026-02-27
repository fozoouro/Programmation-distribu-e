package com.service.MainService.resource;

import com.service.MainService.dto.MenageDTO;
import com.service.MainService.infra.config.SecurityConfig;
import com.service.MainService.service.MenageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menages")
@SecurityRequirement(name = SecurityConfig.SWG_SS)
@Tag(name = "Menages API", description = "API de gestion des menages")
public class MenageResource {

    private final MenageService service;

    public MenageResource(MenageService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('AGENT')")
    @Operation(summary = "Créer un menage sociale", description = "Création de menage")
    public MenageDTO create(@RequestBody MenageDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('AGENT','CHEF')")
    @Operation(summary = "Récupérer tous les menages", description =
            "Récupération de tous les menages")
    public List<MenageDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('AGENT','CHEF')")
    @Operation(summary = "Récupérer un menage par son ID", description
            = "Récupération d'un menage par son ID")
    public MenageDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    @Operation(summary = "Modifier un menage par son ID", description =
            "Modification d'un menage par son ID")
    public MenageDTO update(@PathVariable UUID id, @RequestBody MenageDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    @Operation(summary = "Supprimer un menage par son ID", description =
            "Suppression d'un menage par son ID")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}