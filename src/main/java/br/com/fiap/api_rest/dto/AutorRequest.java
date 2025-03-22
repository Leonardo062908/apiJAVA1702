package br.com.fiap.api_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutorRequest {

    @NotBlank(message = "O nome do autor não pode ser vazio")
    @Size(min = 3, max = 150, message = "O nome do autor deve ter entre 3 e 150 caracteres")
    private String nome;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
