package com.alkimin.simplesdental.application.profissional.service;

import com.alkimin.simplesdental.infrastructure.profissional.dto.*;

import java.util.List;

public interface ProfissionalService {

    ProfissionalRecord buscarPorId(String id);

    ProfissionalCadastradoRecord cadastrar(CriarProfissionalRecord criarProfissionalRecord);

    ProfissionalAtualizadoRecord atualizar(String id, AtualizarProfissionalRecord atualizarProfissionalRecord);

    void deletar(String id);

    Object buscarPorParam(String q, List<String> fields);
}
