package br.com.ApiLivros.application.service;

import br.com.ApiLivros.domain.Autor.*;
import br.com.ApiLivros.infra.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private br.com.ApiLivros.infra.repository.AutorRepository AutorRepository;
    public AutorService(AutorRepository ar){
        this.AutorRepository =ar;
    }

    public List<DadosListagemAutores> listarAutores(){
        List<Autor> autores = this.AutorRepository.findAll();
        return autores.stream().map(DadosListagemAutores::new).collect(Collectors.toList());
    }

    public List<DadosListagemAutoresComSeusLivros> listarAutoresComSeusLivros(){
        List<Autor> autores = this.AutorRepository.findAll();
        return autores.stream().map(DadosListagemAutoresComSeusLivros::new).collect(Collectors.toList());
    }

    public DadosListagemAutores cadastrarAutor(DadosCadastroAutor dto){
        Autor autor = new Autor();
        autor.setNomeAutor(dto.nomeAutor());
        autor.setIdade(dto.idade());
        autor.setNacionalidade(dto.nacionalidade());
        this.AutorRepository.save(autor);
        return new DadosListagemAutores(autor);
    }

    public DadosListagemAutoresComSeusLivros listarAutor(UUID idAutor){
        Autor autor = this.AutorRepository.findAutorByIdAutor(idAutor);
        if(autor == null){
            throw new EntityNotFoundException("Não existe nenhum autor de id " + idAutor);
        }

        return new DadosListagemAutoresComSeusLivros(autor);
    }

    @Transactional
    public DadosListagemAutores editarAutor(DadosEditarAutor dadosAutor){
        Autor autor = this.AutorRepository.findAutorByIdAutor(dadosAutor.idAutor());
        if(autor == null){
            throw new EntityNotFoundException("Não existe nenhum autor de código " + dadosAutor.idAutor());
        }

        if(dadosAutor.nomeAutor() != null){
            autor.setNomeAutor(dadosAutor.nomeAutor());
        }

        if(dadosAutor.idade() != null){
            autor.setIdade(dadosAutor.idade());
        }

        if(dadosAutor.nacionalidade() != null){
            autor.setNacionalidade(dadosAutor.nacionalidade());
        }

        this.AutorRepository.save(autor);
        return new DadosListagemAutores(autor);
    }

    @Transactional
    public void deletarAutor(UUID idAutor){
        if(this.AutorRepository.findAutorByIdAutor(idAutor) == null){
            throw new EntityNotFoundException("Não existe nenhum autor de código " + idAutor);
        }

        this.AutorRepository.deleteByIdAutor(idAutor);
    }

}
