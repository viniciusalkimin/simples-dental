package com.alkimin.simplesdental.infrastructure.contato.service.impl;

import com.alkimin.simplesdental.application.utils.ObjectBuilder;
import com.alkimin.simplesdental.infrastructure.contato.dto.AtualizarContatoRecord;
import com.alkimin.simplesdental.infrastructure.contato.dto.CriarContatoRecord;
import com.alkimin.simplesdental.infrastructure.contato.exception.ContatoNaoEncontradoException;
import com.alkimin.simplesdental.infrastructure.contato.repository.ContatoRepository;
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
class ContatoServiceImplTest {

    @Mock
    ContatoRepository contatoRepository;

    @Mock
    ProfissionalRepository profissionalRepository;

    @InjectMocks
    ContatoServiceImpl contatoService;

    @Test
    void deveBuscarContatoPorIdComSucesso(){
        var uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        var profissional = ObjectBuilder.criarProfissionalEntity(uuid.toString());
        var contato = ObjectBuilder.criarContatoEntity(profissional, uuid.toString());
        when(contatoRepository.findById(uuid)).thenReturn(Optional.ofNullable(contato));

        var profissionalBuscadoPorId = contatoService.buscarPorId(uuid.toString());

        assertEquals(uuid, contato.getId());
        verify(contatoRepository, times(1)).findById(uuid);
    }

    @Test
    void deveLancarExceptionQuandoBuscarContatoPorId() {
        var uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        assertThrows(ContatoNaoEncontradoException.class, () -> contatoService.buscarPorId(uuid.toString()));
    }

    @Test
    void deveCadastrarContatoComSucesso() {
        var profissionalUuid = UUID.fromString("8331daf7-75c1-49d1-8807-e12842022916");
        var contatoUuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        var profissional = ObjectBuilder.criarProfissionalEntity(profissionalUuid.toString());
        var contato = ObjectBuilder.criarContatoEntity(profissional, contatoUuid.toString());
        when(profissionalRepository.findById(profissionalUuid)).thenReturn(Optional.ofNullable(profissional));
        when(contatoRepository.save(any())).thenReturn(contato);

        var contatoResultado = contatoService.cadastrar(new CriarContatoRecord(profissionalUuid.toString(), "celular", "11962626363"));

        verify(profissionalRepository, times(1)).findById(profissionalUuid);
        verify(contatoRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExceptionQuandoCadastrarContato() {
        assertThrows(ProfissionalNaoEncontradoException.class, () -> contatoService.cadastrar(new CriarContatoRecord("8331daf7-75c1-49d1-8807-e12842022916", "celular", "11962626363")));
    }

    @Test
    void deveAtualizarContatoComSucesso() {
        var profissionalUuid = "8331daf7-75c1-49d1-8807-e12842022916";
        var contatoUuid = "550e8400-e29b-41d4-a716-446655440000";
        var dadosContatoAtualizar = new AtualizarContatoRecord("Fixo", "912345678");
        var profissional = ObjectBuilder.criarProfissionalEntity(profissionalUuid);
        var contatoAAtualizar = ObjectBuilder.criarContatoEntity(profissional, contatoUuid);
        when(contatoRepository.findById(UUID.fromString(contatoUuid))).thenReturn(Optional.ofNullable(contatoAAtualizar));

        var repostaContatoAtualizar = contatoService.atualizar(contatoUuid, dadosContatoAtualizar);

        assertNotNull(repostaContatoAtualizar);
        verify(contatoRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExceptionQuandoAtualizarContato() {
        var dadosContatoAtualizar = new AtualizarContatoRecord("Fixo", "912345678");
        assertThrows(ContatoNaoEncontradoException.class, () -> contatoService.atualizar("550e8400-e29b-41d4-a716-446655440000", dadosContatoAtualizar));
    }

    @Test
    void deveDeletarContatoComSucesso() {
        var profissionalUuid = "8331daf7-75c1-49d1-8807-e12842022916";
        var contatoUuid = "550e8400-e29b-41d4-a716-446655440000";
        var profissional = ObjectBuilder.criarProfissionalEntity(profissionalUuid);
        var contatoADeletar = ObjectBuilder.criarContatoEntity(profissional, contatoUuid);
        when(contatoRepository.findById(UUID.fromString(contatoUuid))).thenReturn(Optional.ofNullable(contatoADeletar));

        contatoService.deletar(contatoUuid);

        verify(contatoRepository, times(1)).delete(any());
    }

    @Test
    void deveLancarExceptionQuandoDeletarContato() {
        var contatoUuid = "550e8400-e29b-41d4-a716-446655440000";
        assertThrows(ContatoNaoEncontradoException.class, () -> contatoService.deletar(contatoUuid));
    }

    @Test
    void deveBuscarContatoPorParamExibirContatoComSucesso() {
        var q = "Fixo";
        var field1 = "contato";
        var fields = List.of(field1);
        var resultadoEsperado = "[{contato=11962626363}]";
        var profissionalUuid = "8331daf7-75c1-49d1-8807-e12842022916";
        var contatoUuid = "550e8400-e29b-41d4-a716-446655440000";
        var profissional = ObjectBuilder.criarProfissionalEntity(profissionalUuid);
        var contato = ObjectBuilder.criarContatoEntity(profissional, contatoUuid);
        when(contatoRepository.findByParam(q)).thenReturn(List.of(contato));

        var result = contatoService.buscarPorParam(q, fields);

        assertEquals(resultadoEsperado, result.toString());
        verify(contatoRepository, times(1)).findByParam(q);
    }

    @Test
    void deveBuscarContatoPorParamExibirNomeContatoComSucesso() {
        var q = "Fixo";
        var field1 = "contato";
        var field2 = "nome";
        var fields = List.of(field1,field2);
        var resultadoEsperado = "[{nome=Celular, contato=11962626363}]";
        var profissionalUuid = "8331daf7-75c1-49d1-8807-e12842022916";
        var contatoUuid = "550e8400-e29b-41d4-a716-446655440000";
        var profissional = ObjectBuilder.criarProfissionalEntity(profissionalUuid);
        var contato = ObjectBuilder.criarContatoEntity(profissional, contatoUuid);
        when(contatoRepository.findByParam(q)).thenReturn(List.of(contato));

        var result = contatoService.buscarPorParam(q, fields);

        assertEquals(resultadoEsperado, result.toString());
        verify(contatoRepository, times(1)).findByParam(q);
    }
}