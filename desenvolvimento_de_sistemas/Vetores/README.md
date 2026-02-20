# Exemplo Completo de Vetores em Java

Este projeto demonstra os principais conceitos de **vetores (arrays) em Java**, incluindo:

- Vetor unidimensional (1D)
- Soma de elementos
- Busca linear
- Ordenação (Bubble Sort)
- Busca binária
- Vetor bidimensional (Matriz)
- Operações com matriz

---

## 1. Vetor Unidimensional (Array 1D)

### Declaração e Inicialização

```java
int[] numeros = {40, 15, 70, 5, 30, 90, 25};
````

---

### Percorrendo com for e .length

.length retorna o tamanho do vetor (não usa parênteses).
```java
for (int i = 0; i < numeros.length; i++) {
    System.out.println("numeros[" + i + "] = " + numeros[i]);
}
````

---

### Percorrendo com for-each
```java
for (int num : numeros) {
    System.out.print(num + "  ");
}
````

---
## 2. Soma dos Elementos do Vetor
```java
int soma = 0;

for (int i = 0; i < numeros.length; i++) {
    soma += numeros[i];
}

System.out.println("Soma total: " + soma);
System.out.println("Média: " + (soma / numeros.length));
```
---

## 3. Busca Linear (Sequencial)

Percorre o vetor do início ao fim até encontrar o valor.
```java
int valorBuscado = 70;
int posicaoEncontrada = -1;

for (int i = 0; i < numeros.length; i++) {
    if (numeros[i] == valorBuscado) {
        posicaoEncontrada = i;
        break;
    }
}
```
✔ Se encontrar → retorna a posição
❌ Se não encontrar → retorna -1

---

## 4. Ordenação — Bubble Sort

Ordena comparando elementos adjacentes.
```java
for (int i = 0; i < numeros.length - 1; i++) {
    for (int j = 0; j < numeros.length - 1 - i; j++) {
        if (numeros[j] > numeros[j + 1]) {
            int aux = numeros[j];
            numeros[j] = numeros[j + 1];
            numeros[j + 1] = aux;
        }
    }
}
```
Funciona trocando os elementos fora de ordem até o vetor ficar ordenado.
---

## 5. Busca Binária (Vetor Ordenado)

O vetor precisa estar ordenado!
```java
int alvo = 30;
int inicio = 0;
int fim = numeros.length - 1;
int resultado = -1;

while (inicio <= fim) {
    int meio = (inicio + fim) / 2;

    if (numeros[meio] == alvo) {
        resultado = meio;
        break;
    } else if (numeros[meio] < alvo) {
        inicio = meio + 1;
    } else {
        fim = meio - 1;
    }
}
```

Divide o vetor ao meio a cada comparação.
---

## 6. Vetor Bidimensional (Matriz)
Declaração da Matriz
```java
int[][] matriz = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

### Percorrendo a Matriz
```java
for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        System.out.printf("%4d", matriz[i][j]);
    }
    System.out.println();
}
```
#### matriz.length → número de linhas
#### matriz[i].length → número de colunas
---

### 7. Soma dos Elementos da Matriz
```java
int somaMatriz = 0;

for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        somaMatriz += matriz[i][j];
    }
}
```
---

## 8. Busca em Matriz
```java
int valorMatriz = 6;
boolean achou = false;

for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        if (matriz[i][j] == valorMatriz) {
            System.out.println("Encontrado em [" + i + "][" + j + "]");
            achou = true;
        }
    }
}
```
