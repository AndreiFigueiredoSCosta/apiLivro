package br.com.ApiLivros.domain.Livro;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosCadastroLivro(
        @NotBlank(message = "nomeLivro não pode estar em branco") String nomeLivro,
        @NotBlank(message = "Genero não pode estar em branco") String genero,
        @Min(value = 1, message = "Número de paginas inválida") Integer paginas,
        @NotNull(message = "autor não pode ser nulo") UUID autor) {
}
