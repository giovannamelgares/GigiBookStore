CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS livro (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    paginas INTEGER NOT NULL,
    imagem TEXT,
    sinopse TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS perfil (
    usuarioid UUID NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    CONSTRAINT fk_usuario
        FOREIGN KEY(usuarioid) REFERENCES usuario(id)
);

ALTER TABLE perfil
ADD COLUMN IF NOT EXISTS id serial PRIMARY KEY;

ALTER TABLE perfil DROP CONSTRAINT IF EXISTS perfil_unique;

ALTER TABLE perfil
ADD CONSTRAINT perfil_unique UNIQUE (usuarioid);

CREATE TABLE IF NOT EXISTS favorito (
    usuarioid UUID REFERENCES usuario(id) ON DELETE CASCADE,
    livroid UUID REFERENCES livro(id) ON DELETE CASCADE,
    data_favorito DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY(usuarioid, livroid)
);
