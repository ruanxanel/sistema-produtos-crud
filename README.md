# Sistema de Produtos - CRUD

Sistema de gerenciamento de produtos em **Java** executado no terminal, com persistência em banco de dados **MySQL** utilizando **JDBC**.

O projeto tem como objetivo praticar o CRUD completo e o padrão **DAO** (Data Access Object).

---

## Sobre o projeto

O sistema possui dois perfis de acesso:

- **Administrador**: pode inserir, atualizar, deletar, listar e buscar produtos
- **Usuário**: pode apenas listar e buscar produtos

Cada produto possui as seguintes informações:

- ID (gerado pelo banco)
- Nome
- Preço
- Estoque

O projeto foi desenvolvido como parte dos meus estudos em **Java e banco de dados**.

---

## Tecnologias utilizadas

- Java
- JDBC
- MySQL
- MySQL Connector/J 8.2.0
- Padrão DAO

---

## Estrutura do projeto

```
├── libs
│   └── mysql-connector-j-8.2.0.jar
└── src
    ├── Main.java
    ├── Menu.java
    ├── Produto.java
    └── ProdutoDAO.java
```

### Main

Ponto de entrada da aplicação. Exibe o menu de login em loop.

### Menu

Responsável pela interface no terminal, pelo login e pelas opções de cada perfil.

### Produto

Classe que representa o produto, com atributos privados, getters e setters.

### ProdutoDAO

Responsável pela comunicação com o banco de dados, executando as operações SQL com `PreparedStatement`.

---

## Funcionalidades

### Perfil Administrador

```
1   - Inserir produto
2   - Atualizar produto
3   - Deletar produto
4   - Listar todos os produtos
5   - Buscar produto por ID
100 - Deletar todos os dados da tabela
0   - Voltar
```

A opção **100** apaga todos os produtos da tabela. Por segurança, exige a digitação de `CONFIRMAR` e uma nova autenticação do administrador.

### Perfil Usuário

```
1 - Listar todos os produtos
2 - Buscar produto por ID
0 - Voltar
```

---

## Banco de dados

O projeto utiliza **MySQL** e uma tabela chamada `produtos`.

Exemplo de criação da tabela:

```sql
CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT NOT NULL
);
```

### Configuração da conexão

As credenciais do banco são lidas de um arquivo `config.properties`, que **não é versionado** (está no `.gitignore`) para evitar a exposição de dados sensíveis.

Crie o arquivo em `src/resources/config.properties`, de modo que fique disponível no classpath:

```properties
db.url=jdbc:mysql://localhost:3306/nome_do_banco
db.usuario=seu_usuario
db.senha=sua_senha
```

---

## Como executar

### Pré-requisitos

- JDK instalado
- MySQL instalado e em execução
- Banco de dados e tabela `produtos` criados
- Arquivo `config.properties` configurado

### Passos

```bash
# Clonar o repositório
git clone https://github.com/ruanxanel/sistema-produtos-crud.git

# Entrar na pasta do projeto
cd sistema-produtos-crud

# Compilar
javac -cp "libs/*" -d out src/*.java

# Executar (Linux/macOS)
java -cp "out:libs/*:src/resources" Main

# Executar (Windows)
java -cp "out;libs/*;src/resources" Main
```

Também é possível abrir o projeto em uma IDE como o IntelliJ IDEA, adicionar o `.jar` da pasta `libs` como biblioteca e executar a classe `Main`.

---

## Conceitos praticados

Durante o desenvolvimento deste projeto foram praticados conceitos como:

- Java
- Programação Orientada a Objetos
- CRUD
- JDBC
- Padrão DAO
- `PreparedStatement` (prevenção de SQL Injection)
- `try-with-resources`
- MySQL
- Leitura de configurações com `Properties`
- Separação de responsabilidades (interface, modelo e acesso a dados)

---

## Objetivo

Este projeto faz parte da minha jornada de aprendizado em **desenvolvimento Back-End com Java**.

O objetivo é aplicar na prática os conceitos estudados e construir projetos para meu portfólio.

---

## Próximos passos

- [ ] Guardar as credenciais de administrador fora do código
- [ ] Tratar produto não encontrado na atualização
- [ ] Validar entradas do usuário (letras no lugar de números)
- [ ] Criar uma camada Service
- [ ] Criar testes automatizados
- [ ] Migrar para Spring Boot (veja o projeto [sistema-produto-api](https://github.com/ruanxanel/sistema-produto-api))

---

## Autor

**Ruan Henrique**

Estudante de Ciência da Computação, focado em desenvolvimento Back-End com Java e Spring Boot.