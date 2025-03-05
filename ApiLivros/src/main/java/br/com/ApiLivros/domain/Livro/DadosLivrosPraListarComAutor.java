package br.com.ApiLivros.domain.Livro;

import java.util.UUID;

public record DadosLivrosPraListarComAutor(UUID idLivro, String nomeLivro) {
    public DadosLivrosPraListarComAutor(Livro livro){
        this(livro.getIdLivro(), livro.getNomeLivro());
    }
}
