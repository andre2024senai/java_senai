#  Estruturas de Repetição

Repositório com exemplos práticos das principais estruturas de repetição em Java, utilizando a classe `Scanner` para entrada de dados pelo usuário.

---

##  Objetivo

Demonstrar o uso das estruturas:

- `while`
- `for`
- `do while`

Cada exemplo contém:

- Entrada de dados
- Estrutura de repetição
- Aplicação prática
- Boas práticas (como fechamento do `Scanner`)

---

##   While

###  Quando usar?

Utilizamos `while` quando **não sabemos quantas vezes** a repetição irá acontecer.
A condição é testada **antes** de executar o bloco.

###  Exemplo: Somar números até o usuário digitar 0
```java
import java.util.Scanner;

public class ExemploWhile {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int valorDigitado;
        int acumulador = 0;

        System.out.print("Digite um número (0 para sair): ");
        valorDigitado = leitor.nextInt();

        while (valorDigitado != 0) {
            acumulador += valorDigitado;
            System.out.print("Digite outro número (0 para sair): ");
            valorDigitado = leitor.nextInt();
        }

        System.out.println("Soma total: " + acumulador);
        leitor.close();
    }
}
```

##  For

###  Quando usar?

Utilizamos `for` quando **sabemos exatamente quantas vezes** a repetição deve acontecer.
Possui três partes importantes:

- Inicialização
- Condição
- Incremento

### Exemplo: Tabuada de um número
```java
import java.util.Scanner;

public class ExemploFor {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite um número para ver a tabuada: ");
        int numeroBase = entrada.nextInt();

        for (int contador = 1; contador <= 10; contador++) {
            int resultadoMultiplicacao = numeroBase * contador;
            System.out.println(numeroBase + " x " + contador + " = " + resultadoMultiplicacao);
        }

        entrada.close();
    }
}
```

---

##  Do While

###  Quando usar?

Utilizamos `do while` quando o bloco precisa ser **executado pelo menos uma vez**, independentemente da condição.
A condição é testada **após** a execução do bloco.

###  Exemplo: Validação de senha
```java
import java.util.Scanner;

public class ExemploDoWhile {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int senhaCorreta = 1234;
        int senhaInformada;

        do {
            System.out.print("Digite a senha: ");
            senhaInformada = teclado.nextInt();

            if (senhaInformada != senhaCorreta) {
                System.out.println("Senha incorreta! Tente novamente.");
            }
        } while (senhaInformada != senhaCorreta);

        System.out.println("Acesso liberado!");
        teclado.close();
    }
}
```
