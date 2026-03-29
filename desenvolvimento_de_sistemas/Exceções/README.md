
# Exceções em Java - Guia de Referência

## 📚 Índice

1. [O que são Exceções?](#o-que-são-exceções)
2. [Try-Catch Básico](#try-catch-básico)
3. [Múltiplos Catch](#múltiplos-catch)
4. [Bloco Finally](#bloco-finally)
5. [Try-With-Resources](#try-with-resources)
6. [Lançando Exceções (throw)](#lançando-exceções-throw)
7. [Checked vs Unchecked](#checked-vs-unchecked)
8. [Exceções Personalizadas](#exceções-personalizadas)
9. [Boas Práticas](#boas-práticas)
10. [Preparação para JDBC](#preparação-para-jdbc)

---

## O que são Exceções?

Exceções são **eventos anormais** que ocorrem durante a execução de um programa e **interrompem** o fluxo normal de instruções.

### Problema sem Tratamento

```java
public static void dividir(int a, int b) {
    int resultado = a / b;  // Se b = 0, programa TRAVA!
    System.out.println("Resultado: " + resultado);
    System.out.println("Esta linha nunca executa se der erro");
}
```

### Solução com Tratamento

```java
public static void dividir(int a, int b) {
    try {
        int resultado = a / b;
        System.out.println("Resultado: " + resultado);
    } catch (ArithmeticException e) {
        System.out.println("ERRO: Não pode dividir por zero!");
    }
    System.out.println("Programa continua normalmente");
}
```

---

## Try-Catch Básico

### Estrutura

```java
try {
    // Código que PODE gerar exceção
    
} catch (TipoDeExcecao e) {
    // Código que trata a exceção
}
```

### Exemplo Prático

```java
try {
    String texto = null;
    int tamanho = texto.length();  // NullPointerException
    
} catch (NullPointerException e) {
    System.out.println("ERRO: Texto está nulo!");
    System.out.println("Mensagem técnica: " + e.getMessage());
}
```

### Métodos Úteis da Exceção

```java
catch (Exception e) {
    e.getMessage();        // Mensagem de erro
    e.printStackTrace();   // Imprime pilha de chamadas (útil para debug)
    e.toString();          // Nome da exceção + mensagem
    e.getClass().getName(); // Nome da classe da exceção
}
```

---

## Múltiplos Catch

Quando um bloco de código pode gerar **diferentes tipos** de exceções:

```java
try {
    String numero = "abc";
    int valor = Integer.parseInt(numero);  // NumberFormatException
    int resultado = 10 / valor;            // ArithmeticException
    
} catch (NumberFormatException e) {
    System.out.println("ERRO: Não é um número válido!");
    
} catch (ArithmeticException e) {
    System.out.println("ERRO: Divisão por zero!");
    
} catch (Exception e) {
    System.out.println("ERRO INESPERADO: " + e.getMessage());
}
```

### ⚠️ ORDEM IMPORTA!

```java
// ❌ ERRADO - Exception é mais genérica, deve vir por último
try {
    // código
} catch (Exception e) {           // Pega TUDO
    
} catch (IOException e) {          // Nunca será executado!
    
}

// ✅ CORRETO - Do mais específico para o mais genérico
try {
    // código
} catch (FileNotFoundException e) {   // Mais específica
    
} catch (IOException e) {              // Específica
    
} catch (Exception e) {                // Genérica
    
}
```

---

## Bloco Finally

Código que **SEMPRE** executa, independente se houve exceção ou não.

### Quando Usar?

- Fechar arquivos
- Fechar conexões com banco de dados
- Liberar recursos
- Registrar logs

### Exemplo

```java
Scanner scanner = null;

try {
    scanner = new Scanner(System.in);
    String entrada = scanner.nextLine();
    
} catch (Exception e) {
    System.out.println("Erro: " + e.getMessage());
    
} finally {
    // Este bloco SEMPRE executa!
    if (scanner != null) {
        scanner.close();
        System.out.println("Scanner fechado com segurança");
    }
}
```

### Ordem de Execução

```java
try {
    System.out.println("1. Try");
    // Se não houver erro, vai para o finally
    // Se houver erro, vai para o catch
    
} catch (Exception e) {
    System.out.println("2. Catch (só se houver erro)");
    
} finally {
    System.out.println("3. Finally (SEMPRE executa)");
}

System.out.println("4. Continua o programa");
```

---

## Try-With-Resources

Forma **moderna** de garantir que recursos sejam fechados automaticamente.

### Jeito Antigo (Manual)

```java
BufferedReader reader = null;

try {
    reader = new BufferedReader(new FileReader("arquivo.txt"));
    String linha = reader.readLine();
    
} catch (IOException e) {
    System.out.println("Erro: " + e.getMessage());
    
} finally {
    try {
        if (reader != null) {
            reader.close();  // Fecha manualmente
        }
    } catch (IOException e) {
        System.out.println("Erro ao fechar!");
    }
}
```

### Jeito Moderno (Automático) ✅

```java
// O recurso fecha AUTOMATICAMENTE ao sair do try
try (BufferedReader reader = new BufferedReader(new FileReader("arquivo.txt"))) {
    
    String linha = reader.readLine();
    System.out.println(linha);
    
    // Não precisa de finally!
    
} catch (IOException e) {
    System.out.println("Erro: " + e.getMessage());
}
```

### Múltiplos Recursos

```java
try (
    FileReader fr = new FileReader("entrada.txt");
    BufferedReader br = new BufferedReader(fr);
    FileWriter fw = new FileWriter("saida.txt")
) {
    // Usa os recursos
    
} catch (IOException e) {
    System.out.println("Erro: " + e.getMessage());
}

// Todos os recursos fecham automaticamente!
```

### Requisitos

O recurso DEVE implementar a interface `AutoCloseable`:

```java
public class MeuRecurso implements AutoCloseable {
    
    @Override
    public void close() {
        System.out.println("Recurso fechado!");
    }
}
```

---

## Lançando Exceções (throw)

Use `throw` para **criar e lançar** uma exceção.

### Validação de Dados

```java
public static void validarIdade(int idade) {
    if (idade < 0) {
        throw new IllegalArgumentException("Idade não pode ser negativa!");
    }
    
    if (idade > 120) {
        throw new IllegalArgumentException("Idade inválida!");
    }
    
    System.out.println("Idade válida: " + idade);
}

// Uso:
try {
    validarIdade(-5);
} catch (IllegalArgumentException e) {
    System.out.println("ERRO: " + e.getMessage());
}
```

### Validação de Email

```java
public static void validarEmail(String email) {
    if (email == null || email.isEmpty()) {
        throw new IllegalArgumentException("Email não pode estar vazio!");
    }
    
    if (!email.contains("@")) {
        throw new IllegalArgumentException("Email deve conter @");
    }
}
```

---

## Checked vs Unchecked

### Unchecked (RuntimeException)

**Não precisam** ser declaradas com `throws` nem tratadas obrigatoriamente.

```java
// Não precisa declarar 'throws'
public static void exemplo() {
    throw new NullPointerException();      // Unchecked
    throw new ArithmeticException();       // Unchecked
    throw new IllegalArgumentException();  // Unchecked
}
```

**Principais:**
- `NullPointerException`
- `ArithmeticException`
- `ArrayIndexOutOfBoundsException`
- `IllegalArgumentException`
- `IllegalStateException`

### Checked (Exception)

**DEVEM** ser declaradas com `throws` **OU** tratadas com try-catch.

```java
// ❌ ERRO DE COMPILAÇÃO - IOException não declarada
public static void ler() {
    FileReader reader = new FileReader("arquivo.txt");
}

// ✅ Opção 1: Declarar throws
public static void ler() throws IOException {
    FileReader reader = new FileReader("arquivo.txt");
}

// ✅ Opção 2: Tratar com try-catch
public static void ler() {
    try {
        FileReader reader = new FileReader("arquivo.txt");
    } catch (IOException e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
```

**Principais:**
- `IOException`
- `SQLException`
- `FileNotFoundException`
- `ClassNotFoundException`

### Propagação com throws

```java
public static void metodo1() throws IOException {
    metodo2();  // Propaga a exceção
}

public static void metodo2() throws IOException {
    metodo3();  // Propaga a exceção
}

public static void metodo3() throws IOException {
    throw new IOException("Erro aqui!");  // Lança a exceção
}

// No main, trata:
public static void main(String[] args) {
    try {
        metodo1();
    } catch (IOException e) {
        System.out.println("Exceção capturada: " + e.getMessage());
    }
}
```

---

## Exceções Personalizadas

Crie suas próprias exceções para situações específicas do seu sistema.

### Estrutura Básica

```java
// Herda de Exception (checked) ou RuntimeException (unchecked)
public class MinhaExcecao extends Exception {
    
    public MinhaExcecao(String mensagem) {
        super(mensagem);
    }
}
```

### Exemplo: Saldo Insuficiente

```java
public class SaldoInsuficienteException extends Exception {
    private double saldoAtual;
    private double valorSaque;
    
    public SaldoInsuficienteException(double saldoAtual, double valorSaque) {
        super("Saldo insuficiente! Saldo: R$ " + saldoAtual + 
              ", Saque: R$ " + valorSaque);
        this.saldoAtual = saldoAtual;
        this.valorSaque = valorSaque;
    }
    
    public double getSaldoAtual() {
        return saldoAtual;
    }
    
    public double getValorSaque() {
        return valorSaque;
    }
}
```

### Uso

```java
public void sacar(double valor) throws SaldoInsuficienteException {
    if (valor > saldo) {
        throw new SaldoInsuficienteException(saldo, valor);
    }
    saldo -= valor;
}

// No main:
try {
    conta.sacar(1000.00);
} catch (SaldoInsuficienteException e) {
    System.out.println(e.getMessage());
    System.out.println("Saldo: R$ " + e.getSaldoAtual());
    System.out.println("Tentou sacar: R$ " + e.getValorSaque());
}
```

---

## Boas Práticas

### ✅ Faça

1. **Seja específico** nos catches
   ```java
   catch (FileNotFoundException e) {  // Específico
   ```

2. **Use try-with-resources** quando possível
   ```java
   try (Scanner sc = new Scanner(System.in)) {
   ```

3. **Mensagens claras** ao usuário
   ```java
   System.out.println("Arquivo não encontrado. Verifique o caminho.");
   ```

4. **Log de erros** técnicos
   ```java
   e.printStackTrace();  // Para debugging
   ```

5. **Valide dados** antes de processar
   ```java
   if (email == null) throw new IllegalArgumentException(...);
   ```

### ❌ Evite

1. **Catch vazio**
   ```java
   catch (Exception e) {
       // Nada aqui - NUNCA faça isso!
   }
   ```

2. **Capturar Exception** sem necessidade
   ```java
   catch (Exception e) {  // Muito genérico
   ```

3. **PrintStackTrace em produção**
   ```java
   e.printStackTrace();  // Só em desenvolvimento!
   ```

4. **Lançar exceptions sem mensagem**
   ```java
   throw new Exception();  // Sem mensagem é ruim
   ```

5. **Ignorar exceptions**
   ```java
   try {
       // código perigoso
   } catch (Exception e) {
       return null;  // Ignora o erro
   }
   ```

---

## Preparação para JDBC

### Como será com Banco de Dados

```java
// JDBC usa SQLException (checked exception)
import java.sql.*;

public class ExemploJDBC {
    
    public static void consultarClientes() {
        String url = "jdbc:mysql://localhost:3306/loja";
        String user = "root";
        String pass = "senha";
        
        // Try-with-resources fecha conexão automaticamente
        try (
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")
        ) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                System.out.println(nome + " - " + email);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

### Exceções Comuns em JDBC

| Exceção | Causa |
|---------|-------|
| `SQLException` | Erro genérico de SQL |
| "Access denied" | Usuário/senha incorretos |
| "Unknown database" | Banco não existe |
| "Communications link failure" | Banco não está rodando |
| "Table doesn't exist" | Tabela não encontrada |

---

## Resumo Rápido

```java
// 1. Try-catch básico
try {
    // código perigoso
} catch (Exception e) {
    // trata erro
}

// 2. Múltiplos catches
try {
    // código
} catch (IOException e) {
    // trata IO
} catch (SQLException e) {
    // trata SQL
}

// 3. Finally
try {
    // código
} catch (Exception e) {
    // trata
} finally {
    // sempre executa
}

// 4. Try-with-resources (RECOMENDADO)
try (Scanner sc = new Scanner(System.in)) {
    // usa recurso
} catch (Exception e) {
    // trata
}

// 5. Throw
if (invalido) {
    throw new IllegalArgumentException("Mensagem");
}

// 6. Throws
public void metodo() throws IOException {
    // pode lançar IOException
}

// 7. Exceção personalizada
public class MinhaExcecao extends Exception {
    public MinhaExcecao(String msg) {
        super(msg);
    }
}
```

---

## Exercícios Propostos

1. Crie um validador de CPF que lance exceção personalizada
2. Implemente uma calculadora que trate divisão por zero
3. Crie um sistema de login que lance exceção para senha inválida
4. Simule leitura de arquivo com try-with-resources
5. Crie uma classe Produto com validações que lancem exceções

---


**Professor:** Andre  
**Curso:** Desenvolvimento de Sistemas - SENAI  
**Ano:** 2026
