# 📦 Java + MySQL com JDBC

> Material de apoio — Desenvolvimento de Sistemas | SENAI  
> Turmas 2931 e 2932

Este repositório acompanha a prática guiada de conexão Java com banco de dados MySQL usando JDBC.  
Leia com calma antes da aula — cada conceito aqui aparece no código que vamos construir juntos.

---

## 🗂️ Estrutura do projeto

```
MeuProjetoJDBC/
├── src/
│   ├── Conexao.java       ← configura a conexão com o banco
│   ├── Pessoa.java        ← classe modelo (representa uma linha da tabela)
│   ├── PessoaDAO.java     ← executa os comandos SQL
│   └── Main.java          ← ponto de entrada do programa (menu CRUD)
└── lib/
    └── mysql-connector-j-8.x.jar
```

---

## 🔌 O que é JDBC?

**JDBC** significa *Java Database Connectivity*. É a API padrão do Java para se comunicar com bancos de dados relacionais — MySQL, PostgreSQL, Oracle, etc.

Pense assim: o Java não "fala MySQL" diretamente. O JDBC é o **tradutor** entre o seu código Java e o banco de dados.

```
Código Java  →  JDBC (API)  →  Driver MySQL  →  Banco de dados
```

Para funcionar, você precisa do **MySQL Connector/J** — um arquivo `.jar` que é o driver específico do MySQL. Sem ele, o Java não sabe como se conectar.

**Por que aprender JDBC?**  
Antes de usar frameworks como Spring Data JPA, é importante entender o que acontece "por baixo do capô". O Spring também usa JDBC internamente — você só não precisa escrever o SQL na mão.

---

## 🏗️ Classe modelo — `Pessoa.java`

A classe modelo representa **uma linha da tabela do banco de dados**.  
Cada atributo da classe corresponde a uma coluna da tabela `pessoas`.

| Atributo Java | Coluna MySQL | Tipo       |
|---------------|--------------|------------|
| `id`          | `id`         | `int`      |
| `nome`        | `nome`       | `varchar`  |
| `idade`       | `idade`      | `int`      |

```java
public class Pessoa {
    private int id;
    private String nome;
    private int idade;

    // construtor sem ID → usado ao inserir (o banco gera o ID)
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // construtor completo → usado ao buscar ou atualizar dados do banco
    public Pessoa(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    // getters e setters omitidos por brevidade
}
```

**Por que dois construtores?**  
Quando você *insere* uma pessoa, o `id` ainda não existe — o banco cria automaticamente (AUTO_INCREMENT).  
Quando você *lê* ou *atualiza* uma pessoa, o `id` já existe e precisa ser informado.

---

## 🔑 Classe de conexão — `Conexao.java`

Centraliza as informações de acesso ao banco. Em vez de repetir URL, usuário e senha em todo lugar, você chama `Conexao.conectar()` sempre que precisar.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL     = "jdbc:mysql://localhost:3306/db_exemplo";
    private static final String USUARIO = "root";
    private static final String SENHA   = "sua_senha_aqui";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
```

Entendendo a URL de conexão:

```
jdbc:mysql://localhost:3306/db_exemplo
  │      │       │          │
  │      │       │          └── nome do banco (criado no Workbench)
  │      │       └── porta padrão do MySQL
  │      └── tipo do banco
  └── protocolo JDBC
```

---

## 🔗 O que é `Connection`?

`Connection` é o **canal aberto** entre o Java e o banco de dados. É como um fio que liga os dois lados.

Toda operação SQL (inserir, listar, atualizar, excluir) precisa de uma `Connection` ativa.

```java
Connection conn = Conexao.conectar(); // abre o canal
// ... executa SQL ...
conn.close();                         // fecha o canal
```

> ⚠️ **Importante:** conexões são recursos limitados. Sempre feche a conexão depois de usar.  
> O padrão moderno usa `try-with-resources` para fechar automaticamente — veja no DAO abaixo.

---

## 🛡️ O que é `PreparedStatement`?

É a forma **segura e correta** de enviar SQL com parâmetros variáveis ao banco.

Compare as duas abordagens:

```java
// ❌ ERRADO — concatenação de String (vulnerável a SQL Injection)
String sql = "INSERT INTO pessoas (nome, idade) VALUES ('" + nome + "', " + idade + ")";

