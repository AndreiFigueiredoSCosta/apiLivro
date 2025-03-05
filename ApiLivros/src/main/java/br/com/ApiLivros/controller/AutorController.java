package br.com.ApiLivros.controller;

import br.com.ApiLivros.application.service.AutorService;
import br.com.ApiLivros.domain.Autor.*;
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
@RequestMapping("/autor")
@Tag(name = "Autor", description = "Path relacionado as operações com autores")
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService s){
        this.service=s;
    }

    @GetMapping("/listar-autores")
    @Operation(summary = "Listar todos os autores que estão cadastrados",
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "lista de autores",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Autor.class)
                    )
            )
        }
    )
    public List<DadosListagemAutores> listarAutores(){
        return this.service.listarAutores();
    }

    @GetMapping("/listar-autores-com-livros")
    @Operation(summary = "Listar todos os autores que estão cadastrados junto com o seus livros",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "lista de autores com livros",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Autor.class)
                            )
                    )
            }
    )
    public List<DadosListagemAutoresComSeusLivros> listarAutoresComLivros(){
        return this.service.listarAutoresComSeusLivros();
    }

    @GetMapping("/listar-autor/{uuid}")
    @Operation(summary = "Listar informações de um autor específico",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista do autor e seus livros",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Autor.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public DadosListagemAutoresComSeusLivros listarAutor(@PathVariable UUID uuid){
        return this.service.listarAutor(uuid);
    }

    @PostMapping("/cadastrar-autor")
    @Operation(summary = "Cadastrar um novo autor",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Autor cadastrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Autor.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400",
                            description = "Dados inválidos fornecidos para o cadastro",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<DadosListagemAutores> cadastrarAutor(@RequestBody @Valid DadosCadastroAutor dto , UriComponentsBuilder uriBuilder){
        DadosListagemAutores autorCriado = this.service.cadastrarAutor(dto);
        URI uri = uriBuilder.path("autor/listar-autor/{uuid}").buildAndExpand(autorCriado.idAutor()).toUri();
        return ResponseEntity.created(uri).body(autorCriado);
    }

    @PutMapping("/editar-autor")
    @Operation(summary = "Editar dados de um autor",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Auto editado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Autor.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400",
                            description = "Dados inválidos fornecidos",
                            content = @Content(mediaType = "application/json"
                            )
                    )
            }
    )
    public ResponseEntity<DadosListagemAutores> editarAutor(@RequestBody @Valid DadosEditarAutor dadosAutor){
        DadosListagemAutores autor = this.service.editarAutor(dadosAutor);
        return ResponseEntity.ok(autor);
    }

    @Operation(summary = "Deletar um produto por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Produto deletado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json"
                            )
                    )
            }
    )
    @DeleteMapping("/deletar-autor/{uuid}")
    public ResponseEntity<String> deletarAutor(@PathVariable UUID uuid){
        this.service.deletarAutor(uuid);
        return ResponseEntity.ok("Autor de id " + uuid + " deletado com sucesso!");
    }
}
