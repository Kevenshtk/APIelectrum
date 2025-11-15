# APIelectrum

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Endpoints](#endpoints)
- [Instalação](#instalação)

## Sobre o Projeto

Este projeto trata-se da API backend para um e-commerce de produtos tech. A API fornece endpoints para gerenciar produtos, usuários e favoritos, integrando-se a uma aplicação frontend em React para compor uma solução completa de e-commerce.
O banco de dados utilizado é leve (H2) para desenvolvimento/local, e a aplicação roda sobre Spring Boot. É possível ser dockerizada para facilitar deploy ou ambiente de desenvolvimento.

## Tecnologias Utilizadas

- Java
- Spring Boot
- H2 Database
- Docker

## Funcionalidades

A API oferece as seguintes funcionalidades principais:

- CRUD (Create, Read, Update, Delete) de produtos — permitir listar, criar, editar e remover produtos.

- CRUD de usuários — permitir cadastrar usuários, listar, editar e remover usuários.

- Gerenciamento de Favoritos — permitir que usuários adicionem ou removam produtos aos seus favoritos, bem como listar favoritos.

### Funcionalidades em Desenvolvimento

- Adição/removal de produtos no carrinho de compras

## Endpoints
- Usuários
   ```terminal
   /rest-electrum/users

- Produtos
   ```terminal
   /rest-electrum/products

- Favoritos
   ```terminal
   /rest-electrum/favorites
