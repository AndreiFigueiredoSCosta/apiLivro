package br.com.ApiLivros.domain.Livro;

import br.com.ApiLivros.domain.Autor.DadosAutorPraListarComLivro;

import java.util.UUID;

public record DadosLIstagemLivrosComAutores(
        UUID idLivro,
        String nomeLivro,
        Integer paginas,
        String genero,
        DadosAutorPraListarComLivro autor) {
    public DadosLIstagemLivrosComAutores(Livro livro){
        this(
                livro.getIdLivro(),
                livro.getNomeLivro(),
                livro.getPaginas(),
                livro.getGenero(),
                new DadosAutorPraListarComLivro(livro.getAutor())
        );
    }
}
