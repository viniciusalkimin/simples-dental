package com.alkimin.simplesdental.infrastructure.profissional.service.impl;

import com.alkimin.simplesdental.application.utils.ObjectBuilder;
import com.alkimin.simplesdental.infrastructure.profissional.exception.ProfissionalNaoEncontradoException;
import com.alkimin.simplesdental.infrastructure.profissional.repository.ProfissionalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfissionalServiceImplTest {

    @Mock
    ProfissionalRepository profissionalRepository;

    @InjectMocks
    ProfissionalServiceImpl profissionalService;

    @Test
    void deveBuscarProfissionalPorIdComSucesso() {
        var uuid = UUID.fromString("8331daf7-75c1-49d1-8807-e12842022916");
        when(profissionalRepository.findById(uuid)).thenReturn(Optional.ofNullable(ObjectBuilder.criarProfissionalEntity("8331daf7-75c1-49d1-8807-e12842022916")));
        var profissional = profissionalService.buscarPorId(uuid.toString());
        assertEquals(uuid, profissional.id());
    }

    @Test
    void deveLancarExceptionQuandoBuscarProfissionalPorId() {
        assertThrows(ProfissionalNaoEncontradoException.class, () -> profissionalService.buscarPorId("8331daf7-75c1-49d1-8807-e12842022916"));
    }

    @Test
    void deveCadastrarProfissionalComSucesso() {
        var profissionalEntity = ObjectBuilder.criarProfissionalEntity("43e2d264-7f4c-11ee-b962-0242ac120002");
        when(profissionalRepository.save(any())).thenReturn(profissionalEntity);
        var profissionalASalvar = ObjectBuilder.criarCriarProfissionalRecord();
        var profissionalCadastrado = profissionalService.cadastrar(profissionalASalvar);
        assertNotNull(profissionalCadastrado.profissionalId());
        verify(profissionalRepository, times(1)).save(any());
    }

    @Test
    void deveAtualizarProfissionalComSucesso() {
        var uuid = UUID.fromString("8331daf7-75c1-49d1-8807-e12842022916");
        var dadosAtualizar = ObjectBuilder.criarAtualizarProfissionalRecord();
        var profissionalEntity = ObjectBuilder.criarProfissionalEntity(uuid.toString());
        when(profissionalRepository.findById(any())).thenReturn(Optional.of(profissionalEntity));
        when(profissionalRepository.save(any())).thenReturn(profissionalEntity);
        var respostaProfissionalAtualizado = profissionalService.atualizar(uuid.toString(), dadosAtualizar);
        verify(profissionalRepository, times(1)).findById(uuid);
        verify(profissionalRepository, times(1)).save(profissionalEntity);
        assertEquals(uuid, respostaProfissionalAtualizado.profissionalId());
    }

    @Test
    void deveLancarExceptionQuandoAtualizarProfissional() {
        var uuid = UUID.fromString("8331daf7-75c1-49d1-8807-e12842022916");
        var dadosAtualizar = ObjectBuilder.criarAtualizarProfissionalRecord();
        assertThrows(ProfissionalNaoEncontradoException.class, () -> profissionalService.atualizar(uuid.toString(), dadosAtualizar));
    }

    @Test
    void deveDeletarUmUsuarioComSucesso() {
        var uuid = "8331daf7-75c1-49d1-8807-e12842022916";
        var profissionalEntity = ObjectBuilder.criarProfissionalEntity(uuid);
        when(profissionalRepository.findById(any())).thenReturn(Optional.of(profissionalEntity));
        profissionalService.deletar(uuid);
        verify(profissionalRepository, times(1)).findById(UUID.fromString(uuid));
        verify(profissionalRepository, times(1)).save(profissionalEntity);
    }

    @Test
    void deveLancarExceptionQuandoDeletarProfissional(){
        assertThrows(ProfissionalNaoEncontradoException.class, () -> profissionalService.deletar("8331daf7-75c1-49d1-8807-e12842022916"));
    }

    @Test
    void deveBuscarPorParamComSucesso() {
        var uuid = "8331daf7-75c1-49d1-8807-e12842022916";
        var nome = "Vinicius";
        var profissionalEntity = ObjectBuilder.criarProfissionalEntity(uuid);
        when(profissionalRepository.findByParam(nome)).thenReturn(List.of(profissionalEntity));
        profissionalService.buscarPorParam(nome, any());
        verify(profissionalRepository, times(1)).findByParam(nome);
    }

}