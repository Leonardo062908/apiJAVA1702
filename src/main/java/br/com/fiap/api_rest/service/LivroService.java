package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.LivroRequest;
import br.com.fiap.api_rest.dto.LivroResponse;
import br.com.fiap.api_rest.model.Categoria;
import br.com.fiap.api_rest.model.Livro;
import br.com.fiap.api_rest.repository.CategoriaRepository;
import br.com.fiap.api_rest.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // ✅ Converte LivroRequest para Livro e salva no banco
    public LivroResponse salvarLivro(LivroRequest livroRequest) {
        Livro livro = requestToLivro(livroRequest);
        livro = livroRepository.save(livro);
        return livroToResponse(livro);
    }

    // ✅ Converte LivroRequest para Livro (não salva)
    public Livro requestToLivro(LivroRequest livroRequest) {
        Livro livro = new Livro();
        livro.setAutor(livroRequest.getAutor());
        livro.setTitulo(livroRequest.getTitulo());
        livro.setPreco(livroRequest.getPreco());
        livro.setIsbn(livroRequest.getIsbn());

        // Buscar Categoria pelo ID
        Optional<Categoria> categoria = categoriaRepository.findById(livroRequest.getCategoriaId());
        if (categoria.isEmpty()) {
            throw new RuntimeException("Categoria com ID " + livroRequest.getCategoriaId() + " não encontrada");
        }
        livro.setCategoria(categoria.get());

        return livro;
    }

    // ✅ Converte Livro para LivroResponse
    public LivroResponse livroToResponse(Livro livro) {
        return new LivroResponse(livro.getTitulo(), livro.getAutor(), livro.getPreco());
    }

    // ✅ Lista todos os livros no formato de resposta
    public List<LivroResponse> livrosToResponse(List<Livro> livros) {
        return livros.stream()
                .map(this::livroToResponse)
                .collect(Collectors.toList());
    }

    // ✅ Busca um livro por ID
    public LivroResponse buscarLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro com ID " + id + " não encontrado"));
        return livroToResponse(livro);
    }
}