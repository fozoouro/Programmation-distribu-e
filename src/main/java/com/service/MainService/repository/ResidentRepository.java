package com.service.MainService.repository;

import com.service.MainService.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, String> {
    List<Resident> findByMenageId(UUID menageId);
}
