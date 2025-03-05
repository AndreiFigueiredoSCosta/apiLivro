package br.com.ApiLivros.infra.repository;

import br.com.ApiLivros.domain.Autor.Autor;
import br.com.ApiLivros.domain.Livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
   public Autor findAutorByIdAutor(UUID id);
    public void deleteByIdAutor(UUID id);
}
