package com.alkimin.simplesdental.application.profissional.validation;

import com.alkimin.simplesdental.domain.profissional.Profissional;
import com.alkimin.simplesdental.infrastructure.profissional.dto.AtualizarProfissionalRecord;

public final class ProfissionalValidation {

    public static Profissional validarAtualizacao(AtualizarProfissionalRecord atualizarProfissionalRecord, Profissional profissional) {
        if(!profissional.getNome().equals(atualizarProfissionalRecord.nome())) {
            profissional.setNome(atualizarProfissionalRecord.nome());
        }
        if(!profissional.getCargo().equals(atualizarProfissionalRecord.cargo())) {
            profissional.setCargo(atualizarProfissionalRecord.cargo());
        }
        if(!profissional.getNascimento().equals(atualizarProfissionalRecord.nascimento())) {
            profissional.setNascimento(atualizarProfissionalRecord.nascimento());
        }
        return profissional;
    }
}
