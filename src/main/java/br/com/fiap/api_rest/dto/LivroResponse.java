package br.com.fiap.api_rest.dto;

public class LivroResponse {
    private String titulo;
    private String autor;
    private int preco;

    // ✅ Adicione este construtor para aceitar três parâmetros
    public LivroResponse(String titulo, String autor, int preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
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
}
