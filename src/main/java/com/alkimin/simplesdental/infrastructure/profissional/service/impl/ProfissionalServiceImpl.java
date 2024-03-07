package com.alkimin.simplesdental.infrastructure.profissional.service.impl;

import com.alkimin.simplesdental.application.profissional.ProfissionalService;
import com.alkimin.simplesdental.domain.profissional.Profissional;
import com.alkimin.simplesdental.infrastructure.profissional.dto.CriarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.repository.ProfissionalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class ProfissionalServiceImpl implements ProfissionalService {

    private ProfissionalRepository profissionalRepository;

    @Override
    public String cadastrar(CriarProfissionalRecord criarProfissionalRecord) {
        var profissional = Profissional.builder()
                .nome(criarProfissionalRecord.nome()).cargo(criarProfissionalRecord.cargo())
                .nascimento(criarProfissionalRecord.nascimento()).created_date(LocalDate.now()).build();
        var profissionalSalvo = profissionalRepository.save(profissional);
        return profissionalSalvo.getId().toString();
    }
}
