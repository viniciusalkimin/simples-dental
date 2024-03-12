package com.alkimin.simplesdental.infrastructure.contato.controller;

import com.alkimin.simplesdental.application.contato.service.ContatoService;
import com.alkimin.simplesdental.infrastructure.contato.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("${application.context-path}/contatos")
public class ContatoController {

    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<Object> buscarPorParam(
        @RequestParam String q,
        @RequestParam(required = false) List<String> fields) {
        return ResponseEntity.ok(contatoService.buscarPorParam(q, fields));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoRecord> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(contatoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContatoCadastradoRecord> cadastrar(@RequestBody CriarContatoRecord criarContatoRecord) {
        return ResponseEntity.ok(contatoService.cadastrar(criarContatoRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoAtualizadoRecord> atualizar(@PathVariable String id, @RequestBody AtualizarContatoRecord atualizarContatoRecord) {
        return ResponseEntity.ok(contatoService.atualizar(id, atualizarContatoRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable String id) {
        contatoService.deletar(id);
        return ResponseEntity.ok().body("Contato excluído com sucesso!");
    }
}
