package com.service.MainService.service;

import com.service.MainService.dto.ResidentDTO;
import com.service.MainService.entity.Menage;
import com.service.MainService.entity.Resident;
import com.service.MainService.mapper.ResidentMapper;
import com.service.MainService.repository.ResidentRepository;
import com.service.MainService.repository.MenageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final MenageRepository menageRepository;

    public ResidentService(ResidentRepository residentRepository, MenageRepository menageRepository) {
        this.residentRepository = residentRepository;
        this.menageRepository = menageRepository;
    }

    public List<ResidentDTO> getAllResidents() {
        return residentRepository.findAll()
                .stream()
                .map(ResidentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ResidentDTO getResidentById(String cni) {
        Resident resident = residentRepository.findById(cni)
                .orElseThrow(() -> new RuntimeException("Resident not found"));
        return ResidentMapper.toDTO(resident);
    }

    public List<ResidentDTO> getResidentsByMenage(UUID menageId) {
        return residentRepository.findByMenageId(menageId)
                .stream()
                .map(ResidentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ResidentDTO createResident(ResidentDTO dto) {
        Resident resident = ResidentMapper.toEntity(dto);

        if (dto.menageId() != null) {
            Menage menage = menageRepository.findById(java.util.UUID.fromString(dto.menageId()))
                    .orElseThrow(() -> new RuntimeException("Menage not found"));
            resident.setMenage(menage);
        }

        resident = residentRepository.save(resident);
        return ResidentMapper.toDTO(resident);
    }

    public ResidentDTO updateResident(String cni, ResidentDTO dto) {
        Resident resident = residentRepository.findById(cni)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        resident.setNom(dto.nom());
        resident.setPrenom(dto.prenom());
        resident.setNationalite(dto.nationalite());
        resident.setDiplomePlusEleve(dto.diplomePlusEleve());
        resident.setEstLettre(dto.estLettre());
        resident.setDateNaissance(dto.dateNaissance());
        resident.setEstChef(dto.estChef());
        resident.setTrancheSalariale(dto.trancheSalariale());

        if (dto.menageId() != null) {
            Menage menage = menageRepository.findById(java.util.UUID.fromString(dto.menageId()))
                    .orElseThrow(() -> new RuntimeException("Menage not found"));
            resident.setMenage(menage);
        }

        return ResidentMapper.toDTO(residentRepository.save(resident));
    }

    public void deleteResident(String cni) {
        Resident resident = residentRepository.findById(cni)
                .orElseThrow(() -> new RuntimeException("Resident not found"));
        residentRepository.delete(resident);
    }
}