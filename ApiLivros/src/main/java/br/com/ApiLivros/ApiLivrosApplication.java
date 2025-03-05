package br.com.ApiLivros;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "apiLivros",
			version = "1.0",
			description = "Uma api quem consiste basicamente nas entidades de Livros e autores, um " +
					"pequeno projeto de estudos.",
			contact = @Contact(
					name = "Andrei Figueiredo da silva Costa",
					email = "andreifscosta@gmail.com"
			)
		)
)
public class ApiLivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLivrosApplication.class, args);
	}

}
