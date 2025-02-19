package br.com.fiap.api_rest.dto;

import jakarta.validation.constraints.*;

public class LivroRequest {

    @NotBlank(message = "O título não pode ser nulo ou vazio")
    @Size(min = 3, max = 254, message = "O título deve ter entre 3 e 254 caracteres")
    private String titulo;

    @NotBlank(message = "O nome do autor não pode ser nulo ou vazio")
    @Size(min = 3, max = 150, message = "O nome do autor deve ter entre 3 e 150 caracteres")
    private String autor;

    @Min(value = 1, message = "O preço deve ser no mínimo 1")
    @Max(value = 99, message = "O preço deve ser no máximo 99")
    private int preco;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId; // Agora recebe apenas o ID da categoria

    @Pattern(regexp = "^970\\d{10}$|^970\\d{7}$", message = "O ISBN deve ter 10 OU 13 dígitos e iniciar por 970")
    private String isbn;

    // Construtor vazio necessário para serialização do JSON para Java
    public LivroRequest() {}

    // Construtor completo para facilitar a criação de objetos
    public LivroRequest(String titulo, String autor, int preco, Long categoriaId, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.categoriaId = categoriaId;
        this.isbn = isbn;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}