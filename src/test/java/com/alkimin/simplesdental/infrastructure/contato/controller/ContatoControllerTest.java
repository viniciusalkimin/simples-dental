package com.alkimin.simplesdental.infrastructure.contato.controller;

import com.alkimin.simplesdental.application.contato.service.ContatoService;
import com.alkimin.simplesdental.application.utils.ObjectBuilder;
import com.alkimin.simplesdental.infrastructure.contato.dto.RespostaContatoRecord;
import com.alkimin.simplesdental.infrastructure.contato.service.impl.ContatoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContatoControllerTest {

    private final String PATH = "/contatos";

    @MockBean
    ContatoService contatoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void deveRetornar200BuscarContatoPorId() throws Exception {
        var contatoUuid = "8331daf7-75c1-49d1-8807-e12842022916";
        var profissionalUuid = "550e8400-e29b-41d4-a716-446655440000";
        var contatoRecord = ObjectBuilder.criarContatoRecord(contatoUuid, profissionalUuid);
        when(contatoService.buscarPorId(contatoUuid)).thenReturn(contatoRecord);
        mockMvc.perform(get(PATH.concat("/550e8400-e29b-41d4-a716-446655440000"))).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200BuscarContatoPorParams() throws Exception {
when(contatoService.buscarPorParam(any(),any())).thenReturn(ObjectBuilder.criarContatoRecord("8331daf7-75c1-49d1-8807-e12842022916", "550e8400-e29b-41d4-a716-446655440000"));
        mockMvc.perform(get(PATH).param("q", "nome", "contato").param("fields", "nome", "cargo")).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200CadastrarContatoComSucesso() throws Exception {
        String requestBody = """
                {
                        "profissionalId" : "550e8400-e29b-41d4-a716-446655440000",
                        "nome" : "Celular",
                        "contato":"11963636464"              
                }
                """;
        when(contatoService.cadastrar(any())).thenReturn(new RespostaContatoRecord(UUID.fromString("550e8400-e29b-41d4-a716-446655440000")));
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200AtualizarContatoComSucesso() throws Exception {
        String requestBody = """
                {
                        "profissionalId" : "550e8400-e29b-41d4-a716-446655440000",
                        "nome" : "Fixo",
                        "contato":"11963636464"              
                }
                """;
        when(contatoService.atualizar(any(), any())).thenReturn(new RespostaContatoRecord(UUID.fromString("550e8400-e29b-41d4-a716-446655440000")));
        mockMvc.perform(put(PATH.concat("/550e8400-e29b-41d4-a716-446655440000")).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200DeletarContatoComSucesso() throws Exception {
        ContatoServiceImpl cont = mock(ContatoServiceImpl.class);
        doNothing().when(cont).deletar(any());
        mockMvc.perform(delete(PATH.concat("/550e8400-e29b-41d4-a716-446655440000"))).andExpect(status().isOk());

    }

}