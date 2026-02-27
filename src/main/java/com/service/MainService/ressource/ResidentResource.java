package com.service.MainService.ressource;

import com.service.MainService.dto.ResidentDTO;
import com.service.MainService.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/residents")
public class ResidentResource {

    private final ResidentService residentService;

    public ResidentResource(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public ResponseEntity<List<ResidentDTO>> getAll() {
        return ResponseEntity.ok(residentService.getAllResidents());
    }

    @GetMapping("/{cni}")
    public ResponseEntity<ResidentDTO> getById(@PathVariable String cni) {
        return ResponseEntity.ok(residentService.getResidentById(cni));
    }

    // Récupération par ménage
    @GetMapping("/menage/{menageId}")
    public ResponseEntity<List<ResidentDTO>> getByMenage(@PathVariable String menageId) {
        UUID id = UUID.fromString(menageId);
        List<ResidentDTO> residents = residentService.getResidentsByMenage(id);
        return ResponseEntity.ok(residents);
    }

    @PostMapping
    public ResponseEntity<ResidentDTO> create(@RequestBody ResidentDTO dto) {
        return ResponseEntity.ok(residentService.createResident(dto));
    }

    @PutMapping("/{cni}")
    public ResponseEntity<ResidentDTO> update(@PathVariable String cni, @RequestBody ResidentDTO dto) {
        return ResponseEntity.ok(residentService.updateResident(cni, dto));
    }

    @DeleteMapping("/{cni}")
    public ResponseEntity<Void> delete(@PathVariable String cni) {
        residentService.deleteResident(cni);
        return ResponseEntity.noContent().build();
    }
}
