package com.service.MainService.ressource;

import com.service.MainService.dto.MenageDTO;
import com.service.MainService.service.MenageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menages")
public class MenageController {

    private final MenageService service;

    public MenageController(MenageService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('AGENT')")
    public MenageDTO create(@RequestBody MenageDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('AGENT','CHEF')")
    public List<MenageDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('AGENT','CHEF')")
    public MenageDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }
}