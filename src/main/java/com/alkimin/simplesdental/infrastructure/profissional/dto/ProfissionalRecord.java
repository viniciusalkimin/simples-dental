package com.alkimin.simplesdental.infrastructure.profissional.dto;

import com.alkimin.simplesdental.domain.profissional.Cargo;
import com.alkimin.simplesdental.domain.profissional.Profissional;

import java.time.LocalDate;
import java.util.UUID;

public record ProfissionalRecord(UUID id, String nome, Cargo cargo, LocalDate nascimento, boolean isAtivo, LocalDate created_date) {
    public ProfissionalRecord(Profissional profissional) {
        this(profissional.getId(), profissional.getNome(), profissional.getCargo(), profissional.getNascimento(), profissional.isAtivo(), profissional.getCreated_date());
    }
}
