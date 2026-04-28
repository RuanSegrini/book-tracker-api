## 📚 Book Tracker API

API para gerenciamento de leitura de livros, permitindo que usuários acompanhem seu progresso, avaliem obras e salvem favoritos.

## 🚧 Projeto em desenvolvimento — funcionalidades podem mudar e novas melhorias estão sendo implementadas.

## 🧠 Visão Geral

O Book Tracker API é uma aplicação backend focada no controle de leitura de usuários. Com ela, é possível:

- Cadastrar usuários
- Registrar livros
- Acompanhar progresso de leitura
- Avaliar livros
- Favoritar livros
## 🧩 Modelo de Domínio

O sistema é composto pelas seguintes entidades principais:

## 👤 User

Representa um usuário da aplicação.

# Atributos:

- id: Long
- name: String
- email: String (único)
- password: String
- createdAt: LocalDateTime
## 📖 Book

Representa um livro disponível no sistema.

# Atributos:

- id: Long
- title: String
- author: String
- totalPages: Integer
- genre: Genre
- createdAt: LocalDateTime
## 📘 Reading

Controla o progresso de leitura de um usuário em um livro.

# Atributos:

- id: Long
- currentPage: Integer
- status: ReadingStatus
- startedAt: LocalDateTime
- finishedAt: LocalDateTime
## ⭐ Review

Avaliação feita por um usuário em um livro.

# Atributos:

- id: Long
- rating: Integer (1–5)
- comment: String
- createdAt: LocalDateTime
## 💜 Favorite

Representa um livro favoritado por um usuário.

# Atributos:

- id: Long
- createdAt: LocalDateTime
## 🔗 Relacionamentos
# Um User pode ter:
- várias leituras (Reading)
- várias avaliações (Review)
- vários favoritos (Favorite)
- Um Book pode ter:
- várias leituras
- várias avaliações
- vários favoritos
## 📚 Enums
# Genre
- FICTION
- NON_FICTION
- FANTASY
- SCIENCE
- HISTORY
- BIOGRAPHY
# ReadingStatus
- NOT_STARTED
- READING
- COMPLETED
- ABANDONED
## 📏 Regras de Negócio
- Um usuário não pode avaliar o mesmo livro mais de uma vez.
- Um usuário não pode favoritar o mesmo livro mais de uma vez.
- finishedAt só pode ser definido se o status for COMPLETED.
- currentPage não pode ser maior que totalPages.
- Se currentPage == totalPages, o status pode ser marcado como COMPLETED.
- Livros não são deletados fisicamente (soft delete recomendado futuramente).
## 🚀 Funcionalidades (planejadas / em andamento)
- CRUD de usuários
- CRUD de livros
- Controle de leitura
- Sistema de avaliações
- Sistema de favoritos
- Autenticação e autorização
- Soft delete
- Paginação e filtros
## 🛠️ Tecnologias (exemplo)

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Validation
- JUnit
- Git + GitHub
- Postman
  
## ▶️ Como executar o projeto
# Clonar o repositório
git clone https://github.com/seu-usuario/book-tracker-api.git

# Entrar no diretório
cd book-tracker-api

# Rodar a aplicação
./mvnw spring-boot:run

# 📌 Observações

Este projeto está em fase inicial de desenvolvimento, então:

- A estrutura pode sofrer mudanças
- Algumas regras podem ser refinadas
- Novas features serão adicionadas
