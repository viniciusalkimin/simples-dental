package com.alkimin.simplesdental.infrastructure.profissional.dto;

import com.alkimin.simplesdental.domain.profissional.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarProfissionalRecord(@NotBlank String nome, @NotNull Cargo cargo, @NotNull LocalDate nascimento) {
}
