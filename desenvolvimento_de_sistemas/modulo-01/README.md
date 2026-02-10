1️⃣ Importando e criando o Scanner

Todo programa com entrada de dados começa assim:

import java.util.Scanner;

public class EntradaDados {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // entradas aqui

        teclado.close();
    }
}


💡 Dica didática:
Explique que o Scanner é como um “leitor do teclado”.

2️⃣ Tipos primitivos + Scanner (um por um)
🔹 byte – inteiro pequeno

📌 Intervalo: -128 a 127

System.out.print("Digite um valor byte: ");
byte valorByte = teclado.nextByte();


🧠 Exemplo prático: idade pequena, nível, quantidade limitada.

🔹 short – inteiro médio

📌 Intervalo: -32.768 a 32.767

System.out.print("Digite um valor short: ");
short valorShort = teclado.nextShort();

🔹 int – inteiro mais usado

📌 Intervalo: cerca de -2 bilhões a +2 bilhões

System.out.print("Digite um valor inteiro: ");
int valorInt = teclado.nextInt();


🧠 Use para: idade, quantidade, número de alunos, etc.

🔹 long – inteiro grande

📌 Usado para números muito grandes (CPF, ID, população)

System.out.print("Digite um valor long: ");
long valorLong = teclado.nextLong();


⚠️ Dica: sem pontos ou traços na digitação.

🔹 float – número decimal (menos preciso)

📌 Use , ou .?
👉 Java usa ponto (.)

System.out.print("Digite um valor float: ");
float valorFloat = teclado.nextFloat();


🧠 Exemplo: peso simples, média pequena.

🔹 double – número decimal (mais preciso)

📌 Mais usado que float

System.out.print("Digite um valor double: ");
double valorDouble = teclado.nextDouble();


🧠 Exemplo: salário, nota, média final.

🔹 char – um único caractere

📌 O Scanner não tem nextChar(), então usamos:

System.out.print("Digite um caractere: ");
char letra = teclado.next().charAt(0);


🧠 Explicação para os alunos:

next() → lê uma palavra

charAt(0) → pega o primeiro caractere

🔹 boolean – verdadeiro ou falso

📌 Só aceita true ou false

System.out.print("Digite true ou false: ");
boolean resposta = teclado.nextBoolean();


🧠 Exemplo: ligado/desligado, aprovado/reprovado.

3️⃣ Exemplo completo (todos juntos)

Esse aqui é ótimo pra aula prática 👇

import java.util.Scanner;

public class TiposComScanner {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Byte: ");
        byte b = teclado.nextByte();

        System.out.print("Short: ");
        short s = teclado.nextShort();

        System.out.print("Int: ");
        int i = teclado.nextInt();

        System.out.print("Long: ");
        long l = teclado.nextLong();

        System.out.print("Float: ");
        float f = teclado.nextFloat();

        System.out.print("Double: ");
        double d = teclado.nextDouble();

        System.out.print("Char: ");
        char c = teclado.next().charAt(0);

        System.out.print("Boolean (true/false): ");
        boolean bo = teclado.nextBoolean();

        System.out.println("\n--- Valores digitados ---");
        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(c);
        System.out.println(bo);

        teclado.close();
    }
}

4️⃣ Dicas importantes para evitar erro em aula ⚠️

✔ Decimal sempre com ponto (7.5)
✔ boolean → só true ou false
✔ char → apenas 1 letra
✔ Se digitar texto quando espera número → InputMismatchException (explique que é erro de tipo)
