package com.alkimin.simplesdental.infrastructure.contato.dto;

import com.alkimin.simplesdental.domain.contato.Contato;

import java.time.LocalDate;
import java.util.UUID;

public record ContatoRecord(UUID id, String profissionalId, String nome, String contato, LocalDate created_date) {

    public ContatoRecord(Contato contato) {
        this(contato.getId(), contato.getProfissional().getId().toString(),contato.getNome(), contato.getContato(), contato.getCreated_date());
    }
}
