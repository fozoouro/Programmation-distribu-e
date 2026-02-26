package com.service.MainService.repository;

import com.service.MainService.entity.Menage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenageRepository extends JpaRepository<Menage, UUID> {
    // Trouve tous les ménages dont le score est inférieur ou égal au seuil du programme
    List<Menage> findByScoreSocialLessThanEqual(Integer scoreMax);
}
