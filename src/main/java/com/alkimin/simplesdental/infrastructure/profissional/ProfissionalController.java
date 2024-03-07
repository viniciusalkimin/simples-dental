package com.alkimin.simplesdental.infrastructure.profissional;

import com.alkimin.simplesdental.application.profissional.ProfissionalService;
import com.alkimin.simplesdental.infrastructure.profissional.dto.CriarProfissionalRecord;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/profissionais")
public class ProfissionalController {

    private ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CriarProfissionalRecord criarProfissionalRecord) {
        return ResponseEntity.ok(profissionalService.cadastrar(criarProfissionalRecord));
    }
}
