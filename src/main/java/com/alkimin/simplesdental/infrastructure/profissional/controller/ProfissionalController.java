package com.alkimin.simplesdental.infrastructure.profissional.controller;

import com.alkimin.simplesdental.application.profissional.service.ProfissionalService;
import com.alkimin.simplesdental.infrastructure.profissional.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${application.context-path}/profissionais")
public class ProfissionalController {

    private ProfissionalService profissionalService;

    @GetMapping
    public ResponseEntity<Object> buscarPorParam(
            @RequestParam String q,
            @RequestParam(required = false) List<String> fields) {
        return ResponseEntity.ok(profissionalService.buscarPorParam(q, fields));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalRecord> buscarPorId(@PathVariable String id) {
        var resp = profissionalService.buscarPorId(id);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ProfissionalCadastradoRecord> cadastrar(@RequestBody @Valid CriarProfissionalRecord criarProfissionalRecord) {
        return ResponseEntity.ok(profissionalService.cadastrar(criarProfissionalRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalAtualizadoRecord> atualizar(@PathVariable String id, @RequestBody @Valid AtualizarProfissionalRecord atualizarProfissionalRecord) {
        return ResponseEntity.ok(profissionalService.atualizar(id, atualizarProfissionalRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable String id) {
        profissionalService.deletar(id);
        return ResponseEntity.ok().body("Profissional excluído com sucesso.");
    }
}
