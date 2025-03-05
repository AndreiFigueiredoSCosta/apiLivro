package br.com.ApiLivros.domain.Autor;

import br.com.ApiLivros.domain.Livro.DadosLivrosPraListarComAutor;

import java.util.List;
import java.util.UUID;

public record DadosListagemAutoresComSeusLivros(
        UUID idAutor,
        String nomeAutor,
        Integer idade,
        String nacionalidade,
        List<DadosLivrosPraListarComAutor> livros) {
    public DadosListagemAutoresComSeusLivros(Autor autor){
        this(
                autor.getIdAutor(),
                autor.getNomeAutor(),
                autor.getIdade(),
                autor.getNacionalidade(),
                autor.getLivros().stream()
                        .map(DadosLivrosPraListarComAutor::new)
                        .toList()
        );
    }
}
