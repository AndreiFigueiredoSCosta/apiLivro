package br.com.ApiLivros.controller;

import br.com.ApiLivros.application.service.LivroService;
import br.com.ApiLivros.domain.Autor.Autor;
import br.com.ApiLivros.domain.Livro.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livro")
@Tag(name="Livro", description = "Path relacionado com as operações dos livros")
public class LivroController {

    private final LivroService ls;

    public LivroController(LivroService ls){
        this.ls = ls;
    }

    @PostMapping("/cadastrar-livro")
    @Operation(summary = "Cadastrar um novo livro",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Livro cadastrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Livro.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400",
                            description = "Dados inválidos fornecidos para o cadastro",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<DadosLIstagemLivrosComAutores> cadastrarLivro(@RequestBody @Valid DadosCadastroLivro dadosLivro, UriComponentsBuilder uriBuilder){
        DadosLIstagemLivrosComAutores livro = this.ls.cadastrarLivro(dadosLivro);
        URI uri = uriBuilder.path("livro/listar-livro/{uuid}").buildAndExpand(livro.idLivro()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @GetMapping("/listar-livros")
    @Operation(summary = "Listar todos os livros que estão cadastrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "lista de livros",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Livro.class)
                            )
                    )
            }
    )
    public List<DadosListagemLivros> listarLivros(){
        return  this.ls.listarLivros();
    }

    @GetMapping("/listar-livros-com-autores")
    @Operation(summary = "Listar todos os autores que estão cadastrados juntamente com o seus autores",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "lista de livros com seus autores",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Livro.class)
                            )
                    )
            }
    )
    public List<DadosLIstagemLivrosComAutores> listarLivrosComAutores(){
        return this.ls.listarLivrosComAutores();
    }

    @GetMapping("/listar-livro/{uuid}")
    @Operation(summary = "Listar informações de um Livro específico",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Informações do livro",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Livro.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Livro não encontrado",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public DadosLIstagemLivrosComAutores listarLivro(@PathVariable UUID uuid){
        return this.ls.listarLivro(uuid);
    }

    @PutMapping("/editar-livro")
    @Operation(summary = "Editar dados de um Livro",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Livro editado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Livro.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400",
                            description = "Dados inválidos fornecidos",
                            content = @Content(mediaType = "application/json"
                            )
                    )
            }
    )
    public ResponseEntity<DadosLIstagemLivrosComAutores> editarLivro(@RequestBody @Valid DadosEditarLivro livro){
        DadosLIstagemLivrosComAutores dados = this.ls.editarLivro(livro);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/deletar-livro/{uuid}")
    @Operation(summary = "Deletar um livro por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "livro deletado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "livro não encontrado",
                            content = @Content(mediaType = "application/json"
                            )
                    )
            }
    )
    public ResponseEntity<String> deletarLivro(@PathVariable UUID uuid){
        this.ls.deletarLivro(uuid);
        return ResponseEntity.ok("Livro de ID " + uuid + " deletado com sucesso");
    }
}
