package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.AutorRequest;
import br.com.fiap.api_rest.dto.AutorResponse;
import br.com.fiap.api_rest.model.Autor;
import br.com.fiap.api_rest.model.Livro;
import br.com.fiap.api_rest.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.com.fiap.api_rest.controller.AutorController;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // ✅ CREATE
    public Autor salvar(AutorRequest request) {
        Autor autor = new Autor();
        autor.setNome(request.getNome());
        return autorRepository.save(autor);
    }

    // ✅ READ (Todos os autores)
    public List<AutorResponse> listarTodos() {
        return autorRepository.findAll()
                .stream()
                .map(this::autorToResponse)
                .collect(Collectors.toList());
    }

    // ✅ READ (Autor por ID - retornando DTO)
    //Commit teste
    public AutorResponse buscarPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        return autorToResponse(autor);
    }

    // ✅ READ (Autor por ID - retornando o objeto bruto)
    public Optional<Autor> buscarPorIdRaw(Long id) {
        return autorRepository.findById(id);
    }

    // ✅ CONVERSÃO: Autor → AutorResponse
    public AutorResponse autorToResponse(Autor autor) {
        List<String> livros = autor.getLivros() != null
                ? autor.getLivros().stream().map(Livro::getTitulo).collect(Collectors.toList())
                : List.of();

        AutorResponse response = new AutorResponse(autor.getId(), autor.getNome(), livros);

        // Opcional: Adicionar link HATEOAS
        Link selfLink = linkTo(methodOn(AutorController.class).getAutorById(autor.getId())).withSelfRel();
        response.setLink(selfLink);

        return response;
    }

    // ✅ UPDATE
    public Autor atualizar(Long id, AutorRequest request) {
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        autorExistente.setNome(request.getNome());

        return autorRepository.save(autorExistente);
    }

    // ✅ DELETE
    public void deletar(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        autorRepository.deleteById(id);
    }
}
