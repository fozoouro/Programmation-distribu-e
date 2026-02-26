package com.service.MainService.repository;

import com.service.MainService.entity.ProgrammeSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeSocialRepository extends JpaRepository<ProgrammeSocial, Long> {
}
