package br.com.fiap.api_rest.repository;

import br.com.fiap.api_rest.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);
    List<Livro> findTop3ByAutorAndDataLancamentoGreaterThan(Date dataLancamento);
    List<Livro> findTop3ByPreco (int preco);
    List<Livro> findDistinctByAutor (String autor);
    List<Livro> findFirstByDataLancamento(Date dataLancamento);
    List<Livro> findByAutorIs(String autor);
    List<Livro> findByTituloEqualsIgnoreCase(String titulo);
    List<Livro> findByTituloIsNot(String categoria);
    List<Livro> findByTituloIsNull(String titulo);
    List<Livro> findByTituloIsNotNull(String titulo);
    List<Livro> findByEbookTrue(boolean Ebook);
    List<Livro> findByEbookFalse(boolean Ebook);
    List<Livro> findByTituloStartingWith(String prefix);
    List<Livro> findByTituloEndingWith(String suffix);
    List<Livro> findByTituloContaining(String infix);
    List<Livro> findByTituloLike(String likePattern);
    List<Livro> findByPrecoLessThan(int preco);
    List<Livro> findByPrecoLessThanEqual(int preco);
    List<Livro> findByPrecoGreaterThan(int preco);
    List<Livro> findByPrecoGreaterThanEqual(int preco);
    List<Livro> findByPrecoBetween(double min, double max);
    List<Livro> findByTituloIn(Collection<String> livros);
    List<Livro> findByDataLancamentoBefore(Date dataLancamento);
    List<Livro> findByDataLancamentoAfter(Date dataLancamento);
    List<Livro> findByDataLancamentoBetween(Date dataLancamentoInicial, Date dataLancamentoFinal);
    List<Livro> findByPrecoOrAutor(int preco, String Autor);
    List<Livro> findByPrecoOrAutorAndCategoria(int preco, String autor, String Categoria);
    List<Livro> findByPrecoOrderByPreco(int preco);
    List<Livro> findByPrecoOrderByPrecoDesc(int preco);

}