// ✅ CERTO — PreparedStatement com parâmetros
String sql = "INSERT INTO pessoas (nome, idade) VALUES (?, ?)";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, nome);   // substitui o 1º ?
stmt.setInt(2, idade);     // substitui o 2º ?
stmt.executeUpdate();
```

Os `?` são **placeholders** — o driver MySQL substitui cada `?` pelo valor real de forma segura, sem risco de o usuário injetar código malicioso.

**Métodos principais do `PreparedStatement`:**

| Método              | Quando usar                        |
|---------------------|------------------------------------|
| `setString(n, v)`   | para campos `varchar` / `text`     |
| `setInt(n, v)`      | para campos `int`                  |
| `executeUpdate()`   | para INSERT, UPDATE, DELETE        |
| `executeQuery()`    | para SELECT (retorna `ResultSet`)  |

---

## 📋 O que é `ResultSet`?

Quando você executa um `SELECT`, o banco retorna uma tabela de resultados. O `ResultSet` é o **cursor** que percorre essa tabela linha por linha.

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM pessoas");

while (rs.next()) {                        // avança uma linha
    int id       = rs.getInt("id");
    String nome  = rs.getString("nome");
    int idade    = rs.getInt("idade");

    System.out.println(id + " | " + nome + " | " + idade);
}
```

Pense no `ResultSet` como um dedo que começa antes da primeira linha e vai descendo com cada `rs.next()`. Quando não há mais linhas, `rs.next()` retorna `false` e o `while` para.

**Métodos de leitura mais usados:**

| Método                  | Tipo retornado |
|-------------------------|----------------|
| `getInt("coluna")`      | `int`          |
| `getString("coluna")`   | `String`       |
| `getDouble("coluna")`   | `double`       |
| `getBoolean("coluna")`  | `boolean`      |

---

## 🏦 Padrão DAO — `PessoaDAO.java` (CRUD completo)

**DAO** significa *Data Access Object*. É um padrão de projeto que **separa o código de acesso ao banco do restante da aplicação**.

Em vez de espalhar SQL pelo `Main` ou pelas classes de negócio, você concentra tudo em uma classe específica.

```
Main.java          → "quero inserir / listar / atualizar / excluir uma pessoa"
PessoaDAO.java     → sabe COMO fazer cada operação (escreve o SQL, usa JDBC)
Banco de dados     → executa e persiste
```

### CREATE — `inserir()`

```java
public void inserir(Pessoa p) throws SQLException {
    String sql = "INSERT INTO pessoas (nome, idade) VALUES (?, ?)";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, p.getNome());
        stmt.setInt(2, p.getIdade());
        stmt.executeUpdate();
        System.out.println("✔ Pessoa inserida com sucesso!");
    }
}
```

### READ — `listar()`

```java
public List<Pessoa> listar() throws SQLException {
    List<Pessoa> lista = new ArrayList<>();
    String sql = "SELECT * FROM pessoas";

    try (Connection conn = Conexao.conectar();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            lista.add(new Pessoa(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("idade")
            ));
        }
    }
    return lista;
}
```

### UPDATE — `atualizar()`

O UPDATE precisa do `id` para saber **qual linha** alterar no banco. Por isso o `Main` cria o objeto `Pessoa` usando o construtor completo (com id):

```java
public void atualizar(Pessoa p) throws SQLException {
    String sql = "UPDATE pessoas SET nome = ?, idade = ? WHERE id = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, p.getNome());
        stmt.setInt(2, p.getIdade());
        stmt.setInt(3, p.getId());     // ← o WHERE usa o id
        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("✔ Pessoa atualizada com sucesso!");
        } else {
            System.out.println("⚠ Nenhuma pessoa encontrada com esse ID.");
        }
    }
}
```

> 💡 **Atenção ao `WHERE`**: sem a cláusula `WHERE id = ?`, o UPDATE alteraria **todas** as linhas da tabela. Sempre confirme qual registro você está modificando.

`executeUpdate()` retorna o número de linhas afetadas — verificar esse valor é uma boa prática para saber se o registro realmente existia.

### DELETE — `excluir()`

```java
public void excluir(int id) throws SQLException {
    String sql = "DELETE FROM pessoas WHERE id = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("✔ Pessoa excluída com sucesso!");
        } else {
            System.out.println("⚠ Nenhuma pessoa encontrada com esse ID.");
        }
    }
}
```

