package com.alkimin.simplesdental.infrastructure.profissional;

import com.alkimin.simplesdental.application.profissional.service.ProfissionalService;


import com.alkimin.simplesdental.application.utils.ObjectBuilder;
import com.alkimin.simplesdental.infrastructure.profissional.dto.RespostaProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.service.impl.ProfissionalServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.http.HttpRequest;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfissionalControllerTest {

    private final String PATH = "/profissionais";
    @MockBean
    private ProfissionalService profissionalService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void deveRetornar200BuscarProfissionalPorId() throws Exception {
        when(profissionalService.buscarPorId(any())).thenReturn(ObjectBuilder.criarProfissionalRecord());
        mockMvc.perform(get(PATH.concat("/43e2d264-7f4c-11ee-b962-0242ac120002"))).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200BuscarProfissionalPorParams() throws Exception {
        when(profissionalService.buscarPorParam(any(),any())).thenReturn(ObjectBuilder.criarProfissionalRecord());
        mockMvc.perform(get(PATH).param("q", "DE").param("fields", "nome", "cargo")).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200CadastrarProfissionalComSucesso() throws Exception {
        String requestBody = """
                {
                        "nome" : "Vinicius Alkimin",
                        "cargo":"DESIGNER",
                        "nascimento": "1995-11-16"                
                }
                """;
        when(profissionalService.cadastrar(any())).thenReturn(new RespostaProfissionalRecord(UUID.fromString("43e2d264-7f4c-11ee-b962-0242ac120002")));
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200AtualizarProfissionalComSucesso() throws Exception {
        String requestBody = """
                {
                        "nome" : "Vinicius Alkimin",
                        "cargo":"DESIGNER",
                        "nascimento": "1995-11-16"                
                }
                """;
        when(profissionalService.atualizar(any(), any())).thenReturn(new RespostaProfissionalRecord(UUID.fromString("43e2d264-7f4c-11ee-b962-0242ac120002")));
        mockMvc.perform(put(PATH.concat("/43e2d264-7f4c-11ee-b962-0242ac120002")).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void deveRetornar200DeletarProfissionalComSucesso() throws Exception {
        ProfissionalServiceImpl prof = mock(ProfissionalServiceImpl.class);
        doNothing().when(prof).deletar(any());
        mockMvc.perform(delete(PATH.concat("/43e2d264-7f4c-11ee-b962-0242ac120002"))).andExpect(status().isOk());

    }

}