package br.com.ApiLivros.domain.Autor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public record DadosCadastroAutor(
        @NotBlank(message = "Nome não pode estar em branco") String nomeAutor,
        @Min(value = 10, message = "Idade inválida") @NotNull(message = "Idade não pode ser nula") @Max(value = 120, message = "Idade inválida") Integer idade,
        @NotBlank(message = "Nacionalidade não pode estar em branco") String nacionalidade) {
}
