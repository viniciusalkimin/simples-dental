package com.alkimin.simplesdental.application.utils;

import com.alkimin.simplesdental.domain.profissional.Cargo;
import com.alkimin.simplesdental.infrastructure.profissional.dto.ProfissionalRecord;

import java.time.LocalDate;
import java.util.UUID;

public class ObjectBuilder {

    public static ProfissionalRecord criarProfissionalRecord() {
        return new ProfissionalRecord(UUID.fromString("43e2d264-7f4c-11ee-b962-0242ac120002"), "Alberto Silva", Cargo.DESENVOLVEDOR, LocalDate.of(1995, 11, 16 ), true, LocalDate.now());
    }
}
