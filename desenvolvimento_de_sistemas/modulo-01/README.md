# Entrada de Dados em Java com Scanner

Este material apresenta o uso da classe `Scanner` para entrada de dados em Java, abordando os principais **tipos primitivos** utilizados no dia a dia da programação.

---

## 1️⃣ Importando e criando o Scanner

Todo programa Java que utiliza entrada de dados pelo teclado começa com a importação da classe `Scanner`.

```java
import java.util.Scanner;

public class EntradaDados {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // entradas aqui

        teclado.close();
    }
}

##2️⃣ Tipos primitivos + Scanner (um por um)
🔹 byte – inteiro pequeno

📌 Intervalo: -128 a 127

System.out.print("Digite um valor byte: ");
byte valorByte = teclado.nextByte();


🧠 Exemplo prático: idade pequena, nível, quantidade limitada.
