package com.service.MainService.resource;

import com.service.MainService.dto.ProgrammeSocialDTO;
import com.service.MainService.service.ProgrammeSocialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programmes")
public class ProgrammeSocialResource {

    private final ProgrammeSocialService service;

    public ProgrammeSocialResource(ProgrammeSocialService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeSocialDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammeSocialDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProgrammeSocialDTO> create(@RequestBody ProgrammeSocialDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammeSocialDTO> update(@PathVariable Long id, @RequestBody ProgrammeSocialDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}