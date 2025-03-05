package br.com.ApiLivros.infra.repository;

import br.com.ApiLivros.domain.Autor.Autor;
import br.com.ApiLivros.domain.Livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    public Livro findLivroByIdLivro(UUID id);
    public void deleteByIdLivro(UUID id);
}
