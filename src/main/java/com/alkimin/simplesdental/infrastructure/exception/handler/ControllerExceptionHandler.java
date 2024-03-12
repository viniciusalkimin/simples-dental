package com.alkimin.simplesdental.infrastructure.exception.handler;

import com.alkimin.simplesdental.infrastructure.contato.exception.ContatoNaoEncontradoException;
import com.alkimin.simplesdental.infrastructure.profissional.exception.ProfissionalNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(ContatoNaoEncontradoException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> contatoNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos nenhum contato em nossa base com o ID informado.");
    }

    @ExceptionHandler(ProfissionalNaoEncontradoException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> profissionalNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos nenhum profissional em nossa base com o ID informado.");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> illegalArgumentException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A requisição contém atributos inválidos.");
    }

}
