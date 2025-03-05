package br.com.ApiLivros.application.service;

import br.com.ApiLivros.domain.Autor.Autor;
import br.com.ApiLivros.domain.Livro.*;
import br.com.ApiLivros.infra.repository.AutorRepository;
import br.com.ApiLivros.infra.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LivroService {
    LivroRepository livroRepository;
    AutorRepository autorRepository;

    public LivroService(LivroRepository lr, AutorRepository ar){
        this.livroRepository = lr;
        this.autorRepository = ar;
    }

    public DadosLIstagemLivrosComAutores cadastrarLivro(DadosCadastroLivro dadosLivro){
        Autor autor = this.autorRepository.findAutorByIdAutor(dadosLivro.autor());
        if(autor == null)
            throw new EntityNotFoundException("Não existe nenhum autor de id " + dadosLivro.autor());

        Livro livro = new Livro();
        livro.setNomeLivro(dadosLivro.nomeLivro());
        livro.setGenero(dadosLivro.genero());
        livro.setPaginas(dadosLivro.paginas());
        livro.setAutor(autor);
        this.livroRepository.save(livro);
        return new DadosLIstagemLivrosComAutores(livro);
    }

    public List<DadosListagemLivros> listarLivros(){
        List<Livro> livros = this.livroRepository.findAll();
        return livros.stream().map(DadosListagemLivros::new).collect(Collectors.toList());
    }

    public List<DadosLIstagemLivrosComAutores> listarLivrosComAutores(){
        List<Livro> livros = this.livroRepository.findAll();
        return livros.stream().map(DadosLIstagemLivrosComAutores::new).collect(Collectors.toList());
    }

    public DadosLIstagemLivrosComAutores listarLivro(UUID uuid){
        Livro livro = this.livroRepository.findLivroByIdLivro(uuid);
        if(livro == null) {
            throw new EntityNotFoundException("Não existe nenhum livro de id " + uuid);
        }

        return new DadosLIstagemLivrosComAutores(livro);
    }

    public DadosLIstagemLivrosComAutores editarLivro(DadosEditarLivro dadosLivro){
        Livro livro = this.livroRepository.findLivroByIdLivro(dadosLivro.idLivro());
        if(livro == null) {
            throw new EntityNotFoundException("Não existe nenhum livro de id " + dadosLivro.autor());
        }
        if (dadosLivro.nomeLivro() != null) {
            livro.setNomeLivro(dadosLivro.nomeLivro());
        }

        if (dadosLivro.genero() != null) {
            livro.setGenero(dadosLivro.genero());
        }

        if (dadosLivro.paginas() != null) {
            livro.setPaginas(dadosLivro.paginas());
        }

        if (dadosLivro.autor() != null) {
            if(this.autorRepository.findAutorByIdAutor(dadosLivro.autor()) == null) {
                throw new EntityNotFoundException("Não existe nenhum autor de id" + dadosLivro.autor());
            }

            livro.setAutor(this.autorRepository.findAutorByIdAutor(dadosLivro.autor()));
        }
        this.livroRepository.save(livro);

        return new DadosLIstagemLivrosComAutores(livro);
    }

    @Transactional
    public void deletarLivro(UUID idLivro){
        if(this.livroRepository.findLivroByIdLivro(idLivro) == null) {
            throw new EntityNotFoundException("Não eixste nenhum livro de id " + idLivro);
        }

        this.livroRepository.deleteByIdLivro(idLivro);
    }
}