> 💡 **Atenção ao `WHERE`**: sem a cláusula `WHERE id = ?`, o DELETE removeria **todas** as linhas da tabela. Cuidado redobrado aqui — não há desfazer.

**Por que `try-with-resources`?**  
`Connection`, `PreparedStatement` e `ResultSet` são recursos que precisam ser fechados. O `try (...)` garante que o Java fecha tudo automaticamente ao final do bloco — mesmo se ocorrer uma exceção.

---

## ⚠️ O que é `SQLException`?

`SQLException` é a exceção lançada quando **algo dá errado na comunicação com o banco**. Exemplos comuns:

| Situação                          | Mensagem típica                        |
|-----------------------------------|----------------------------------------|
| Senha errada                      | `Access denied for user 'root'`        |
| Banco não encontrado              | `Unknown database 'db_exemplo'`        |
| MySQL não está rodando            | `Communications link failure`          |
| Coluna com nome errado no SQL     | `Unknown column 'nomee' in field list` |

**Onde tratar?**  
A convenção neste projeto é deixar o DAO **lançar** (`throws SQLException`) e o `Main` **capturar** (`catch`). Assim o DAO fica limpo e o controle de erro fica em um único lugar.

---

## 🖥️ Classe principal — `Main.java`

O `Main` implementa um menu interativo com as quatro operações do CRUD. Alguns pontos importantes do código:

**Leitura de inteiros com `Integer.parseInt(sc.nextLine())`**

```java
opcao = Integer.parseInt(sc.nextLine());
```

Preferimos essa abordagem em vez de `sc.nextInt()` porque o `nextInt()` não consome o `\n` do Enter — o que causa problemas nas leituras de String seguintes. Com `nextLine()` + `parseInt`, o buffer fica sempre limpo.

**Caso 3 — Atualizar:**

```java
case 3:
    System.out.print("ID da pessoa a atualizar: ");
    int idAtualizar = Integer.parseInt(sc.nextLine());

    System.out.print("Novo nome: ");
    String novoNome = sc.nextLine();

    System.out.print("Nova idade: ");
    int novaIdade = Integer.parseInt(sc.nextLine());

    Pessoa pessoaAtualizada = new Pessoa(idAtualizar, novoNome, novaIdade);
    pessoaDAO.atualizar(pessoaAtualizada);
    break;
```

Note que o objeto `Pessoa` é criado com o **construtor completo** (id + nome + idade), pois o DAO precisa do id para montar o `WHERE`.

**Caso 4 — Excluir:**

```java
case 4:
    System.out.print("ID da pessoa a excluir: ");
    int idExcluir = Integer.parseInt(sc.nextLine());

    pessoaDAO.excluir(idExcluir);
    break;
```

Aqui apenas o id é necessário — o DAO faz o resto.

---

## 🗄️ Preparando o banco de dados

Execute estes comandos no **MySQL Workbench** antes de rodar o projeto:

```sql
CREATE DATABASE db_exemplo;
USE db_exemplo;

CREATE TABLE pessoas (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nome   VARCHAR(100) NOT NULL,
    idade  INT NOT NULL
);
```

Para verificar os dados após rodar o programa:

```sql
SELECT * FROM pessoas;
```

---

## 🗺️ Mapa dos conceitos

```
JDBC              → tecnologia que permite Java falar com banco de dados
Driver (.jar)     → implementação específica para o MySQL
Connection        → canal aberto entre Java e o banco
PreparedStatement → comando SQL seguro com parâmetros (?)
ResultSet         → tabela de resultados de um SELECT
SQLException      → erro de banco (conexão, SQL errado, etc.)
Classe modelo     → representa uma linha da tabela como objeto Java
DAO               → classe que concentra todo o código SQL do projeto
CRUD              → Create, Read, Update, Delete — as quatro operações básicas
```

---

## 🔭 Próximos passos

Este projeto é a base para o que vem a seguir:

1. **Web com Spring Boot** — o Spring usa os mesmos conceitos, mas abstrai o JDBC com JPA/Hibernate
2. **Spring Data JPA** — você terá `findAll()`, `save()`, `deleteById()` prontos, sem escrever SQL na mão

---

> Dúvidas? Abra uma *issue* neste repositório ou pergunte na aula. 🙋
