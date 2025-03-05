package br.com.ApiLivros.domain.Autor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosEditarAutor(@NotNull(message = "idAutor não pode ser nulo") UUID idAutor,
                               @NotBlank(message = "nomeAutor não pode estar em branco") String nomeAutor,
                               @Min(value = 10, message = "Idade inválida") @Max(value = 120, message = "Idade inválida") Integer idade,
                               @NotBlank(message = "nacionalidade não pode estar em branco") String nacionalidade) {
    public DadosEditarAutor(Autor autor){
        this(autor.getIdAutor(),
                autor.getNomeAutor(),
                autor.getIdade(),
                autor.getNacionalidade());
    }
}
