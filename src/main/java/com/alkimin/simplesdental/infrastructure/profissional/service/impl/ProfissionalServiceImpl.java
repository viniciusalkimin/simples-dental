package com.alkimin.simplesdental.infrastructure.profissional.service.impl;

import com.alkimin.simplesdental.application.profissional.service.ProfissionalService;
import com.alkimin.simplesdental.application.profissional.utils.ProfissionaisWithParamBuilder;
import com.alkimin.simplesdental.application.profissional.validation.ProfissionalValidation;
import com.alkimin.simplesdental.domain.profissional.Profissional;
import com.alkimin.simplesdental.infrastructure.profissional.dto.AtualizarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.CriarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.ProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.RespostaProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.exception.ProfissionalNaoEncontradoException;
import com.alkimin.simplesdental.infrastructure.profissional.repository.ProfissionalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class ProfissionalServiceImpl implements ProfissionalService {

    private ProfissionalRepository profissionalRepository;

    @Override
    public ProfissionalRecord buscarPorId(String id) {
        var profissional = profissionalRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Status = error, ProfissionalService.buscarPorId().");
                    throw new ProfissionalNaoEncontradoException("Profissional com o ID informado não encontrado.");
                });

        return new ProfissionalRecord(profissional);
    }

    @Override
    public RespostaProfissionalRecord cadastrar(CriarProfissionalRecord criarProfissionalRecord) {
        log.info("Status = início, ProfissionalService.cadastrar().");

        var profissionalASalvar = Profissional.builder()
                .nome(criarProfissionalRecord.nome()).cargo(criarProfissionalRecord.cargo())
                .nascimento(criarProfissionalRecord.nascimento()).created_date(LocalDate.now())
                .isAtivo(true).build();
        var profissionalSalvo = profissionalRepository.save(profissionalASalvar);

        log.info("Status = fim, ProfissionalService.cadastrar().");
        return new RespostaProfissionalRecord(profissionalSalvo.getId());
    }

    @Override
    public RespostaProfissionalRecord atualizar(String id, AtualizarProfissionalRecord atualizarProfissionalRecord) {
        log.info("Status = início, ProfissionalService.atualizar().");

        var profissionalAAtualizar = profissionalRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Status = error, ProfissionalService.atualizar().");
                    throw new ProfissionalNaoEncontradoException("Profissional com o ID informado não encontrado.");
                });

        var profissionalValidado = ProfissionalValidation.validarAtualizacao(atualizarProfissionalRecord, profissionalAAtualizar );
        var profissionalSalvo = profissionalRepository.save(profissionalValidado);

        log.info("Status = fim, ProfissionalService.atualizar().");
        return new RespostaProfissionalRecord(profissionalSalvo.getId());
    }

    @Override
    public void deletar(String id) {
        log.info("Status = início, ProfissionalService.deletar().");

        var profissionalDeletar = profissionalRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Status = error, ProfissionalService.deletar().");
                    throw new ProfissionalNaoEncontradoException("Profissional com o ID informado não encontrado.");
                });
        profissionalDeletar.setAtivo(false);
        profissionalRepository.save(profissionalDeletar);

        log.info("Status = fim, ProfissionalService.deletar().");
    }

    @Override
    public Object buscarPorParam(String q, List<String> fields) {
        log.info("Passou buscarPorParam()");
        var profissionais = profissionalRepository.findByParam(q);
        if (!Objects.isNull(fields)) {
            return ProfissionaisWithParamBuilder.execute(profissionais, fields);
        }
        return profissionais;
    }
}
