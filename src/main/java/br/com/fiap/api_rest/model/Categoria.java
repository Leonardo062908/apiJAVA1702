<<<<<<< HEAD
package br.com.fiap.api_rest.model;public class Categoria {
}
=======
package br.com.fiap.api_rest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
>>>>>>> 9ebb697 (🚀 Implementação da API REST para gerenciamento de livros)
