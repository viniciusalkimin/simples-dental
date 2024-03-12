package com.alkimin.simplesdental.application.utils;

import com.alkimin.simplesdental.domain.contato.Contato;
import com.alkimin.simplesdental.domain.profissional.Cargo;
import com.alkimin.simplesdental.domain.profissional.Profissional;
import com.alkimin.simplesdental.infrastructure.contato.dto.ContatoRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.AtualizarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.CriarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.ProfissionalRecord;
import org.mockito.internal.matchers.CapturesArguments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class ObjectBuilder {

    public static ProfissionalRecord criarProfissionalRecord() {
        return new ProfissionalRecord(UUID.fromString("43e2d264-7f4c-11ee-b962-0242ac120002"), "Alberto Silva", Cargo.DESENVOLVEDOR, LocalDate.of(1995, 11, 16 ), new ArrayList<>(), true, LocalDate.now());
    }

    public static Profissional criarProfissionalEntity(String id) {
        return Profissional.builder().id(UUID.fromString(id)).nome("Vinicius Alkimin").isAtivo(true)
                .cargo(Cargo.DESENVOLVEDOR).nascimento(LocalDate.of(1995, 11, 16)).created_date(LocalDate.of(2024, 03, 8))
                .contatos(new ArrayList<>()).build();
    }

    public static CriarProfissionalRecord criarCriarProfissionalRecord() {
        return new CriarProfissionalRecord("Vinicius Alkimin", Cargo.DESENVOLVEDOR, LocalDate.of(1995,11,16));
    }

    public static AtualizarProfissionalRecord criarAtualizarProfissionalRecord() {
        return new AtualizarProfissionalRecord("Vinicius Alkimin", Cargo.DESENVOLVEDOR, LocalDate.of(1995,11,16));
    }

    public static Contato criarContatoEntity(Profissional profissional, String contatoId) {
        return Contato.builder().id(UUID.fromString(contatoId)).nome("Celular")
                .profissional(profissional).contato("11962626363")
                .created_date(LocalDate.now()).build();
    }

    public static ContatoRecord criarContatoRecord(String contatoUuid, String profissionalUuid) {
        return new ContatoRecord(criarContatoEntity(criarProfissionalEntity(profissionalUuid), contatoUuid));
    }
}
