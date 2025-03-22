package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.AutorRequest;
import br.com.fiap.api_rest.dto.AutorResponse;
import br.com.fiap.api_rest.model.Autor;
import br.com.fiap.api_rest.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@Tag(name = "api-autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Operation(summary = "Cadastra um novo autor")
    @PostMapping
    public ResponseEntity<Autor> createAutor(@RequestBody @Valid AutorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvar(request));
    }

    @Operation(summary = "Lista todos os autores")
    @GetMapping
    public ResponseEntity<List<AutorResponse>> getAllAutores() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @Operation(summary = "Retorna um autor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> getAutorById(@PathVariable Long id) {
        return autorService.buscarPorIdRaw(id)
                .map(autor -> ResponseEntity.ok(autorService.autorToResponse(autor)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
