package com.service.MainService.resource;

import com.service.MainService.dto.ProgrammeSocialDTO;
import com.service.MainService.infra.config.SecurityConfig;
import com.service.MainService.service.ProgrammeSocialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programmes")
@SecurityRequirement(name = SecurityConfig.SWG_SS)
@Tag(name = "Programme sociale API", description = "API de gestion des programme sociale")
public class ProgrammeSocialResource {

    private final ProgrammeSocialService service;

    public ProgrammeSocialResource(ProgrammeSocialService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les programmes sociale", description =
            "Récupération de tous les programmes sociale")
    public ResponseEntity<List<ProgrammeSocialDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une programme sociale par son ID", description
            = "Récupération d'une programme sociale par son ID")
    public ResponseEntity<ProgrammeSocialDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "Créer une programme sociale", description = "Création du programme sociale")
    public ResponseEntity<ProgrammeSocialDTO> create(@RequestBody ProgrammeSocialDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une programme sociale par son ID", description =
            "Modification d'une programme sociale par son ID")
    public ResponseEntity<ProgrammeSocialDTO> update(@PathVariable Long id, @RequestBody ProgrammeSocialDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une programme sociale par son ID", description =
            "Suppression d'une programme sociale par son ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}