package com.alkimin.simplesdental.application.profissional.utils;

import com.alkimin.simplesdental.domain.profissional.Profissional;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public final class ProfissionaisWithParamBuilder {

    public static List<HashMap<String, String>>execute(List<Profissional> profissionais, List<String> fields) {
        log.info("Status = início, ProfissionaisWithParamBuilder.execute().");
        var profissionaisParam = new ArrayList<HashMap<String, String>>();
        profissionais.forEach(profissional -> {
            HashMap<String, String> map = new HashMap<>();
            fields.forEach(field -> {
                if (field.equals("id")) {
                    map.put("id", profissional.getId().toString());
                }
                if (field.equals("nome")) {
                    map.put("nome", profissional.getNome());
                }
                if (field.equals("cargo")) {
                    map.put("cargo", profissional.getCargo().toString());
                }
                if (field.equals("created_date")) {
                    map.put("created_date", profissional.getCreated_date().toString());
                }
                if (field.equals("is_ativo")) {
                    map.put("is_ativo", String.valueOf(profissional.isAtivo()));
                }
                if (field.equals("nascimento")) {
                    map.put("nascimento", profissional.getNascimento().toString());
                }
            });
            profissionaisParam.add(map);
        });
        log.info("Status = fim, ProfissionaisWithParamBuilder.execute().");
        return profissionaisParam;
    }
}

