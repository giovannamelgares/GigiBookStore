# GigiBookStore

Projeto de livraria online desenvolvido com Java e Spring Boot.

https://gigibookstore.onrender.com

## Funcionalidades

### Usuário
- Realizar cadastro no sistema  
- Realizar login  
- Visualizar livros disponíveis  
- Favoritar livros  
- Visualizar lista de livros favoritados  

### Administrador
- Cadastrar livros  
- Editar livros  
- Excluir livros  
- Visualizar lista de usuários cadastrados  

## Regras de negócio

- O sistema possui dois tipos de usuários: administrador e cliente  
- Administradores podem cadastrar, editar e excluir livros  
- Administradores também podem visualizar a lista de usuários cadastrados  
- Clientes podem visualizar livros e favoritar livros   
- As funcionalidades disponíveis mudam de acordo com o perfil do usuário logado

## Tecnologias utilizadas

- Java  
- Spring Boot  
- Spring Security  
- Thymeleaf  
- JDBC Template  
- PostgreSQL  

## Banco de dados

O sistema utiliza PostgreSQL com as seguintes tabelas principais:

- usuario  
- livro  
- perfil  
- favorito  