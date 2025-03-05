package br.com.ApiLivros.domain.Autor;

import br.com.ApiLivros.domain.Livro.Livro;

import java.util.List;
import java.util.UUID;

public record DadosListagemAutores(UUID idAutor, String nomeAutor, Integer idade, String nacionalidade) {
    public DadosListagemAutores(Autor autor){
        this(
                autor.getIdAutor(),
                autor.getNomeAutor(),
                autor.getIdade(),
                autor.getNacionalidade());
    }
}
