package br.com.ApiLivros.domain.Autor;

import java.util.UUID;

public record DadosAutorPraListarComLivro(UUID idAutor, String nomeAutor) {
    public DadosAutorPraListarComLivro(Autor autor){

        this(autor.getIdAutor(), autor.getNomeAutor());
    }
}
