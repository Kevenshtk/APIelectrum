# APIelectrum

A API Electrum é o backend responsável por fornecer os serviços necessários para o funcionamento do projeto **Electrum**, um e-commerce de produtos eletrônicos desenvolvido como projeto de portfólio.

A aplicação foi desenvolvida utilizando **Java** e **Spring Boot**, sendo responsável pelo gerenciamento de produtos, usuários, favoritos e carrinho disponibilizando uma API REST consumida pelo frontend desenvolvido em React. Para o ambiente de desenvolvimento, a persistência dos dados é realizada utilizando o banco H2, além de oferecer suporte à execução com Docker.

## 🎯 Objetivo

O objetivo do projeto foi desenvolver uma API REST aplicando os principais conceitos estudados no desenvolvimento backend, como:

- Desenvolvimento de APIs REST com Spring Boot.
- Organização da aplicação em camadas (Controller, DTO, Module e Repository).
- Persistência de dados utilizando Spring Data JPA.
- Mapeamento objeto-relacional (ORM) com Hibernate.
- Operações CRUD para gerenciamento de recursos.
- Integração entre frontend e backend.
- Conteinerização da aplicação utilizando Docker.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Docker

## 🚀 Funcionalidades

A API disponibiliza os serviços responsáveis pelo funcionamento do **Electrum**, fornecendo endpoints para gerenciamento dos recursos da aplicação.

### Produtos

- Cadastro de produtos.
- Listagem de produtos.
- Consulta por identificador.
- Atualização de informações.
- Exclusão de produtos.

### Usuários

- Cadastro de usuários.
- Listagem de usuários.
- Atualização de informações.
- Exclusão de usuários.

### Favoritos

- Adição de produtos aos favoritos.
- Remoção de produtos dos favoritos.
- Consulta da lista de favoritos de cada usuário.

### Carrinho de Compras

- Adição de produtos ao carrinho.
- Remoção de produtos do carrinho.
- Consulta dos produtos adicionados ao carrinho.

## 🏛️ Arquitetura

O projeto foi organizado seguindo uma arquitetura em camadas, separando as responsabilidades entre apresentação, regras de negócio e acesso aos dados, facilitando a manutenção e evolução da aplicação.

```
src/
├── main/
│   ├── java/APIelectrum
│   ├── controller/
│   ├── dto/
│   ├── module/
│   └── repository/
```

controller/ → Responsável pelos endpoints da API e tratamento das requisições HTTP.

dto/ → Objetos utilizados para transferência de dados entre cliente e servidor.

module/ → Contém as classes que representam as entidades da aplicação, responsáveis por modelar os dados utilizados pela API e seu mapeamento com o banco de dados.

repository/ → Responsável pelo acesso e persistência dos dados.

## 🌐 Endpoints (para APIs)
- Usuários
   ```text
   /rest-electrum/users

- Produtos
   ```text
   /rest-electrum/products

- Favoritos
   ```text
   /rest-electrum/favorites

- Carrinho de Compras
   ```text
   /rest-electrum/shopping-cart

