package com.alkimin.simplesdental.application.profissional.utils;

import com.alkimin.simplesdental.domain.profissional.Profissional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ProfissionaisWithParamBuilder {

    public static List<HashMap<String, String>>execute(List<Profissional> profissionais, List<String> fields) {
        var profissionaisParam = new ArrayList<HashMap<String, String>>();
        profissionais.forEach(p -> {
            HashMap<String, String> map = new HashMap<>();
            fields.forEach(f -> {
                if (f.equals("id")) {
                    map.put("id", p.getId().toString());
                }
                if (f.equals("nome")) {
                    map.put("nome", p.getNome());
                }
                if (f.equals("cargo")) {
                    map.put("cargo", p.getCargo().toString());
                }
                if (f.equals("created_date")) {
                    map.put("created_date", p.getCreated_date().toString());
                }
                if (f.equals("is_ativo")) {
                    map.put("is_ativo", String.valueOf(p.isAtivo()));
                }
                if (f.equals("nascimento")) {
                    map.put("nascimento", p.getNascimento().toString());
                }
            });
            profissionaisParam.add(map);
        });
        return profissionaisParam;
    }
}

