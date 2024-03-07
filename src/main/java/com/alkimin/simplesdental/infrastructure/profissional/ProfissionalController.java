package com.alkimin.simplesdental.infrastructure.profissional;

import com.alkimin.simplesdental.application.profissional.service.ProfissionalService;
import com.alkimin.simplesdental.infrastructure.profissional.dto.AtualizarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.CriarProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.ProfissionalRecord;
import com.alkimin.simplesdental.infrastructure.profissional.dto.RespostaProfissionalRecord;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profissionais")
public class ProfissionalController {

    private ProfissionalService profissionalService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalRecord> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(profissionalService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RespostaProfissionalRecord> cadastrar(@RequestBody CriarProfissionalRecord criarProfissionalRecord) {
        return ResponseEntity.ok(profissionalService.cadastrar(criarProfissionalRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaProfissionalRecord> atualizar(@PathVariable String id, @RequestBody AtualizarProfissionalRecord atualizarProfissionalRecord) {
        return ResponseEntity.ok(profissionalService.atualizar(id, atualizarProfissionalRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable String id) {
        profissionalService.deletar(id);
        return ResponseEntity.ok().body("Profissional excluído com sucesso.");
    }
}
