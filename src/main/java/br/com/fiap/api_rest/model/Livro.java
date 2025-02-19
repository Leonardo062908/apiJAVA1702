package br.com.fiap.api_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "livros") // Define explicitamente o nome da tabela no banco
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 254)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String autor;

    @Column(nullable = false)
    private int preco;

    @ManyToOne(fetch = FetchType.LAZY) // 🔹 Melhor prática para evitar queries desnecessárias
    @JoinColumn(name = "categoria_id", nullable = false) // Define a chave estrangeira no banco
    @JsonIgnore // 🔹 Evita loop infinito na serialização JSON
    private Categoria categoria;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
