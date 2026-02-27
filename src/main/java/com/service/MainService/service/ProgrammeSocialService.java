package com.service.MainService.service;

import com.service.MainService.dto.ProgrammeSocialDTO;
import com.service.MainService.entity.ProgrammeSocial;
import com.service.MainService.mapper.ProgrammeSocialMapper;
import com.service.MainService.repository.ProgrammeSocialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProgrammeSocialService {

    private final ProgrammeSocialRepository repository;

    public ProgrammeSocialService(ProgrammeSocialRepository repository) {
        this.repository = repository;
    }

    public List<ProgrammeSocialDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(ProgrammeSocialMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProgrammeSocialDTO getById(Long id) {
        ProgrammeSocial entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgrammeSocial not found with id: " + id));
        return ProgrammeSocialMapper.toDTO(entity);
    }

    public ProgrammeSocialDTO create(ProgrammeSocialDTO dto) {
        ProgrammeSocial entity = ProgrammeSocialMapper.toEntity(dto);
        entity = repository.save(entity);
        return ProgrammeSocialMapper.toDTO(entity);
    }

    public ProgrammeSocialDTO update(Long id, ProgrammeSocialDTO dto) {
        ProgrammeSocial entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgrammeSocial not found with id: " + id));
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setScoreMaximumEligible(dto.getScoreMaximumEligible());
        entity.setDateLancement(dto.getDateLancement());
        entity = repository.save(entity);
        return ProgrammeSocialMapper.toDTO(entity);
    }

    public void delete(Long id) {
        ProgrammeSocial entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgrammeSocial not found with id: " + id));
        repository.delete(entity);
    }
}
