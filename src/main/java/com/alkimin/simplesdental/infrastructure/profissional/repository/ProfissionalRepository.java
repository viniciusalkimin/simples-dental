package com.alkimin.simplesdental.infrastructure.profissional.repository;

import com.alkimin.simplesdental.domain.profissional.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {
}
