# Aula - Operadores Aritméticos e Classe Math

Nesta aula, vamos aprender a realizar cálculos matemáticos no Java, desde o básico até funções avançadas.

---

## 1️⃣ Operadores Aritméticos Básicos

São os símbolos que usamos para fazer contas simples.

| Operador | Nome | Exemplo | Resultado |
| :---: | :--- | :--- | :--- |
| `+` | Adição | `10 + 5` | `15` |
| `-` | Subtração | `10 - 5` | `5` |
| `*` | Multiplicação | `10 * 5` | `50` |
| `/` | Divisão | `10 / 2` | `5` |
| `%` | Módulo (Resto) | `10 % 3` | `1` |

### ⚠️ A Pegadinha da Divisão (Inteiro vs Decimal)

No Java, se você dividir dois números inteiros, o resultado será inteiro (ele corta a vírgula!).
* `5 / 2` resulta em **2** (e não 2.5).
* `5.0 / 2` resulta em **2.5** (agora sim!).

> **Dica:** Se precisar de precisão, certifique-se de que pelo menos um dos números seja `double` ou `float`.

---

## 2️⃣ Classe Math (Matemática)

Para operações mais complexas (como potência, raiz quadrada, seno, cosseno), o Java já traz uma biblioteca pronta chamada `Math`. Não precisa importar, ela já vem "embutida".

| Comando | O que faz? | Exemplo Prático |
| :--- | :--- | :--- |
| `Math.pow(base, exp)` | Potência (Exponenciação) | `Math.pow(2, 3)` = 8.0 |
| `Math.sqrt(x)` | Raiz Quadrada | `Math.sqrt(25)` = 5.0 |
| `Math.abs(x)` | Valor Absoluto (Módulo) | `Math.abs(-10)` = 10 |
| `Math.random()` | Gera número aleatório (0.0 a 1.0) | Sorteios |
| `Math.ceil(x)` | Arredonda para CIMA (Teto) | `Math.ceil(4.1)` = 5.0 |
| `Math.floor(x)` | Arredonda para BAIXO (Chão) | `Math.floor(4.9)` = 4.0 |
| `Math.round(x)` | Arredonda tradicionalmente | `Math.round(4.6)` = 5 |

---

## 3️⃣ Ordem de Precedência

Assim como na matemática da escola, o Java resolve as contas numa ordem específica:

1.  Parênteses `( )`
2.  Potências e Raízes
3.  Multiplicação `*`, Divisão `/` e Módulo `%`
4.  Soma `+` e Subtração `-`

**Exemplo:**
```java
double x = 10 + 5 * 2;   // Resultado: 20 (primeiro multiplica)
double y = (10 + 5) * 2; // Resultado: 30 (primeiro soma)
