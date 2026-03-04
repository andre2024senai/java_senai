# Construtores e `this` em Java - POO

Guia completo sobre construtores e a palavra-chave `this` em Java, com exemplos práticos e analogias do dia-a-dia.

---

##  Índice

- [O que são Construtores?](#o-que-são-construtores)
- [Características dos Construtores](#características-dos-construtores)
- [Tipos de Construtores](#tipos-de-construtores)
- [A Palavra-chave `this`](#a-palavra-chave-this)
- [Usos do `this`](#usos-do-this)
- [Exemplos Práticos](#exemplos-práticos)
- [Analogias do Dia-a-Dia](#analogias-do-dia-a-dia)

---

## O que são Construtores?

**Construtores** são métodos especiais usados para **inicializar objetos** quando são criados. Eles garantem que o objeto nasça com um estado inicial válido.
```java
public class Pessoa {
    private String nome;
    private int idade;
    
    // Este é um construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}

// Usando o construtor ao criar um objeto
Pessoa pessoa1 = new Pessoa("João", 25);
```

---

## Características dos Construtores

| Característica | Descrição |
|----------------|-----------|
| **Nome** | Deve ter o **mesmo nome da classe** |
| **Retorno** | Não possui tipo de retorno (nem `void`) |
| **Chamada** | Executado automaticamente ao usar `new` |
| **Sobrecarga** | Pode haver múltiplos construtores na mesma classe |
| **Padrão** | Se não criar nenhum, Java cria um construtor vazio automaticamente |

---

## Tipos de Construtores

### 1. Construtor Padrão (Sem Parâmetros)
```java
public class Carro {
    private String modelo;
    private int ano;
    
    // Construtor padrão
    public Carro() {
        this.modelo = "Não definido";
        this.ano = 2024;
    }
}

// Uso
Carro carro1 = new Carro();
```

### 2. Construtor Parametrizado
```java
public class Carro {
    private String modelo;
    private int ano;
    
    // Construtor com parâmetros
    public Carro(String modelo, int ano) {
        this.modelo = modelo;
        this.ano = ano;
    }
}

// Uso
Carro carro2 = new Carro("Civic", 2023);
```

### 3. Sobrecarga de Construtores

Você pode ter **vários construtores** com diferentes parâmetros:
```java
public class Produto {
    private String nome;
    private double preco;
    private int quantidade;
    
    // Construtor 1: sem parâmetros
    public Produto() {
        this.nome = "Produto genérico";
        this.preco = 0.0;
        this.quantidade = 0;
    }
    
    // Construtor 2: com nome e preço
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = 1; // padrão
    }
    
    // Construtor 3: todos os parâmetros
    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}

// Diferentes formas de criar o objeto
Produto p1 = new Produto();
Produto p2 = new Produto("Notebook", 2500.00);
Produto p3 = new Produto("Mouse", 50.00, 10);
```

---

## A Palavra-chave `this`

O `this` é uma **referência ao objeto atual** (a instância que está sendo manipulada naquele momento).

### Por que usar `this`?

- Diferenciar atributos de parâmetros com o mesmo nome
- Chamar outro construtor da mesma classe
- Chamar métodos do próprio objeto
- Retornar a própria instância (Method Chaining)

---

## Usos do `this`

### 1️ Diferenciar Atributos de Parâmetros

Quando o parâmetro tem o **mesmo nome** do atributo:
```java
public class Aluno {
    private String nome;
    private int matricula;
    
    public Aluno(String nome, int matricula) {
        this.nome = nome;           // this.nome = atributo da classe
        this.matricula = matricula; // matricula = parâmetro do construtor
    }
}
```

**Sem o `this`**, haveria ambiguidade e o código não funcionaria corretamente.

---

### 2️ Chamar Outro Construtor

Use `this()` para **evitar duplicação de código** entre construtores:
```java
public class ContaBancaria {
    private String titular;
    private double saldo;
    
    // Construtor padrão chama o construtor completo
    public ContaBancaria() {
        this("Titular desconhecido", 0.0);
    }
    
    // Construtor completo
    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }
}
```

 **Importante**: `this()` deve ser a **primeira linha** do construtor.

---

### 3️ Chamar Métodos do Próprio Objeto
```java
public class Calculadora {
    private int resultado;
    
    public void somar(int valor) {
        this.resultado += valor;
        this.exibirResultado(); // Chama método do próprio objeto
    }
    
    public void exibirResultado() {
        System.out.println("Resultado: " + this.resultado);
    }
}
```

---

### 4️ Retornar o Próprio Objeto (Method Chaining)

Permite encadear chamadas de métodos:
```java
public class Texto {
    private String conteudo;
    
    public Texto adicionar(String texto) {
        this.conteudo += texto;
        return this; // Retorna o próprio objeto
    }
    
    public Texto maiuscula() {
        this.conteudo = this.conteudo.toUpperCase();
        return this;
    }
    
    public void exibir() {
        System.out.println(this.conteudo);
    }
}

// Uso encadeado
Texto texto = new Texto()
    .adicionar("Olá ")
    .adicionar("Mundo!")
    .maiuscula();
    
texto.exibir(); // Saída: OLÁ MUNDO!
```

---

## Exemplos Práticos

### Exemplo 1: Classe Pessoa Completa
```java
public class Pessoa {
    // Atributos
    private String nome;
    private int idade;
    private String cpf;
    
    // Construtor padrão
    public Pessoa() {
        this("Não informado", 0, "000.000.000-00");
    }
    
    // Construtor com nome e idade
    public Pessoa(String nome, int idade) {
        this(nome, idade, "000.000.000-00");
    }
    
    // Construtor completo
    public Pessoa(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }
    
    // Métodos
    public void apresentar() {
        System.out.println("Olá, meu nome é " + this.nome);
        System.out.println("Tenho " + this.idade + " anos");
    }
    
    public boolean isMaiorDeIdade() {
        return this.idade >= 18;
    }
}

// Testando
public class Main {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa("Maria", 25);
        Pessoa p3 = new Pessoa("João", 30, "123.456.789-00");
        
        p2.apresentar();
        System.out.println("É maior de idade? " + p2.isMaiorDeIdade());
    }
}
```

### Exemplo 2: Sistema de Pedidos
```java
public class Pedido {
    private int numero;
    private String cliente;
    private double valorTotal;
    private String status;
    
    // Construtor
    public Pedido(int numero, String cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.valorTotal = 0.0;
        this.status = "Pendente";
    }
    
    // Adicionar valor ao pedido
    public Pedido adicionarValor(double valor) {
        this.valorTotal += valor;
        return this; // Method Chaining
    }
    
    // Finalizar pedido
    public Pedido finalizar() {
        this.status = "Finalizado";
        this.exibirResumo();
        return this;
    }
    
    // Exibir resumo
    private void exibirResumo() {
        System.out.println("=== RESUMO DO PEDIDO ===");
        System.out.println("Número: " + this.numero);
        System.out.println("Cliente: " + this.cliente);
        System.out.println("Total: R$ " + this.valorTotal);
        System.out.println("Status: " + this.status);
    }
}

// Uso
Pedido pedido = new Pedido(1001, "João Silva")
    .adicionarValor(50.00)
    .adicionarValor(30.00)
    .finalizar();
```

---

## Analogias do Dia-a-Dia

### 🏭 Construtor = Linha de Montagem

Quando um carro sai da fábrica, ele já vem configurado:
```java
Carro meuCarro = new Carro("Vermelho", "1.0", 4);
// Cor: Vermelho, Motor: 1.0, Portas: 4
```

**O construtor é o momento em que o carro é montado com suas especificações.**

---

###  Construtor = Formulário de Cadastro

Criar uma conta no Instagram:
```java
ContaInstagram conta = new ContaInstagram("joao123", "joao@email.com", "senha123");
```

**Você precisa preencher os dados iniciais antes de usar o serviço.**

---

###  Construtor = Certidão de Nascimento

Registro inicial de uma pessoa:
```java
Pessoa maria = new Pessoa("Maria Silva", "01/01/2024", "Ana Silva");
```

**O construtor define as informações básicas quando o objeto "nasce".**

---

###  `this` = "Eu mesmo"

Quando você diz "**eu** vou fazer **meu** dever":
```java
public void fazerDever() {
    this.estudar();  // "EU vou estudar"
    this.escrever(); // "EU vou escrever"
}
```

**`this` sempre se refere ao próprio objeto.**

---

##  Resumo

| Conceito | Descrição | Exemplo |
|----------|-----------|---------|
| **Construtor** | Inicializa o objeto | `public Pessoa(String nome) { ... }` |
| **Construtor padrão** | Sem parâmetros | `public Pessoa() { ... }` |
| **Sobrecarga** | Múltiplos construtores | Vários construtores na mesma classe |
| **`this.atributo`** | Referencia atributo do objeto | `this.nome = nome;` |
| **`this()`** | Chama outro construtor | `this("valor padrão");` |
| **`return this`** | Retorna o próprio objeto | Para method chaining |

---
