package com.alkimin.simplesdental.application.contato.service;

import com.alkimin.simplesdental.infrastructure.contato.dto.*;

import java.util.List;

public interface ContatoService {

    ContatoRecord buscarPorId(String id);

    ContatoCadastradoRecord cadastrar(CriarContatoRecord criarContatoRecord);

    ContatoAtualizadoRecord atualizar(String id, AtualizarContatoRecord atualizarContatoRecord);
    void deletar(String id);

    Object buscarPorParam(String q, List<String> fields);
}
