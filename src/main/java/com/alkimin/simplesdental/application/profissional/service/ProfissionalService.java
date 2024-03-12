package com.alkimin.simplesdental.application.profissional.service;

import com.alkimin.simplesdental.domain.profissional.Profissional;
import com.alkimin.simplesdental.infrastructure.profissional.dto.AtualizarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.CriarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.ProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.RespostaProfissionalRecord;

import java.util.List;

public interface ProfissionalService {

    ProfissionalRecord buscarPorId(String id);

    RespostaProfissionalRecord cadastrar(CriarProfissionalRecord criarProfissionalRecord);

    RespostaProfissionalRecord atualizar(String id,AtualizarProfissionalRecord atualizarProfissionalRecord);

    void deletar(String id);

    Object buscarPorParam(String q, List<String> fields);
}
