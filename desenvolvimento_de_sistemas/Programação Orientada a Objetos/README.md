# Os 4 Pilares da Programação Orientada a Objetos em Java
 
Guia completo sobre os fundamentos da POO: Encapsulamento, Herança, Polimorfismo e Abstração, com exemplos práticos e analogias do dia-a-dia.
 
---
 
##  Índice
 
- [Introdução à POO](#introducao-à-poo)
- [1️ Encapsulamento](#1-encapsulamento)
- [2️ Herança](#2-heranca)
- [3️ Polimorfismo](#3-polimorfismo)
- [4️ Abstração](#4-abstracao)
- [Comparação Entre os Pilares](#comparacao-entre-os-pilares)
- [Exemplo Completo Integrando Todos os Pilares](#exemplo-completo-integrando-todos-os-pilares)
 
---
 
## Introdução à POO
 
A **Programação Orientada a Objetos (POO)** é um paradigma de programação baseado no conceito de "objetos", que podem conter dados (atributos) e código (métodos).
 
### Os 4 Pilares da POO (em ordem de aprendizado):
 
| Pilar | Descrição Resumida |
|-------|-------------------|
| **1. Encapsulamento** | Proteger dados internos, controlando o acesso |
| **2. Herança** | Reutilizar código através de hierarquia de classes |
| **3. Polimorfismo** | Múltiplas formas de executar a mesma ação |
| **4. Abstração** | Esconder complexidade, mostrar apenas o essencial |
 
---
 
<a id="1-encapsulamento"></a>
## 1️ Encapsulamento
 
### O que é?
 
**Encapsulamento** é o conceito de **proteger os dados internos** de uma classe, permitindo acesso controlado através de métodos públicos (getters e setters). É a base fundamental da POO.
 
### Analogia 🏦
 
Um caixa eletrônico:
- Você **não pode** acessar o dinheiro diretamente
- Você **precisa** usar a interface (tela, teclado) para sacar
- O banco controla as regras (limite, saldo, senha)
 
### Princípios do Encapsulamento
 
- Atributos devem ser **private**
- Acesso aos atributos através de métodos **public** (getters/setters)
- Adicionar **validações** nos setters
- Controlar **como** os dados são modificados
 
### Exemplo Básico
```java
public class Pessoa {
    // Atributos PRIVADOS - ninguém acessa diretamente
    private String nome;
    private int idade;
    private String cpf;
    
    // Construtor
    public Pessoa(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }
    
    // GETTERS - permitem CONSULTAR os dados
    public String getNome() {
        return nome;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    // SETTERS - permitem MODIFICAR os dados com VALIDAÇÃO
    public void setNome(String nome) {
        if (nome != null && nome.length() >= 3) {
            this.nome = nome;
        } else {
            System.out.println("Nome inválido! Deve ter pelo menos 3 caracteres.");
        }
    }
    
    public void setIdade(int idade) {
        if (idade >= 0 && idade <= 150) {
            this.idade = idade;
        } else {
            System.out.println("Idade inválida!");
        }
    }
    
    // CPF não tem setter - não pode ser alterado
    
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("CPF: " + cpf);
    }
}
 
// Uso correto do encapsulamento
public class Main {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("João Silva", 25, "123.456.789-00");
        
        // pessoa.nome = "Hacker"; // ERRO! Atributo é private
        
        // Forma correta - através dos métodos públicos
        System.out.println("Nome: " + pessoa.getNome()); // Consulta
        pessoa.setNome("João Pedro Silva"); // Modifica com validação
        
        pessoa.setIdade(-5); // Validação impede valor inválido
        pessoa.setIdade(26); // Valor válido é aceito
        
        pessoa.exibirDados();
    }
}
```
 
### Exemplo Prático: Conta Bancária
```java
public class ContaBancaria {
    // Atributos ENCAPSULADOS
    private String titular;
    private String numeroConta;
    private double saldo;
    private double limite;
    
    // Construtor
    public ContaBancaria(String titular, String numeroConta) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
        this.limite = 0.0;
    }
    
    // Getters - apenas consulta
    public String getTitular() {
        return titular;
    }
    
    public String getNumeroConta() {
        return numeroConta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public double getSaldoDisponivel() {
        return saldo + limite;
    }
    
    // Setter com validação
    public void setLimite(double limite) {
        if (limite >= 0) {
            this.limite = limite;
            System.out.println("Limite definido para: R$ " + limite);
        } else {
            System.out.println("Limite inválido!");
        }
    }
    
    // Métodos que CONTROLAM as modificações no saldo
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado!");
            System.out.println("Novo saldo: R$ " + this.saldo);
        } else {
            System.out.println("Valor de depósito inválido!");
        }
    }
    
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido!");
            return false;
        }
        
        if (valor > getSaldoDisponivel()) {
            System.out.println("Saldo insuficiente!");
            System.out.println("Saldo disponível: R$ " + getSaldoDisponivel());
            return false;
        }
        
        this.saldo -= valor;
        System.out.println("Saque de R$ " + valor + " realizado!");
        System.out.println("Novo saldo: R$ " + this.saldo);
        return true;
    }
    
    public void transferir(ContaBancaria destino, double valor) {
        System.out.println("\n=== TRANSFERÊNCIA ===");
        System.out.println("De: " + this.titular);
        System.out.println("Para: " + destino.getTitular());
        System.out.println("Valor: R$ " + valor);
        
        if (this.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Transferência não realizada!");
        }
    }
    
    public void exibirExtrato() {
        System.out.println("\n=== EXTRATO ===");
        System.out.println("Titular: " + titular);
        System.out.println("Conta: " + numeroConta);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("Limite: R$ " + limite);
        System.out.println("Saldo disponível: R$ " + getSaldoDisponivel());
    }
}
 
// Testando o encapsulamento
public class Main {
    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaBancaria("João Silva", "12345-6");
        ContaBancaria conta2 = new ContaBancaria("Maria Santos", "98765-4");
        
        // conta1.saldo = 1000000; // ERRO! Protegido por encapsulamento
        
        // Forma correta - através dos métodos
        conta1.depositar(1000.00);
        conta1.setLimite(500.00);
        conta1.sacar(200.00);
        
        conta2.depositar(500.00);
        
        conta1.transferir(conta2, 300.00);
        
        conta1.exibirExtrato();
        conta2.exibirExtrato();
    }
}
```
 
### 🎯 Benefícios do Encapsulamento
 
✅ **Segurança**: Dados protegidos de modificações indevidas  
✅ **Validação**: Controle sobre valores atribuídos  
✅ **Manutenção**: Mudanças internas não afetam código externo  
✅ **Flexibilidade**: Alterar implementação sem quebrar código  
✅ **Integridade**: Garante que objetos estejam sempre em estado válido
 
---
 
<a id="2-heranca"></a>
## 2️⃣ Herança
 
### O que é?
 
**Herança** permite que uma classe (subclasse/filha) **herde atributos e métodos** de outra classe (superclasse/pai), promovendo **reutilização de código** e criando hierarquias.
 
### Analogia 👨‍👩‍👧‍👦
 
Uma família:
- Filhos **herdam** características dos pais (altura, cor dos olhos, etc.)
- Mas também têm características **próprias**
- Podem **modificar** comportamentos herdados
 
### Sintaxe
```java
public class ClasseFilha extends ClassePai {
    // Herda tudo que é public/protected do pai
    // Pode adicionar novos atributos e métodos
    // Pode sobrescrever métodos do pai
}
```
 
### Por que usar Herança?
 
- **Reutilização de código**: Evita duplicação
- **Organização**: Cria hierarquias lógicas
- **Manutenção**: Alterações no pai afetam todos os filhos
- **Especialização**: Classes filhas são versões especializadas do pai
 
### Exemplo Básico
```java
// Classe PAI (Superclasse/Base)
public class Pessoa {
    // Atributos protegidos - acessíveis nas classes filhas
    protected String nome;
    protected int idade;
    protected String cpf;
    
    // Construtor
    public Pessoa(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }
    
    // Métodos comuns a todas as pessoas
    public void apresentar() {
        System.out.println("Olá, meu nome é " + nome);
        System.out.println("Tenho " + idade + " anos");
    }
    
    public boolean isMaiorDeIdade() {
        return idade >= 18;
    }
    
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("CPF: " + cpf);
    }
}
 
// Classe FILHA 1 - Aluno HERDA de Pessoa
public class Aluno extends Pessoa {
    // Atributos específicos de Aluno
    private String matricula;
    private String curso;
    private double[] notas;
    private int quantidadeNotas;
    
    // Construtor
    public Aluno(String nome, int idade, String cpf, String matricula, String curso) {
        super(nome, idade, cpf); // Chama construtor do pai
        this.matricula = matricula;
        this.curso = curso;
        this.notas = new double[4];
        this.quantidadeNotas = 0;
    }
    
    // Métodos específicos de Aluno
    public void adicionarNota(double nota) {
        if (quantidadeNotas < 4 && nota >= 0 && nota <= 10) {
            notas[quantidadeNotas] = nota;
            quantidadeNotas++;
            System.out.println("Nota adicionada: " + nota);
        } else {
            System.out.println("Erro ao adicionar nota!");
        }
    }
    
    public double calcularMedia() {
        if (quantidadeNotas == 0) return 0;
        
        double soma = 0;
        for (int i = 0; i < quantidadeNotas; i++) {
            soma += notas[i];
        }
        return soma / quantidadeNotas;
    }
    
    public String verificarSituacao() {
        double media = calcularMedia();
        if (media >= 7.0) return "Aprovado";
        else if (media >= 5.0) return "Recuperação";
        else return "Reprovado";
    }
    
    // Sobrescrevendo método do pai para adicionar informações
    @Override
    public void exibirDados() {
        super.exibirDados(); // Chama o método do pai
        System.out.println("Matrícula: " + matricula);
        System.out.println("Curso: " + curso);
        System.out.println("Média: " + calcularMedia());
        System.out.println("Situação: " + verificarSituacao());
    }
}
 
// Classe FILHA 2 - Professor HERDA de Pessoa
public class Professor extends Pessoa {
    // Atributos específicos de Professor
    private String disciplina;
    private double salario;
    private int cargaHoraria;
    
    // Construtor
    public Professor(String nome, int idade, String cpf, String disciplina, double salario) {
        super(nome, idade, cpf);
        this.disciplina = disciplina;
        this.salario = salario;
        this.cargaHoraria = 40;
    }
    
    // Métodos específicos de Professor
    public void darAula() {
        System.out.println("Professor " + nome + " está ministrando " + disciplina);
    }
    
    public void corrigirProvas() {
        System.out.println("Professor " + nome + " está corrigindo provas");
    }
    
    public double calcularSalarioPorHora() {
        return salario / cargaHoraria;
    }
    
    public void aumentarSalario(double percentual) {
        if (percentual > 0) {
            salario += salario * (percentual / 100);
            System.out.println("Novo salário: R$ " + salario);
        }
    }
    
    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Disciplina: " + disciplina);
        System.out.println("Salário: R$ " + salario);
        System.out.println("Carga horária: " + cargaHoraria + "h");
    }
}
 
// Testando a herança
public class Main {
    public static void main(String[] args) {
        // Criando objetos
        Aluno aluno = new Aluno("João Silva", 20, "111.111.111-11", "2024001", "Ciência da Computação");
        Professor professor = new Professor("Maria Santos", 35, "222.222.222-22", "Programação Java", 5000.00);
        
        System.out.println("=== ALUNO ===");
        aluno.apresentar(); // Método herdado
        aluno.adicionarNota(8.5);
        aluno.adicionarNota(9.0);
        aluno.adicionarNota(7.5);
        aluno.exibirDados(); // Método sobrescrito
        
        System.out.println("\n=== PROFESSOR ===");
        professor.apresentar(); // Método herdado
        professor.darAula();
        professor.aumentarSalario(10);
        professor.exibirDados(); // Método sobrescrito
    }
}
```
 
### Exemplo: Hierarquia de Veículos
```java
// Classe BASE
public class Veiculo {
    protected String marca;
    protected String modelo;
    protected int ano;
    protected double velocidadeAtual;
    
    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velocidadeAtual = 0;
    }
    
    public void acelerar(double incremento) {
        velocidadeAtual += incremento;
        System.out.println("Acelerando... Velocidade: " + velocidadeAtual + " km/h");
    }
    
    public void frear(double decremento) {
        velocidadeAtual -= decremento;
        if (velocidadeAtual < 0) velocidadeAtual = 0;
        System.out.println("Freando... Velocidade: " + velocidadeAtual + " km/h");
    }
    
    public void exibirInfo() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Velocidade atual: " + velocidadeAtual + " km/h");
    }
}
 
// HERANÇA: Carro é um Veículo
public class Carro extends Veiculo {
    private int numeroPortas;
    private boolean arCondicionado;
    private String tipoCombustivel;
    
    public Carro(String marca, String modelo, int ano, int numeroPortas, String tipoCombustivel) {
        super(marca, modelo, ano);
        this.numeroPortas = numeroPortas;
        this.arCondicionado = false;
        this.tipoCombustivel = tipoCombustivel;
    }
    
    public void ligarArCondicionado() {
        arCondicionado = true;
        System.out.println("Ar condicionado ligado!");
    }
    
    public void desligarArCondicionado() {
        arCondicionado = false;
        System.out.println("Ar condicionado desligado!");
    }
    
    @Override
    public void acelerar(double incremento) {
        System.out.println("Carro acelerando...");
        super.acelerar(incremento);
    }
    
    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Portas: " + numeroPortas);
        System.out.println("Combustível: " + tipoCombustivel);
        System.out.println("Ar condicionado: " + (arCondicionado ? "Ligado" : "Desligado"));
    }
}
 
// HERANÇA: Moto é um Veículo
public class Moto extends Veiculo {
    private boolean partidaEletrica;
    private int cilindradas;
    
    public Moto(String marca, String modelo, int ano, int cilindradas, boolean partidaEletrica) {
        super(marca, modelo, ano);
        this.cilindradas = cilindradas;
        this.partidaEletrica = partidaEletrica;
    }
    
    public void empinar() {
        if (velocidadeAtual > 20) {
            System.out.println("Moto empinando! 🏍️");
        } else {
            System.out.println("Velocidade insuficiente para empinar!");
        }
    }
    
    @Override
    public void acelerar(double incremento) {
        System.out.println("Moto acelerando rapidamente...");
        super.acelerar(incremento * 1.5); // Motos aceleram mais rápido
    }
    
    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Cilindradas: " + cilindradas);
        System.out.println("Partida elétrica: " + (partidaEletrica ? "Sim" : "Não"));
    }
}
 
// Testando
public class Main {
    public static void main(String[] args) {
        Carro carro = new Carro("Honda", "Civic", 2023, 4, "Flex");
        Moto moto = new Moto("Yamaha", "MT-07", 2022, 689, true);
        
        System.out.println("=== CARRO ===");
        carro.exibirInfo();
        carro.acelerar(50);
        carro.ligarArCondicionado();
        carro.frear(20);
        
        System.out.println("\n=== MOTO ===");
        moto.exibirInfo();
        moto.acelerar(20); // Acelera mais devido ao override
        moto.empinar();
        moto.acelerar(10);
        moto.empinar();
    }
}
```
 
### 🎯 Modificadores de Acesso na Herança
 
| Modificador | Classe | Pacote | Subclasse | Todos |
|-------------|--------|--------|-----------|-------|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `default` | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |
 
### Palavra-chave `super`
```java
// super() - chama construtor do pai
public Aluno(String nome, int idade) {
    super(nome, idade); // Construtor de Pessoa
}
 
// super.metodo() - chama método do pai
@Override
public void exibir() {
    super.exibir(); // Chama exibir() de Pessoa
    // Adiciona mais informações
}
```
 
### ⚠️ Cuidados com Herança
 
- Use herança apenas quando há **relação "É UM"**
  - Cachorro **É UM** Animal ✅
  - Carro **TEM UM** Motor (use composição) ❌
- Evite **hierarquias muito profundas**
- Prefira **composição** quando apropriado
- Java não permite **herança múltipla** de classes
 
---
 
<a id="3-polimorfismo"></a>
## 3️⃣ Polimorfismo
 
### O que é?
 
**Polimorfismo** significa "**muitas formas**". É a capacidade de um objeto assumir diferentes formas e se comportar de maneiras diferentes dependendo do contexto. Permite que o mesmo método tenha comportamentos distintos em classes diferentes.
 
### Analogia 🎮
 
Um controle de videogame:
- O botão "A" funciona em diferentes jogos
- Em um jogo de corrida: acelera
- Em um jogo de luta: soca
- Em um jogo de plataforma: pula
- **Mesmo botão, comportamentos diferentes!**
 
### Tipos de Polimorfismo
 
#### 1. Polimorfismo de Sobrescrita (Override) - Runtime
 
Métodos na classe filha **substituem** a implementação da classe pai.
```java
// Classe pai
public class Animal {
    protected String nome;
    
    public Animal(String nome) {
        this.nome = nome;
    }
    
    public void emitirSom() {
        System.out.println("Animal emitindo som...");
    }
    
    public void mover() {
        System.out.println("Animal se movendo...");
    }
}
 
// Classes filhas com comportamentos DIFERENTES
public class Cachorro extends Animal {
    public Cachorro(String nome) {
        super(nome);
    }
    
    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: Au Au! 🐕");
    }
    
    @Override
    public void mover() {
        System.out.println(nome + " está correndo!");
    }
}
 
public class Gato extends Animal {
    public Gato(String nome) {
        super(nome);
    }
    
    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: Miau! 🐈");
    }
    
    @Override
    public void mover() {
        System.out.println(nome + " está andando silenciosamente...");
    }
}
 
public class Passaro extends Animal {
    public Passaro(String nome) {
        super(nome);
    }
    
    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: Piu Piu! 🐦");
    }
    
    @Override
    public void mover() {
        System.out.println(nome + " está voando!");
    }
}
 
// A MÁGICA DO POLIMORFISMO!
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("=== CADASTRO DE ANIMAIS ===");
        System.out.print("Digite o nome do cachorro: ");
        String nomeCachorro = scanner.nextLine();
 
        System.out.print("Digite o nome do gato: ");
        String nomeGato = scanner.nextLine();
 
        System.out.print("Digite o nome do pássaro: ");
        String nomePassaro = scanner.nextLine();
 
        // Todos são do tipo Animal, mas cada um se comporta diferente
        Animal animal1 = new Cachorro(nomeCachorro);
        Animal animal2 = new Gato(nomeGato);
        Animal animal3 = new Passaro(nomePassaro);
        
        // Array polimórfico - mesma estrutura, comportamentos diferentes
        Animal[] animais = {animal1, animal2, animal3};
        
        System.out.println("\n=== POLIMORFISMO EM AÇÃO ===\n");
        
        // Mesmo código, resultados diferentes!
        for (Animal animal : animais) {
            animal.emitirSom(); // Cada um emite SOM DIFERENTE
            animal.mover();     // Cada um se MOVE DIFERENTE
            System.out.println();
        }
 
        scanner.close();
    }
}
 
/* Saída:
Rex faz: Au Au! 🐕
Rex está correndo!
 
Mimi faz: Miau! 🐈
Mimi está andando silenciosamente...
 
Piu faz: Piu Piu! 🐦
Piu está voando!
*/
```
 
#### 2. Polimorfismo de Sobrecarga (Overload) - Compile Time
 
**Mesmo nome de método**, mas com **parâmetros diferentes** na mesma classe.
```java
public class Calculadora {
    // Sobrecarga: mesmo nome, assinaturas diferentes
    
    // Soma de 2 inteiros
    public int somar(int a, int b) {
        System.out.println("Somando 2 inteiros");
        return a + b;
    }
    
    // Soma de 3 inteiros
    public int somar(int a, int b, int c) {
        System.out.println("Somando 3 inteiros");
        return a + b + c;
    }
    
    // Soma de 2 doubles
    public double somar(double a, double b) {
        System.out.println("Somando 2 doubles");
        return a + b;
    }
    
    // Soma de array
    public int somar(int[] numeros) {
        System.out.println("Somando array de inteiros");
        int soma = 0;
        for (int num : numeros) {
            soma += num;
        }
        return soma;
    }
    
    // Concatenar strings (sobrecarga criativa)
    public String somar(String a, String b) {
        System.out.println("Concatenando strings");
        return a + " " + b;
    }
}
 
// Testando
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calc = new Calculadora();
 
        System.out.println("=== CALCULADORA COM SOBRECARGA ===");
 
        System.out.print("Digite o 1º número inteiro: ");
        int a = scanner.nextInt();
        System.out.print("Digite o 2º número inteiro: ");
        int b = scanner.nextInt();
        System.out.println("Resultado (2 inteiros): " + calc.somar(a, b));
 
        System.out.print("Digite o 3º número inteiro: ");
        int c = scanner.nextInt();
        System.out.println("Resultado (3 inteiros): " + calc.somar(a, b, c));
 
        System.out.print("Digite o 1º número decimal: ");
        double x = scanner.nextDouble();
        System.out.print("Digite o 2º número decimal: ");
        double y = scanner.nextDouble();
        System.out.println("Resultado (2 doubles): " + calc.somar(x, y));
 
        scanner.nextLine(); // limpar buffer
        System.out.print("Digite a 1ª palavra: ");
        String s1 = scanner.nextLine();
        System.out.print("Digite a 2ª palavra: ");
        String s2 = scanner.nextLine();
        System.out.println("Resultado (strings): " + calc.somar(s1, s2));
 
        scanner.close();
    }
}
```
 
### Exemplo Prático: Sistema de Funcionários
```java
// Classe base
public class Funcionario {
    protected String nome;
    protected String cpf;
    protected double salarioBase;
    
    public Funcionario(String nome, String cpf, double salarioBase) {
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }
    
    // Método que será sobrescrito (polimorfismo)
    public double calcularSalario() {
        return salarioBase;
    }
    
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Salário: R$ " + calcularSalario());
    }
    
    public String getNome() {
        return nome;
    }
}
 
// Gerente - cálculo de salário DIFERENTE
public class Gerente extends Funcionario {
    private double bonus;
    
    public Gerente(String nome, String cpf, double salarioBase, double bonus) {
        super(nome, cpf, salarioBase);
        this.bonus = bonus;
    }
    
    @Override
    public double calcularSalario() {
        return salarioBase + bonus;
    }
    
    public void aprovarDespesa(double valor) {
        System.out.println("Gerente " + nome + " aprovou despesa de R$ " + valor);
    }
}
 
// Desenvolvedor - cálculo de salário DIFERENTE
public class Desenvolvedor extends Funcionario {
    private String linguagem;
    private int horasExtras;
    private static final double VALOR_HORA_EXTRA = 50.00;
    
    public Desenvolvedor(String nome, String cpf, double salarioBase, String linguagem) {
        super(nome, cpf, salarioBase);
        this.linguagem = linguagem;
        this.horasExtras = 0;
    }
    
    @Override
    public double calcularSalario() {
        return salarioBase + (horasExtras * VALOR_HORA_EXTRA);
    }
    
    public void registrarHorasExtras(int horas) {
        this.horasExtras += horas;
        System.out.println(horas + " horas extras registradas para " + nome);
    }
    
    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Linguagem: " + linguagem);
        System.out.println("Horas extras: " + horasExtras);
    }
}
 
// Vendedor - cálculo de salário DIFERENTE
public class Vendedor extends Funcionario {
    private double totalVendas;
    private double percentualComissao;
    
    public Vendedor(String nome, String cpf, double salarioBase, double percentualComissao) {
        super(nome, cpf, salarioBase);
        this.percentualComissao = percentualComissao;
        this.totalVendas = 0;
    }
    
    @Override
    public double calcularSalario() {
        double comissao = totalVendas * (percentualComissao / 100);
        return salarioBase + comissao;
    }
    
    public void registrarVenda(double valor) {
        this.totalVendas += valor;
        System.out.println("Venda de R$ " + valor + " registrada para " + nome);
    }
    
    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Total de vendas: R$ " + totalVendas);
        System.out.println("Comissão: " + percentualComissao + "%");
    }
}
 
// Sistema de folha de pagamento usando POLIMORFISMO
public class FolhaPagamento {
    private ArrayList<Funcionario> funcionarios;
    
    public FolhaPagamento() {
        this.funcionarios = new ArrayList<>();
    }
    
    public void adicionarFuncionario(Funcionario func) {
        funcionarios.add(func);
        System.out.println(func.getNome() + " adicionado à folha de pagamento");
    }
    
    // POLIMORFISMO: mesmo método funciona para TODOS os tipos
    public void processarFolha() {
        System.out.println("\n=== PROCESSANDO FOLHA DE PAGAMENTO ===");
        double totalFolha = 0;
        
        for (Funcionario func : funcionarios) {
            System.out.println("\n" + func.getNome());
            double salario = func.calcularSalario(); // Polimorfismo!
            System.out.println("Salário: R$ " + salario);
            totalFolha += salario;
        }
        
        System.out.println("\n=== TOTAL DA FOLHA: R$ " + totalFolha + " ===");
    }
    
    public void exibirTodosFuncionarios() {
        System.out.println("\n=== TODOS OS FUNCIONÁRIOS ===");
        for (Funcionario func : funcionarios) {
            System.out.println();
            func.exibirDados();
        }
    }
}
 
// Testando o polimorfismo
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FolhaPagamento folha = new FolhaPagamento();
 
        System.out.println("=== CADASTRO DE FUNCIONÁRIOS ===\n");
 
        // Gerente
        System.out.println("--- Dados do Gerente ---");
        System.out.print("Nome: ");
        String nomeGerente = scanner.nextLine();
        System.out.print("CPF: ");
        String cpfGerente = scanner.nextLine();
        System.out.print("Salário base: R$ ");
        double salarioGerente = scanner.nextDouble();
        System.out.print("Bônus: R$ ");
        double bonus = scanner.nextDouble();
        scanner.nextLine();
        Gerente gerente = new Gerente(nomeGerente, cpfGerente, salarioGerente, bonus);
 
        // Desenvolvedor 1
        System.out.println("\n--- Dados do Desenvolvedor 1 ---");
        System.out.print("Nome: ");
        String nomeDev1 = scanner.nextLine();
        System.out.print("CPF: ");
        String cpfDev1 = scanner.nextLine();
        System.out.print("Salário base: R$ ");
        double salarioDev1 = scanner.nextDouble();
        System.out.print("Horas extras: ");
        int horasDev1 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Linguagem: ");
        String linguagemDev1 = scanner.nextLine();
        Desenvolvedor dev1 = new Desenvolvedor(nomeDev1, cpfDev1, salarioDev1, linguagemDev1);
        dev1.registrarHorasExtras(horasDev1);
 
        // Vendedor
        System.out.println("\n--- Dados do Vendedor ---");
        System.out.print("Nome: ");
        String nomeVendedor = scanner.nextLine();
        System.out.print("CPF: ");
        String cpfVendedor = scanner.nextLine();
        System.out.print("Salário base: R$ ");
        double salarioVendedor = scanner.nextDouble();
        System.out.print("Percentual de comissão (%): ");
        double comissao = scanner.nextDouble();
        System.out.print("Total de vendas: R$ ");
        double vendas = scanner.nextDouble();
        scanner.nextLine();
        Vendedor vendedor = new Vendedor(nomeVendedor, cpfVendedor, salarioVendedor, comissao);
        vendedor.registrarVenda(vendas);
 
        // Adicionando todos (polimorfismo - todos são Funcionario)
        folha.adicionarFuncionario(gerente);
        folha.adicionarFuncionario(dev1);
        folha.adicionarFuncionario(vendedor);
 
        // Processando (polimorfismo em ação!)
        folha.processarFolha();
        folha.exibirTodosFuncionarios();
 
        scanner.close();
    }
}
```
 
### 🎯 Benefícios do Polimorfismo
 
✅ **Flexibilidade**: Código funciona com múltiplos tipos  
✅ **Extensibilidade**: Fácil adicionar novos tipos sem alterar código existente  
✅ **Manutenção**: Mudanças localizadas em cada classe  
✅ **Reutilização**: Mesmo código funciona para diferentes objetos  
✅ **Simplicidade**: Interface única para diferentes implementações
 
---
 
<a id="4-abstracao"></a>
## 4️⃣ Abstração
 
### O que é?
 
**Abstração** é o processo de **esconder detalhes complexos** e expor apenas o essencial. Focamos no "**O QUE**" fazer, não no "**COMO**" fazer. É o pilar mais avançado da POO, construído sobre os outros três.
 
### Analogia 🚗
 
Dirigir um carro:
- Você sabe que ao **pisar no acelerador**, o carro anda
- Você **NÃO precisa saber** como funciona a injeção eletrônica, combustão, transmissão
- A complexidade está **abstraída** - você usa uma interface simples
 
### Formas de Abstração em Java

![abstracao1](https://github.com/user-attachments/assets/90602bb9-28ec-40f9-90ae-fbd58892666b)
 
![abstracao2](https://github.com/user-attachments/assets/0bdd9a47-94e7-419d-bec3-d4887985bbb5)

![abstracao3](https://github.com/user-attachments/assets/a5be58c2-21a9-4994-847e-f84854e04183)

![abstracao4](https://github.com/user-attachments/assets/55df408b-55a6-4319-afe7-a38bb1e2a93b)

```
 
### 🎯 Classes Abstratas vs Interfaces
 
| Característica | Classe Abstrata | Interface |
|----------------|-----------------|-----------|
| **Instanciação** | Não pode ser instanciada | Não pode ser instanciada |
| **Métodos** | Pode ter abstratos e concretos | Só abstratos (Java 7) ou default (Java 8+) |
| **Atributos** | Pode ter atributos normais | Só constantes (public static final) |
| **Herança** | Uma classe por vez (extends) | Múltiplas (implements) |
| **Quando usar** | Relação "É UM" com código compartilhado | Contrato/capacidade que classes devem ter |
 
### 🎯 Quando Usar Abstração?
 
✅ **Use classe abstrata quando:**
- Há código comum a ser compartilhado
- Quer forçar subclasses a implementarem certos métodos
- Existe relação hierárquica clara
 
✅ **Use interface quando:**
- Quer definir um contrato sem implementação
- Classes não relacionadas precisam da mesma capacidade
- Precisa de herança múltipla de comportamento
 
 
## Comparação Entre os Pilares
 
| Pilar | Foco | Pergunta Chave | Palavra-chave Java | Objetivo |
|-------|------|----------------|-------------------|----------|
| **1. Encapsulamento** | Proteger dados | "Como proteger?" | `private`, `public`, `protected` | Segurança e integridade |
| **2. Herança** | Reutilizar código | "É um tipo de?" | `extends` | Hierarquia e reuso |
| **3. Polimorfismo** | Flexibilidade | "Pode agir como?" | `@Override`, `implements` | Comportamento dinâmico |
| **4. Abstração** | Ocultar complexidade | "O que faz?" | `abstract`, `interface` | Simplificação e contratos |
 
### Ordem de Aprendizado (Didática)
```
1. ENCAPSULAMENTO 🔒
   ↓
   Aprende a proteger dados
   ↓
2. HERANÇA 👨‍👩‍👧
   ↓
   Aprende a reutilizar código
   ↓
3. POLIMORFISMO 🎭
   ↓
   Aprende a criar comportamentos flexíveis
   ↓
4. ABSTRAÇÃO 🎯
   ↓
   Aprende a criar contratos e simplificar complexidade
```
 
## Exemplo Completo Integrando Todos os Pilares
```java
// 4. ABSTRAÇÃO: Classe abstrata definindo o contrato
public abstract class Conta {
    // 1. ENCAPSULAMENTO: atributos protegidos
    protected String numero;
    protected String titular;
    protected double saldo;
    
    public Conta(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }
    
    // 4. ABSTRAÇÃO: Método abstrato (cada tipo de conta calcula diferente)
    public abstract void calcularRendimento();
    public abstract String getTipoConta();
    
    // 1. ENCAPSULAMENTO: Getters controlam acesso
    public double getSaldo() {
        return saldo;
    }
    
    public String getTitular() {
        return titular;
    }
    
    // Métodos com validação (ENCAPSULAMENTO)
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado");
        } else {
            System.out.println("Valor inválido!");
        }
    }
    
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado");
            return true;
        }
        System.out.println("Saque não autorizado!");
        return false;
    }
    
    public void exibirDados() {
        System.out.println("Tipo: " + getTipoConta());
        System.out.println("Conta: " + numero);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: R$ " + String.format("%.2f", saldo));
    }
}
 
// 2. HERANÇA: ContaPoupanca herda de Conta
public class ContaPoupanca extends Conta {
    private double taxaRendimento;
    
    public ContaPoupanca(String numero, String titular, double taxaRendimento) {
        super(numero, titular); // 2. HERANÇA: chamando construtor do pai
        this.taxaRendimento = taxaRendimento;
    }
    
    // 3. POLIMORFISMO: Sobrescrevendo método abstrato
    @Override
    public void calcularRendimento() {
        double rendimento = saldo * taxaRendimento;
        saldo += rendimento;
        System.out.println("Rendimento de R$ " + String.format("%.2f", rendimento) + " aplicado");
    }
    
    @Override
    public String getTipoConta() {
        return "Conta Poupança";
    }
    
    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Taxa de rendimento: " + (taxaRendimento * 100) + "%");
    }
}
 
// 2. HERANÇA: ContaCorrente herda de Conta
public class ContaCorrente extends Conta {
    private double limite;
    private double taxaManutencao;
    
    public ContaCorrente(String numero, String titular, double limite) {
        super(numero, titular);
        this.limite = limite;
        this.taxaManutencao = 15.00;
    }
    
    // 3. POLIMORFISMO: Sobrescrevendo método do pai
    @Override
    public boolean sacar(double valor) {
        double saldoDisponivel = saldo + limite;
        if (valor > 0 && valor <= saldoDisponivel) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado (limite usado)");
            return true;
        }
        System.out.println("Saque não autorizado!");
        return false;
    }
    
    @Override
    public void calcularRendimento() {
        // Conta corrente não tem rendimento, mas cobra taxa
        saldo -= taxaManutencao;
        System.out.println("Taxa de manutenção de R$ " + taxaManutencao + " cobrada");
    }
    
    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
    
    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Limite: R$ " + limite);
        System.out.println("Saldo disponível: R$ " + (saldo + limite));
    }
    
    // 1. ENCAPSULAMENTO: método para consultar limite
    public double getLimiteDisponivel() {
        return limite + saldo;
    }
}
 
// 4. ABSTRAÇÃO: Interface para investimentos
interface Investimento {
    void investir(double valor);
    double consultarInvestimentos();
    double calcularRetorno();
}
 
// 2. HERANÇA + 4. ABSTRAÇÃO (implementa interface)
public class ContaInvestimento extends Conta implements Investimento {
    private double saldoInvestido;
    private double taxaRetorno;
    
    public ContaInvestimento(String numero, String titular, double taxaRetorno) {
        super(numero, titular);
        this.saldoInvestido = 0.0;
        this.taxaRetorno = taxaRetorno;
    }
    
    @Override
    public void calcularRendimento() {
        double rendimento = saldoInvestido * taxaRetorno;
        saldoInvestido += rendimento;
        System.out.println("Rendimento dos investimentos: R$ " + String.format("%.2f", rendimento));
    }
    
    @Override
    public String getTipoConta() {
        return "Conta Investimento";
    }
    
    // Implementando interface Investimento
    @Override
    public void investir(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            saldoInvestido += valor;
            System.out.println("R$ " + valor + " investido com sucesso!");
        } else {
            System.out.println("Não foi possível investir!");
        }
    }
    
    @Override
    public double consultarInvestimentos() {
        return saldoInvestido;
    }
    
    @Override
    public double calcularRetorno() {
        return saldoInvestido * taxaRetorno;
    }
    
    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Investido: R$ " + String.format("%.2f", saldoInvestido));
        System.out.println("Taxa de retorno: " + (taxaRetorno * 100) + "%");
        System.out.println("Retorno estimado mensal: R$ " + String.format("%.2f", calcularRetorno()));
    }
}
 
// Sistema bancário usando TODOS os pilares
public class Banco {
    // 1. ENCAPSULAMENTO: lista privada
    private ArrayList<Conta> contas;
    private String nome;
    
    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }
    
    public void adicionarConta(Conta conta) {
        contas.add(conta);
        System.out.println("✅ Conta cadastrada: " + conta.getTitular());
    }
    
    // 3. POLIMORFISMO: método funciona com qualquer tipo de Conta
    public void aplicarRendimentosMensais() {
        System.out.println("\n=== " + nome + " - APLICANDO RENDIMENTOS ===");
        for (Conta conta : contas) {
            System.out.println("\n" + conta.getTipoConta() + " - " + conta.getTitular());
            conta.calcularRendimento(); // 3. POLIMORFISMO em ação!
        }
    }
    
    public void exibirTodasContas() {
        System.out.println("\n=== " + nome + " - TODAS AS CONTAS ===");
        for (Conta conta : contas) {
            System.out.println();
            conta.exibirDados();
            System.out.println("---");
        }
    }
    
    public double calcularTotalAtivos() {
        double total = 0;
        for (Conta conta : contas) {
            total += conta.getSaldo();
            if (conta instanceof ContaInvestimento) {
                total += ((ContaInvestimento) conta).consultarInvestimentos();
            }
        }
        return total;
    }
}
 
// Testando TODOS OS PILARES INTEGRADOS
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco POO");
        
        System.out.println("=== CRIANDO CONTAS ===\n");
        
        // Criando diferentes tipos de conta (2. HERANÇA + 4. ABSTRAÇÃO)
        ContaPoupanca poupanca = new ContaPoupanca("001-P", "João Silva", 0.005);
        ContaCorrente corrente = new ContaCorrente("002-C", "Maria Santos", 1000.00);
        ContaInvestimento investimento = new ContaInvestimento("003-I", "Carlos Souza", 0.01);
        
        // Operações
        System.out.println("=== OPERAÇÕES INICIAIS ===\n");
        poupanca.depositar(5000.00);
        corrente.depositar(3000.00);
        investimento.depositar(10000.00);
        investimento.investir(7000.00);
        
        // Adicionando ao banco (3. POLIMORFISMO: todos são Conta)
        banco.adicionarConta(poupanca);
        banco.adicionarConta(corrente);
        banco.adicionarConta(investimento);
        
        // Exibindo estado inicial
        banco.exibirTodasContas();
        
        // Aplicando rendimentos (3. POLIMORFISMO em ação!)
        banco.aplicarRendimentosMensais();
        
        // Exibindo estado final
        banco.exibirTodasContas();
        
        // Usando 1. ENCAPSULAMENTO para acessar dados
        System.out.println("\n=== RESUMO DO BANCO ===");
        System.out.println("Total de ativos: R$ " + String.format("%.2f", banco.calcularTotalAtivos()));
    }
}
```
 
---
 
## 📚 Resumo Final
 
### Checklist dos 4 Pilares ✅
 
#### 1️⃣ Encapsulamento
- [ ] Atributos estão `private`?
- [ ] Tenho getters/setters quando necessário?
- [ ] Adiciono validações nos setters?
- [ ] Controlo como os dados são modificados?
 
#### 2️⃣ Herança
- [ ] Uso herança apenas para relação "É UM"?
- [ ] Evito hierarquias muito profundas?
- [ ] Uso `super` corretamente?
- [ ] Considero composição quando apropriado?
 
#### 3️⃣ Polimorfismo
- [ ] Sobrescrevo métodos com `@Override`?
- [ ] Aproveito polimorfismo para flexibilidade?
- [ ] Uso arrays/listas de tipo base para objetos variados?
- [ ] Implemento comportamentos específicos por tipo?
 
#### 4️⃣ Abstração
- [ ] Uso classes abstratas para código compartilhado?
- [ ] Uso interfaces para contratos?
- [ ] Escondo complexidade desnecessária?
- [ ] Foco no "O QUÊ", não no "COMO"?
 
### Relação Entre os Pilares
```
ENCAPSULAMENTO (Base)
    ↓
Protege os dados
    ↓
HERANÇA
    ↓
Reutiliza código protegido
    ↓
POLIMORFISMO
    ↓
Permite comportamentos flexíveis
    ↓
ABSTRAÇÃO (Topo)
    ↓
Simplifica e define contratos
```
 
### Quando Usar Cada Pilar?
```
┌─────────────────────────────────────────────────┐
│  "Preciso proteger meus dados?"                 │
│  → Use ENCAPSULAMENTO (private + getters/setters)│
├─────────────────────────────────────────────────┤
│  "Preciso reutilizar código?"                   │
│  → Use HERANÇA (extends)                        │
├─────────────────────────────────────────────────┤
│  "Preciso de comportamentos flexíveis?"         │
│  → Use POLIMORFISMO (@Override)                 │
├─────────────────────────────────────────────────┤
│  "Preciso definir contratos/ocultar complexidade?"│
│  → Use ABSTRAÇÃO (abstract class / interface)  │
└─────────────────────────────────────────────────┘
```
 
