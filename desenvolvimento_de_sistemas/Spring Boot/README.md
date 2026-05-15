
# 🚀 Guia Prático: Spring Boot com IntelliJ

Guia simplificado para criar e configurar projetos Spring Boot utilizando IntelliJ IDEA, focado em desenvolvimento de APIs REST com MySQL.

## 📋 Índice

- [Pré-requisitos](#-pré-requisitos)
- [Criando o Projeto](#-criando-o-projeto)
- [Configuração Inicial](#-configuração-inicial)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Criando um CRUD Completo](#-criando-um-crud-completo)
- [Testando com Postman](#-testando-com-postman)
- [Solução de Problemas](#-solução-de-problemas)

## 🛠 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 21 (LTS)** - [Download aqui](https://www.oracle.com/java/technologies/downloads/)
- **IntelliJ IDEA** - [Download aqui](https://www.jetbrains.com/idea/download/)
- **MySQL 8+** - [Download aqui](https://dev.mysql.com/downloads/)
- **MySQL Workbench** - Para gerenciar o banco de dados
- **Postman** - Para testar as APIs

### Plugin IntelliJ Necessário

1. Abra o IntelliJ
2. Vá em `File → Settings → Plugins`
3. Procure por **"Lombok"**
4. Instale e reinicie o IntelliJ

## 🎯 Criando o Projeto

### 1. Acesse o Spring Initializr

Acesse [start.spring.io](https://start.spring.io) e configure:

| Campo | Valor |
|-------|-------|
| **Project** | Maven |
| **Language** | Java |
| **Spring Boot** | 3.x.x (mais recente **SEM** SNAPSHOT) |
| **Group** | com.example |
| **Artifact** | seu-projeto |
| **Packaging** | Jar |
| **Java** | 21 |

### 2. Adicione as Dependências

Clique em "ADD DEPENDENCIES" e adicione:

- ✅ **Spring Web** - Para criar APIs REST
- ✅ **Spring Data JPA** - Para trabalhar com banco de dados
- ✅ **MySQL Driver** - Conector MySQL
- ✅ **Lombok** - Reduz código boilerplate

### 3. Gere e Baixe o Projeto

1. Clique em **GENERATE**
2. Salve o arquivo `.zip` em uma pasta organizada
3. **Descompacte** o arquivo

## 📂 Configuração Inicial

### 1. Abrir no IntelliJ

1. Abra o IntelliJ IDEA
2. `File → Open`
3. Selecione a **pasta descompactada** (que contém `pom.xml`)
4. Clique em **OK**

### 2. Carregar Dependências Maven

Quando aparecer o popup **"Maven Build Scripts Found"**:
- ✅ Clique em **"Load Maven Project"**
- ⏳ Aguarde o download das dependências (2-5 minutos na primeira vez)

> ⚠️ **Importante**: Não rode o projeto enquanto a barra de progresso estiver ativa!

### 3. Habilitar Annotation Processing

Para que o Lombok funcione corretamente:

1. `File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors`
2. Marque ✅ **"Enable annotation processing"**
3. Clique em **Apply** e **OK**

### 4. Configurar application.properties

Abra `src/main/resources/application.properties` e adicione:

```properties
# Conexão com MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=root
spring.datasource.password=sua_senha

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

> 🔧 **Lembre-se**: Altere `seu_banco` e `sua_senha` conforme sua configuração!

### 5. Criar o Banco de Dados

Abra o MySQL Workbench e execute:

```sql
CREATE DATABASE seu_banco;
```

> ℹ️ O Spring Boot criará as tabelas automaticamente quando você rodar o projeto!

## 🏗 Estrutura do Projeto

Organize seu projeto seguindo a arquitetura em camadas:

<img width="488" height="147" alt="image" src="https://github.com/user-attachments/assets/2c231461-bb6b-4939-af3e-b15ad5f0c144" />

## 💻 Criando um CRUD Completo

Vamos criar um CRUD de **Produtos** como exemplo.

### 1. Model (Entidade)

`src/main/java/com/example/seuprojeto/model/Produto.java`

```java
package com.example.seuprojeto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    private Double preco;
    private String descricao;
    private Integer quantidade;
}
```

**Anotações Lombok:**
- `@Data` → Gera getters, setters e toString
- `@NoArgsConstructor` → Construtor vazio (obrigatório para JPA)
- `@AllArgsConstructor` → Construtor com todos os campos

### 2. Repository (Acesso aos Dados)

`src/main/java/com/example/seuprojeto/repository/ProdutoRepository.java`

```java
package com.example.seuprojeto.repository;

import com.example.seuprojeto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Métodos prontos automaticamente:
    // - save() → INSERT/UPDATE
    // - findAll() → SELECT *
    // - findById() → SELECT WHERE id = ?
    // - deleteById() → DELETE WHERE id = ?
}
```

### 3. Service (Regras de Negócio)

`src/main/java/com/example/seuprojeto/service/ProdutoService.java`

```java
package com.example.seuprojeto.service;

import com.example.seuprojeto.model.Produto;
import com.example.seuprojeto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository repository;
    
    // Listar todos
    public List<Produto> listar() {
        return repository.findAll();
    }
    
    // Buscar por ID
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    
    // Criar novo
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }
    
    // Atualizar
    public Produto atualizar(Long id, Produto dados) {
        Produto produto = buscarPorId(id);
        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        produto.setDescricao(dados.getDescricao());
        produto.setQuantidade(dados.getQuantidade());
        return repository.save(produto);
    }
    
    // Deletar
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
```

### 4. Controller (Rotas da API)

`src/main/java/com/example/seuprojeto/controller/ProdutoController.java`

```java
package com.example.seuprojeto.controller;

import com.example.seuprojeto.model.Produto;
import com.example.seuprojeto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    
    private final ProdutoService service;
    
    // GET /produtos - Listar todos
    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }
    
    // GET /produtos/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    
    // POST /produtos - Criar novo
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        return ResponseEntity.ok(service.salvar(produto));
    }
    
    // PUT /produtos/{id} - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id, 
            @RequestBody Produto dados) {
        return ResponseEntity.ok(service.atualizar(id, dados));
    }
    
    // DELETE /produtos/{id} - Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
```

## ▶️ Rodando o Projeto
<img width="476" height="60" alt="image" src="https://github.com/user-attachments/assets/df80d039-12cb-440c-ab55-487c2b3fd8f5" />

✅ **Servidor rodando em**: `http://localhost:8080`

## 🧪 Testando com Postman

### Criar Collection

1. Abra o Postman
2. `Collections → + New Collection`
3. Nomeie: "API - Seu Projeto"

### Endpoints para Testar

| Método | URL | Body (JSON) | Descrição |
|--------|-----|-------------|-----------|
| `GET` | `http://localhost:8080/produtos` | - | Listar todos |
| `GET` | `http://localhost:8080/produtos/1` | - | Buscar por ID |
| `POST` | `http://localhost:8080/produtos` | Ver abaixo | Criar produto |
| `PUT` | `http://localhost:8080/produtos/1` | Ver abaixo | Atualizar produto |
| `DELETE` | `http://localhost:8080/produtos/1` | - | Deletar produto |

### Exemplo de Body para POST/PUT

```json
{
    "nome": "Notebook",
    "preco": 3500.00,
    "descricao": "Notebook Dell Inspiron",
    "quantidade": 10
}
```

### Exportar Collection

1. Clique nos `...` ao lado da collection
2. `Export → Collection v2.1`
3. Salve o arquivo `.json`

## 🔧 Solução de Problemas

### ❌ Erro: "Failed to configure a DataSource"

**Causas possíveis:**

| Problema | Solução |
|----------|---------|
| Senha incorreta | Verifique `spring.datasource.password` |
| Banco não existe | Execute `CREATE DATABASE seu_banco;` |
| MySQL parado | Inicie o serviço MySQL |
| Erro na URL | Verifique `jdbc:mysql://localhost:3306/seu_banco` |

### ❌ Lombok não funciona

1. ✅ Plugin instalado? `File → Settings → Plugins → Lombok`
2. ✅ Annotation Processing ativo? `File → Settings → Compiler → Annotation Processors`
3. Reinicie o IntelliJ

### ❌ Projeto não carrega dependências

1. Clique com botão direito no projeto
2. `Maven → Reload Project`
3. Aguarde o download

## 📚 Referências Úteis

- [Spring Initializr](https://start.spring.io)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [MySQL Documentation](https://dev.mysql.com/doc/)


2. Clique no botão **Run** (▶️) ou pressione `Shift + F10`
3. Aguarde até ver no console:
