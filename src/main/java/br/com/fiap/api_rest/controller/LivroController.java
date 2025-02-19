package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.LivroRequest;
import br.com.fiap.api_rest.dto.LivroResponse;
import br.com.fiap.api_rest.model.Livro;
import br.com.fiap.api_rest.repository.LivroRepository;
import br.com.fiap.api_rest.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;

    // Criar Livro
    @PostMapping
    public ResponseEntity<Livro> createLivro(@Valid @RequestBody LivroRequest livroRequest) {
        Livro livroConvertido = livroService.requestToLivro(livroRequest);
        Livro livroSalvo = livroRepository.save(livroConvertido); // 🔹 Corrigido possível erro de tipo
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    // Buscar todos os livros
    @GetMapping
    public ResponseEntity<List<LivroResponse>> readLivros() {
        List<Livro> livros = livroRepository.findAll(); // 🔹 Certifique-se de que o método retorna List<Livro>
        List<LivroResponse> listaLivros = livros.stream()
                .map(livroService::livroToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaLivros, HttpStatus.OK);
    }

    // Buscar um livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> readLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livroService.livroToResponse(livro.get()), HttpStatus.OK);
    }

    // Atualizar livro por ID
    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody LivroRequest livroRequest) {
        Optional<Livro> livroExistente = livroRepository.findById(id);
        if (livroExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Livro livroConvertido = livroService.requestToLivro(livroRequest);
        livroConvertido.setId(livroExistente.get().getId()); // 🔹 Mantém o ID original

        Livro livroSalvo = livroRepository.save(livroConvertido);
        return new ResponseEntity<>(livroSalvo, HttpStatus.OK);
    }

    // Deletar livro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
