package br.com.ApiLivros.domain.Livro;

import java.util.UUID;

public record DadosListagemLivros(
        UUID idLivro,
        String nomeLivro,
        String genero,
        Integer paginas) {
    public DadosListagemLivros(Livro livro){
        this(
                livro.getIdLivro(),
                livro.getNomeLivro(),
                livro.getGenero(),
                livro.getPaginas());
    }
}
