
CREATE TABLE autor (
                       id_autor UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       nome_autor VARCHAR(255) NOT NULL,
                       idade INTEGER NOT NULL,
                       nacionalidade VARCHAR(255) NOT NULL
);


CREATE TABLE livro (
                       id_livro UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       nome_livro VARCHAR(255) NOT NULL,
                       genero VARCHAR(255) NOT NULL,
                       paginas INTEGER NOT NULL,
                       id_autor UUID NOT NULL,
                       FOREIGN KEY (id_autor) REFERENCES autor(id_autor) ON DELETE CASCADE
);
