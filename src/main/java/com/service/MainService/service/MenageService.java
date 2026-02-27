package com.service.MainService.service;

import com.service.MainService.dto.MenageDTO;
import com.service.MainService.entity.Menage;
import com.service.MainService.exception.ResourceNotFoundException;
import com.service.MainService.mapper.MenageMapper;
import com.service.MainService.repository.MenageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenageService {

    private final MenageRepository repository;

    public MenageService(MenageRepository repository) {
        this.repository = repository;
    }

    public MenageDTO create(MenageDTO dto) {
        Menage entity = MenageMapper.toEntity(dto);
        Menage saved = repository.save(entity);
        return MenageMapper.toDTO(saved);
    }

    public MenageDTO findById(UUID id) {
        Menage menage = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menage avec id: " + id + " introuvable")
                );
        return MenageMapper.toDTO(menage);
    }

    public List<MenageDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(MenageMapper::toDTO)
                .collect(Collectors.toList());
    }
}