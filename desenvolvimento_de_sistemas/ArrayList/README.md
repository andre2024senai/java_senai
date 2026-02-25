# 📚 ArrayList em Java

> Guia completo sobre ArrayList - Estrutura de dados dinâmica essencial em Java

## 📋 Índice

- [O que é ArrayList?](#o-que-é-arraylist)
- [Por que usar ArrayList?](#por-que-usar-arraylist)
- [Declaração e Inicialização](#declaração-e-inicialização)
- [Métodos Principais](#métodos-principais)
- [Percorrendo ArrayList](#percorrendo-arraylist)
- [Array vs ArrayList](#array-vs-arraylist)
- [Exemplos Práticos](#exemplos-práticos)
- [Boas Práticas](#boas-práticas)
- [Recursos Adicionais](#recursos-adicionais)

---

## 🎯 O que é ArrayList?

**ArrayList** é uma classe da biblioteca `java.util` que implementa uma **lista dinâmica** (resizable array). Diferente de arrays tradicionais que têm tamanho fixo, o ArrayList pode crescer e diminuir automaticamente conforme necessário.

```java
import java.util.ArrayList;

ArrayList<String> nomes = new ArrayList<>();
```

### Características principais:

-  **Tamanho dinâmico** — cresce automaticamente
-  **Baseado em índices** — acesso por posição (0, 1, 2...)
-  **Permite duplicatas** — pode ter elementos repetidos
-  **Mantém ordem de inserção** — elementos ficam na ordem que foram adicionados
-  **Aceita null** — pode armazenar valores nulos
-  **Não sincronizado** — não é thread-safe por padrão

---

##  Por que usar ArrayList?

### Limitações do Array tradicional:

```java
// Array - tamanho FIXO
String[] frutas = new String[3];
frutas[0] = "Maçã";
frutas[1] = "Banana";
frutas[2] = "Laranja";
// frutas[3] = "Uva";   ERRO! Não pode adicionar mais elementos
```

### Solução com ArrayList:

```java
// ArrayList - tamanho DINÂMICO
ArrayList<String> frutas = new ArrayList<>();
frutas.add("Maçã");
frutas.add("Banana");
frutas.add("Laranja");
frutas.add("Uva");      //  OK! Adiciona sem problemas
frutas.add("Morango");  //  Continua crescendo!
```

---

## 🚀 Declaração e Inicialização

### Sintaxe básica:

```java
// Importar a classe
import java.util.ArrayList;

// Declaração com Generics (recomendado)
ArrayList<TipoDeDado> nomeLista = new ArrayList<>();
```

### Tipos comuns:

```java
// String
ArrayList<String> nomes = new ArrayList<>();

// Integer (não int!)
ArrayList<Integer> numeros = new ArrayList<>();

// Double
ArrayList<Double> precos = new ArrayList<>();

// Boolean
ArrayList<Boolean> flags = new ArrayList<>();

// Objetos personalizados
ArrayList<Produto> produtos = new ArrayList<>();
```

>  **Importante:** Use classes wrapper (`Integer`, `Double`, `Boolean`) ao invés de tipos primitivos (`int`, `double`, `boolean`)

### Com capacidade inicial:

```java
// Cria ArrayList com capacidade inicial de 50 elementos
ArrayList<String> lista = new ArrayList<>(50);
```

### Com valores iniciais (Java 9+):

```java
// Usando List.of() - LISTA IMUTÁVEL
List<String> cores = List.of("Vermelho", "Verde", "Azul");

// Convertendo para ArrayList mutável
ArrayList<String> coresMutavel = new ArrayList<>(List.of("Vermelho", "Verde", "Azul"));
```

---

## 🔧 Métodos Principais

###  Adicionar elementos

```java
ArrayList<String> frutas = new ArrayList<>();

// Adicionar no final
frutas.add("Maçã");        // [Maçã]
frutas.add("Banana");      // [Maçã, Banana]

// Adicionar em posição específica
frutas.add(1, "Laranja");  // [Maçã, Laranja, Banana]
```

###  Acessar elementos

```java
// Obter elemento por índice
String primeira = frutas.get(0);  // "Maçã"
String ultima = frutas.get(frutas.size() - 1);  // "Banana"

// Obter primeira e última posição (Java 21+)
String primeira = frutas.getFirst();  // "Maçã"
String ultima = frutas.getLast();     // "Banana"
```

###  Modificar elementos

```java
// Substituir elemento em uma posição
frutas.set(1, "Uva");  // [Maçã, Uva, Banana]
```

###  Remover elementos

```java
// Remover por índice
frutas.remove(0);           // Remove "Maçã"

// Remover por objeto
frutas.remove("Banana");    // Remove "Banana"

// Remover todos os elementos
frutas.clear();             // []
```

###  Informações sobre a lista

```java
// Tamanho da lista
int tamanho = frutas.size();

// Verificar se está vazia
boolean vazia = frutas.isEmpty();

// Verificar se contém elemento
boolean tem = frutas.contains("Maçã");

// Obter posição de um elemento
int indice = frutas.indexOf("Banana");  // Retorna -1 se não encontrar
```

###  Outros métodos úteis

```java
// Converter para array
String[] arrayFrutas = frutas.toArray(new String[0]);

// Criar cópia
ArrayList<String> copia = new ArrayList<>(frutas);

// Ordenar (ordem alfabética)
Collections.sort(frutas);

// Reverter ordem
Collections.reverse(frutas);

// Embaralhar
Collections.shuffle(frutas);
```

---

##  Percorrendo ArrayList

### 1️ For tradicional (com índice)

Use quando precisar do **índice** ou **modificar** elementos:

```java
ArrayList<String> nomes = new ArrayList<>();
nomes.add("Ana");
nomes.add("Bruno");
nomes.add("Carlos");

for (int i = 0; i < nomes.size(); i++) {
    System.out.println(i + ": " + nomes.get(i));
}
```

**Saída:**
```
0: Ana
1: Bruno
2: Carlos
```

### 2️ For-each (enhanced for)

Use quando só precisar **ler** os valores:

```java
for (String nome : nomes) {
    System.out.println(nome);
}
```

**Saída:**
```
Ana
Bruno
Carlos
```

### 3️ Iterator

```java
Iterator<String> it = nomes.iterator();
while (it.hasNext()) {
    String nome = it.next();
    System.out.println(nome);
}
```

### 4️ forEach com Lambda (Java 8+)

```java
nomes.forEach(nome -> System.out.println(nome));

// Ou usando method reference
nomes.forEach(System.out::println);
```

---

## ⚖️ Array vs ArrayList

| Característica | Array | ArrayList |
|----------------|-------|-----------|
| **Tamanho** | Fixo | Dinâmico (redimensionável) |
| **Declaração** | `int[] arr = new int[5]` | `ArrayList<Integer> list = new ArrayList<>()` |
| **Adicionar** | `arr[0] = 10` | `list.add(10)` |
| **Acessar** | `arr[0]` | `list.get(0)` |
| **Modificar** | `arr[0] = 20` | `list.set(0, 20)` |
| **Remover** | Complexo (manual) | `list.remove(0)` ou `list.remove(obj)` |
| **Tamanho** | `arr.length` | `list.size()` |
| **Tipos primitivos** |  Suporta |  Usa Wrappers (`Integer`, `Double`) |
| **Performance** | Mais rápido | Levemente mais lento |
| **Flexibilidade** | Baixa | Alta |

### Exemplo visual:

```java
// ARRAY - Fixo
int[] numeros = new int[3];
numeros[0] = 10;
numeros[1] = 20;
numeros[2] = 30;
// Tamanho: 3 (não muda)

// ARRAYLIST - Dinâmico
ArrayList<Integer> lista = new ArrayList<>();
lista.add(10);
lista.add(20);
lista.add(30);
lista.add(40);  //  Cresce automaticamente!
lista.add(50);  //  Continua crescendo!
// Tamanho: 5 (ajusta conforme necessário)
```

---

##  Exemplos Práticos

### Exemplo 1: Lista de Tarefas

```java
import java.util.ArrayList;
import java.util.Scanner;

public class ListaTarefas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tarefas = new ArrayList<>();
        
        while (true) {
            System.out.println("\n=== LISTA DE TAREFAS ===");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Remover tarefa");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    System.out.print("Digite a tarefa: ");
                    String tarefa = sc.nextLine();
                    tarefas.add(tarefa);
                    System.out.println(" Tarefa adicionada!");
                    break;
                    
                case 2:
                    if (tarefas.isEmpty()) {
                        System.out.println("Lista vazia!");
                    } else {
                        for (int i = 0; i < tarefas.size(); i++) {
                            System.out.println((i + 1) + ". " + tarefas.get(i));
                        }
                    }
                    break;
                    
                case 3:
                    System.out.print("Número da tarefa: ");
                    int num = sc.nextInt();
                    if (num > 0 && num <= tarefas.size()) {
                        tarefas.remove(num - 1);
                        System.out.println("✅ Tarefa removida!");
                    }
                    break;
                    
                case 4:
                    System.out.println("Encerrando...");
                    sc.close();
                    return;
            }
        }
    }
}
```

### Exemplo 2: Filtrar e Processar Dados

```java
import java.util.ArrayList;

public class FiltrarDados {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(12);
        numeros.add(8);
        numeros.add(20);
        numeros.add(3);
        
        // Filtrar apenas números maiores que 10
        ArrayList<Integer> maioresQue10 = new ArrayList<>();
        for (int num : numeros) {
            if (num > 10) {
                maioresQue10.add(num);
            }
        }
        
        System.out.println("Todos: " + numeros);
        System.out.println("Maiores que 10: " + maioresQue10);
        
        // Calcular soma
        int soma = 0;
        for (int num : numeros) {
            soma += num;
        }
        System.out.println("Soma total: " + soma);
    }
}
```

**Saída:**
```
Todos: [5, 12, 8, 20, 3]
Maiores que 10: [12, 20]
Soma total: 48
```

### Exemplo 3: ArrayList com Objetos Personalizados

```java
// Classe Produto
class Produto {
    String nome;
    double preco;
    
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    @Override
    public String toString() {
        return nome + " - R$ " + preco;
    }
}

// Uso
public class Estoque {
    public static void main(String[] args) {
        ArrayList<Produto> produtos = new ArrayList<>();
        
        produtos.add(new Produto("Notebook", 3500.00));
        produtos.add(new Produto("Mouse", 45.00));
        produtos.add(new Produto("Teclado", 150.00));
        
        System.out.println("=== PRODUTOS ===");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}
```

**Saída:**
```
=== PRODUTOS ===
Notebook - R$ 3500.0
Mouse - R$ 45.0
Teclado - R$ 150.0
```

---

##  Boas Práticas

### 1. Use Generics (sempre!)

```java
//  Evite (raw type)
ArrayList lista = new ArrayList();

//  Correto
ArrayList<String> lista = new ArrayList<>();
```

### 2. Prefira interface List quando possível

```java
//  Mais flexível
List<String> nomes = new ArrayList<>();

// Permite trocar implementação facilmente
List<String> nomes = new LinkedList<>();
```

### 3. Especifique capacidade inicial se souber o tamanho aproximado

```java
// Se você sabe que vai adicionar ~1000 elementos
ArrayList<String> lista = new ArrayList<>(1000);
```

### 4. Use métodos apropriados para cada situação

```java
//  Evite
if (lista.size() == 0) { }

// ✅ Melhor
if (lista.isEmpty()) { }
```

### 5. Cuidado ao remover durante iteração

```java
//  ERRADO - ConcurrentModificationException
for (String nome : nomes) {
    if (nome.startsWith("A")) {
        nomes.remove(nome);  //  Erro!
    }
}

//  CORRETO - Use Iterator
Iterator<String> it = nomes.iterator();
while (it.hasNext()) {
    String nome = it.next();
    if (nome.startsWith("A")) {
        it.remove();  //  OK
    }
}
```

### 6. Evite comparar com `==`

```java
ArrayList<String> lista1 = new ArrayList<>();
ArrayList<String> lista2 = new ArrayList<>();

lista1.add("Java");
lista2.add("Java");

//  Compara referências
if (lista1 == lista2) { }  // false

//  Compara conteúdo
if (lista1.equals(lista2)) { }  // true
```

---

## 📚 Recursos Adicionais

### Documentação oficial:
- [ArrayList - Java Docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html)
- [Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)

### Tutoriais recomendados:
- [W3Schools - Java ArrayList](https://www.w3schools.com/java/java_arraylist.asp)
- [GeeksforGeeks - ArrayList in Java](https://www.geeksforgeeks.org/arraylist-in-java/)

### Diferenças entre estruturas:
- **ArrayList** vs **LinkedList**: ArrayList é melhor para acesso aleatório; LinkedList para inserções/remoções frequentes
- **ArrayList** vs **Vector**: Vector é sincronizado (thread-safe), mas mais lento
- **ArrayList** vs **Array**: ArrayList é dinâmico e mais flexível

---

