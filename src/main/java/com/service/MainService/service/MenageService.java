package com.service.MainService.service;

import com.service.MainService.dto.MenageDTO;
import com.service.MainService.entity.Menage;
import com.service.MainService.exception.ResourceNotFoundException;
import com.service.MainService.mapper.MenageMapper;
import com.service.MainService.repository.MenageRepository;
import com.service.MainService.infra.config.RabbitMQConfig; // Import ajouté
import org.springframework.amqp.rabbit.core.RabbitTemplate; // Import ajouté
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenageService {

    private final MenageRepository repository;
    private final RabbitTemplate rabbitTemplate; // Déclaration ajoutée

    // Injection dans le constructeur
    public MenageService(MenageRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public MenageDTO create(MenageDTO dto) {
        Menage entity = MenageMapper.toEntity(dto);
        Menage saved = repository.save(entity);

        MenageDTO result = MenageMapper.toDTO(saved);
        enviarMessageAuScoring(result);

        return result;
    }

    public MenageDTO findById(UUID id) {
        return repository.findById(id)
                .map(MenageMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Menage avec id: " + id + " introuvable"));
    }

    public List<MenageDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(MenageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MenageDTO update(UUID id, MenageDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible de modifier : Menage avec id " + id + " introuvable");
        }

        Menage entity = MenageMapper.toEntity(dto);
        entity.setId(id);

        Menage updated = repository.save(entity);
        MenageDTO result = MenageMapper.toDTO(updated);

        enviarMessageAuScoring(result);
        return result;
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Menage avec id " + id + " introuvable");
        }
        repository.deleteById(id);
    }

    private void enviarMessageAuScoring(MenageDTO dto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.SCORING_EXCHANGE,
                RabbitMQConfig.SCORING_ROUTING_KEY,
                dto
        );
        System.out.println("Message envoyé vers RabbitMQ pour le ménage: " + dto.id());
    }
}