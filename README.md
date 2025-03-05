# ApiLivro

A ApiLivro é uma API REST desenvolvida como parte de um projeto pessoal de estudos focado em Java, Spring e Docker. O projeto visa aprofundar o conhecimento na construção de APIs, utilizando boas práticas e ferramentas modernas.
Caso queira acessar a documentação através do springDoc, basta rodar o projeto e acessar o link http://localhost:8080/swagger-ui/index.html

# Entidades

A API é baseada em duas entidades principais:

Livro: Representa um livro com título, descrição e outros atributos.
Autor: Representa um autor, que pode ter um ou mais livros associados.
Relacionamento entre as entidades
Um autor pode ter vários livros.
Um livro pode ter vários autores.
Apesar de sua simplicidade em termos de regras de negócios e complexidade, a aplicação é completa, envolvendo operações CRUD para gerenciamento dos livros e autores, além de outras funcionalidades.

# Tecnologias Utilizadas

Java 17+
Spring Boot
Docker

# Funcionalidades

Cadastro de livros e autores.
Consultas e listagem de livros e autores.
Atualização e exclusão de livros e autores.
Relacionamento entre livros e autores.

# Como Rodar

    1.Clone o repositório:

        git clone https://github.com/SEU_USUARIO/ApiLivro.git

    2.Navegue até o diretório do projeto:

        cd ApiLivro

    3.Execute o projeto com Maven:

        ./mvnw spring-boot:run


    Ou, caso prefira usar Docker:

        1.Construa a imagem Docker:

            docker build -t apilivro .

        2.Execute o container Docker:

            docker run -p 8080:8080 apilivro


    A API estará acessível em http://localhost:8080.

    Qualquer sugestão, dica ou conselho a respeito será muito bem vindo :D

    andreifscosta@gmail.com
