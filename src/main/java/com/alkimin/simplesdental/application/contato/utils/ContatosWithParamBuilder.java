package com.alkimin.simplesdental.application.contato.utils;

import com.alkimin.simplesdental.domain.contato.Contato;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public final class ContatosWithParamBuilder {

    public static List<HashMap<String, String>> execute(List<Contato> contatos, List<String> fields) {
        log.info("Status = início, ProfissionaisWithParamBuilder.execute().");
        var contatosParam = new ArrayList<HashMap<String, String>>();
        contatos.forEach(contato -> {
            HashMap<String, String> map = new HashMap<>();
            fields.forEach(field -> {
                if (field.equals("nome")) {
                    map.put("nome", contato.getNome());
                }
                if (field.equals("contato")) {
                    map.put("contato", contato.getContato());
                }
                if (field.equals("created_date")) {
                    map.put("created_date", contato.getCreated_date().toString());
                }
            });
            contatosParam.add(map);
        });
        log.info("Status = fim, ProfissionaisWithParamBuilder.execute().");
        return contatosParam;
    }
}
