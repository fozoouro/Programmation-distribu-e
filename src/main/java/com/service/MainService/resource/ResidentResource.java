package com.service.MainService.resource;

import com.service.MainService.dto.ResidentDTO;
import com.service.MainService.infra.config.SecurityConfig;
import com.service.MainService.service.ResidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/residents")
@SecurityRequirement(name = SecurityConfig.SWG_SS)
@Tag(name = "Resident API", description = "API de gestion des residents")
public class ResidentResource {

    private final ResidentService residentService;

    public ResidentResource(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les residents", description =
            "Récupération de tous les residents")
    public ResponseEntity<List<ResidentDTO>> getAll() {
        return ResponseEntity.ok(residentService.getAllResidents());
    }

    @GetMapping("/{cni}")
    @Operation(summary = "Récupérer un resident par sa carte d'identité", description
            = "Récupération d'un resident par sa carte d'identité")
    public ResponseEntity<ResidentDTO> getById(@PathVariable String cni) {
        return ResponseEntity.ok(residentService.getResidentById(cni));
    }

    // Récupération par ménage
    @GetMapping("/menage/{menageId}")
    @Operation(summary = "Récupérer tous les residents d'un menage", description =
            "Récupération de tous les residents d'un menage")
    public ResponseEntity<List<ResidentDTO>> getByMenage(@PathVariable String menageId) {
        UUID id = UUID.fromString(menageId);
        List<ResidentDTO> residents = residentService.getResidentsByMenage(id);
        return ResponseEntity.ok(residents);
    }

    @PostMapping
    @Operation(summary = "Créer un resident", description = "Création de resident")
    public ResponseEntity<ResidentDTO> create(@RequestBody ResidentDTO dto) {
        return ResponseEntity.ok(residentService.createResident(dto));
    }

    @PutMapping("/{cni}")
    @Operation(summary = "Modifier un resident par sa carte d'identité", description =
            "Récupération d'un resident par sa carte d'identité")
    public ResponseEntity<ResidentDTO> update(@PathVariable String cni, @RequestBody ResidentDTO dto) {
        return ResponseEntity.ok(residentService.updateResident(cni, dto));
    }

    @DeleteMapping("/{cni}")
    @Operation(summary = "Supprimer un resident par sa carte d'identité", description =
            "Suppression d'un resident par sa carte d'identité")
    public ResponseEntity<Void> delete(@PathVariable String cni) {
        residentService.deleteResident(cni);
        return ResponseEntity.noContent().build();
    }
}
