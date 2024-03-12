package com.alkimin.simplesdental.infrastructure.contato.service.impl;

import com.alkimin.simplesdental.application.contato.service.ContatoService;
import com.alkimin.simplesdental.application.contato.utils.ContatosWithParamBuilder;
import com.alkimin.simplesdental.domain.contato.Contato;
import com.alkimin.simplesdental.infrastructure.contato.dto.*;
import com.alkimin.simplesdental.infrastructure.contato.exception.ContatoNaoEncontradoException;
import com.alkimin.simplesdental.infrastructure.contato.repository.ContatoRepository;
import com.alkimin.simplesdental.infrastructure.profissional.exception.ProfissionalNaoEncontradoException;
import com.alkimin.simplesdental.infrastructure.profissional.repository.ProfissionalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ContatoServiceImpl implements ContatoService {

    private ContatoRepository contatoRepository;

    private ProfissionalRepository profissionalRepository;

    @Override
    public ContatoRecord buscarPorId(String id) {
        log.info("Status = início, ContatoService.buscarPorId().");

        var contatoEntity = contatoRepository.findById(UUID.fromString(id))
                .orElseThrow( () -> new ContatoNaoEncontradoException("Contato com o ID informado não encontrado."));

        log.info("Status = fim, ContatoService.buscarPorId().");
        return new ContatoRecord(contatoEntity);
    }

    @Override
    public ContatoCadastradoRecord cadastrar(CriarContatoRecord criarContatoRecord) {
        log.info("Status = início, ContatoService.cadastrar().");

        var profissional = profissionalRepository.findById(UUID.fromString(criarContatoRecord.profissionalId()))
                .orElseThrow(() -> {
                    log.error("Status = error, ContatoService.cadastrar().");
                    throw new ProfissionalNaoEncontradoException("Profissional com o ID informado não encontrado.");
                });
        var contatoASalvar = Contato.builder()
                .profissional(profissional)
                .nome(criarContatoRecord.nome())
                .contato(criarContatoRecord.contato())
                .created_date(LocalDate.now()).build();

        var contatoSalvo = contatoRepository.save(contatoASalvar);

        log.info("Status = fim, ContatoService.cadastrar().");
        return new ContatoCadastradoRecord(contatoSalvo.getId().toString());
    }

    @Override
    public ContatoAtualizadoRecord atualizar(String id, AtualizarContatoRecord atualizarContatoRecord) {
        log.info("Status = início, ContatoService.atualizar().");

        var contatoAAtualizar = contatoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Status = error, ProfissionalService.atualizar().");
                    throw new ContatoNaoEncontradoException("Contato com o ID informado não encontrado.");
                });
        if(!atualizarContatoRecord.nome().equals(contatoAAtualizar.getNome())) {
            contatoAAtualizar.setNome(atualizarContatoRecord.nome());
        }
        if(!atualizarContatoRecord.contato().equals(contatoAAtualizar.getContato())) {
            contatoAAtualizar.setContato(atualizarContatoRecord.contato());
        }
        contatoRepository.save(contatoAAtualizar);
        log.info("Status = fim, ContatoService.atualizar().");
        return new ContatoAtualizadoRecord(contatoAAtualizar.getId().toString());
    }

    @Override
    public void deletar(String id) {
        log.info("Status = início, ContatoService.deletar().");
        var contato = contatoRepository.findById(UUID.fromString(id))
                .orElseThrow( () -> new ContatoNaoEncontradoException("Contato com o ID informado não encontrado."));
        contatoRepository.delete(contato);

        log.info("Status = fim, ContatoService.deletar().");
    }

    @Override
    public Object buscarPorParam(String q, List<String> fields) {
        log.info("Status = início, ContatoService.buscarPorParam().");
        var contatosByParam= contatoRepository.findByParam(q);
        if(!Objects.isNull(fields)) {
            log.info("Status = fim, ContatoService.buscarPorParam().ContatosWithParamBuilder.execute()");
            return ContatosWithParamBuilder.execute(contatosByParam, fields);
        }
        log.info("Status = fim, ContatoService.buscarPorParam().");
        return contatosByParam.stream().map(ContatoRecord::new).toList();
    }
}
