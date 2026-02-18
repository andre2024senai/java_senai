# 🔁 Exemplos de Loops (Repetição)

Repositório com exemplos práticos das principais estruturas de repetição em Java.

---

## 📚 Índice

- [For Básico](#-1-for-básico)
- [For Reverso](#-2-for-reverso)
- [For Pares](#-3-for-pares)
- [For Aninhado](#-4-for-aninhado)
- [For Aninhado em 3 Níveis](#-5-for-aninhado-em-3-níveis)
- [While](#-6-while)
- [While com Guarda](#-7-while-com-guarda-valor-sentinela)
- [Do While](#-8-do-while)

---

## 🔁 1. For Básico

Imprime os números de 1 a 10.

```java
public class LoopFor {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}
```

---

## 🔁 2. For Reverso

Imprime os números de 10 a 1 (contagem regressiva).

```java
public class LoopForReverso {
    public static void main(String[] args) {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
        }
    }
}
```

---

## 🔁 3. For Pares

Imprime os números pares de 0 a 10, incrementando de 2 em 2.

```java
public class LoopForPares {
    public static void main(String[] args) {
        for (int i = 0; i <= 10; i += 2) {
            System.out.println(i);
        }
    }
}
```

---

## 🔁 4. For Aninhado

Imprime os números de 1 a 100 usando dois `for` aninhados.

> 💡 **For aninhado** significa um `for` dentro de outro. O loop interno é executado completamente a cada iteração do loop externo.

```java
public class LoopForAninhado {
    public static void main(String[] args) {
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(++k);
            }
        }
    }
}
```

---

## 🔁 5. For Aninhado em 3 Níveis

Imprime os números de 1 a 1000 usando três `for` aninhados.

```java
public class LoopForAninhadoAninhado {
    public static void main(String[] args) {
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int m = 0; m < 10; m++) {
                    System.out.println(++k);
                }
            }
        }
    }
}
```

---

## 🔁 6. While

Imprime os números de 1 a 10.

> 💡 Use `while` quando **não sabe quantas vezes** o loop irá repetir. A condição é verificada **antes** de executar o bloco.

```java
public class LoopWhile {
    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            i++;
            System.out.println(i);
        }
    }
}
```

---

## 🔁 7. While com Guarda (Valor Sentinela)

Conta e imprime quantos valores foram digitados pelo usuário até que ele digite `x` para parar.

> 💡 O **valor sentinela** (ou valor de guarda) é um valor especial usado para sinalizar o fim da entrada de dados — neste caso, a letra `x`.

```java
import javax.swing.JOptionPane;

public class LoopWhileGuarda {
    public static void main(String[] args) {
        int i = 0;
        String entrada = "";

        while (!entrada.equals("x") && !entrada.equals("X")) {
            entrada = JOptionPane.showInputDialog(
                "Digite qualquer coisa ou x para parar"
            );
            i++;
            System.out.println(i);
        }
    }
}
```

---

## 🔁 8. Do While

Imprime os números de 1 a 10.

> 💡 Use `do while` quando o bloco precisa ser executado **pelo menos uma vez**. A condição é verificada **após** executar o bloco.

```java
public class LoopDoWhile {
    public static void main(String[] args) {
        int i = 0;
        do {
            i++;
            System.out.println(i);
        } while (i < 10);
    }
}
```

---

## 🆚 Comparativo das Estruturas

| Estrutura  | Quando usar | A condição é testada |
|------------|-------------|----------------------|
| `for`      | Quando se sabe o número exato de repetições | Antes do bloco |
| `while`    | Quando não se sabe quantas repetições ocorrerão | Antes do bloco |
| `do while` | Quando o bloco deve executar pelo menos uma vez | Após o bloco |
