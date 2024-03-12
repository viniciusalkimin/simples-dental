package com.alkimin.simplesdental.application.profissional.utils;

import com.alkimin.simplesdental.application.utils.ObjectBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfissionaisWithParamBuilderTest {


    @Test
    void deveRetornarProfissionaisComParamsInformados() {
 var profissionalEntity = ObjectBuilder.criarProfissionalEntity("8331daf7-75c1-49d1-8807-e12842022916");
        var param1 = "nascimento";
        var param2 = "nome";
        var params = List.of(param1, param2);

        var returnToEvaluate = "[{nascimento=1995-11-16, nome=Vinicius Alkimin}]";
        var entityToString = "Profissional(id=8331daf7-75c1-49d1-8807-e12842022916, nome=Vinicius Alkimin, cargo=DESENVOLVEDOR, contatos=[], nascimento=1995-11-16, created_date=2024-03-08, isAtivo=true)";
        var returnOfProfissionaisWithParamBuilder = ProfissionaisWithParamBuilder.execute(List.of(profissionalEntity), params);

        assertNotEquals(returnOfProfissionaisWithParamBuilder.get(0).toString(), entityToString);
        assertEquals(returnOfProfissionaisWithParamBuilder.toString(), returnToEvaluate);
    }

}