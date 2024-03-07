package com.alkimin.simplesdental.infrastructure.profissional.dto;

import com.alkimin.simplesdental.domain.profissional.Cargo;

import java.time.LocalDate;

public record AtualizarProfissionalRecord(String nome, Cargo cargo, LocalDate nascimento) {
}
