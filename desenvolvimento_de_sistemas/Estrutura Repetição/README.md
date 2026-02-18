# Estruturas de Repetição em Java

Repositório com exemplos práticos das principais estruturas de repetição em Java, utilizando a classe `Scanner` para entrada de dados pelo usuário.

## 🎯 Objetivo

Demonstrar o uso das seguintes estruturas:
* **while**
* **for**
* **do while**

Cada exemplo contém:
1.  Entrada de dados
2.  Estrutura de repetição
3.  Aplicação prática
4.  Boas práticas (como fechamento do Scanner)

---

## 🔁 1. While

### 📌 Quando usar?
Utilizamos `while` quando **não sabemos** quantas vezes a repetição irá acontecer. A condição é testada **antes** de executar o bloco.



### 🧠 Exemplo: Somar números até o usuário digitar 0

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
