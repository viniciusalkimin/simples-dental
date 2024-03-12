package com.alkimin.simplesdental.application.contato.service;

import com.alkimin.simplesdental.infrastructure.contato.dto.AtualizarContatoRecord;
import com.alkimin.simplesdental.infrastructure.contato.dto.RespostaContatoRecord;
import com.alkimin.simplesdental.infrastructure.contato.dto.ContatoRecord;
import com.alkimin.simplesdental.infrastructure.contato.dto.CriarContatoRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.AtualizarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.RespostaProfissionalRecord;

import java.util.List;

public interface ContatoService {

    ContatoRecord buscarPorId(String id);

    RespostaContatoRecord cadastrar(CriarContatoRecord criarContatoRecord);

    RespostaContatoRecord atualizar(String id, AtualizarContatoRecord atualizarContatoRecord);
    void deletar(String id);

    Object buscarPorParam(String q, List<String> fields);
}
