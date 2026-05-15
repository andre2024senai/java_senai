
# 🎯 EXERCÍCIO PRÁTICO - Thymeleaf do Zero ao CRUD

## 📋 Desafio Progressivo (Do Fácil ao Difícil)

---

## NÍVEL 1: APENAS MOSTRAR 👀

### Desafio 1.1: Mostrar um nome
Você tem no Controller:
```java
model.addAttribute("nome", "João Silva");
```

**Sua missão:** Mostrar o nome na página

**HTML de exemplo:**
```html
<h1>Bem-vindo, [COLOQUE O NOME AQUI]</h1>
```

<details>
<summary>💡 Ver Resposta</summary>

```html
<h1>Bem-vindo, <span th:text="${nome}">Nome</span></h1>
```
ou
```html
<h1 th:text="'Bem-vindo, ' + ${nome}">Bem-vindo, Nome</h1>
```

</details>

---

### Desafio 1.2: Mostrar idade com texto
Controller:
```java
model.addAttribute("idade", 25);
```

**HTML de exemplo:**
```html
<p>Você tem [IDADE] anos</p>
```

<details>
<summary>💡 Ver Resposta</summary>

```html
<p th:text="'Você tem ' + ${idade} + ' anos'">Você tem X anos</p>
```
ou
```html
<p>Você tem <span th:text="${idade}">X</span> anos</p>
```

</details>

---

## NÍVEL 2: LISTAS E LOOPS 🔄

### Desafio 2.1: Lista simples
Controller:
```java
List<String> frutas = Arrays.asList("Maçã", "Banana", "Laranja");
model.addAttribute("frutas", frutas);
```

**HTML de exemplo:**
```html
<ul>
    <li>Maçã</li>
    <li>Banana</li>
    <li>Laranja</li>
</ul>
```

**Sua missão:** Fazer isso com loop (th:each)

<details>
<summary>💡 Ver Resposta</summary>

```html
<ul>
    <li th:each="fruta : ${frutas}" th:text="${fruta}">Fruta</li>
</ul>
```

</details>

---

### Desafio 2.2: Tabela de alunos
Controller:
```java
// Imagine que você tem uma lista de alunos
List<Aluno> alunos = List.of(
    new Aluno("Ana", 8.5),
    new Aluno("Bruno", 7.0),
    new Aluno("Carlos", 9.2)
);
model.addAttribute("alunos", alunos);
```

**Sua missão:** Criar tabela mostrando nome e nota

<details>
<summary>💡 Ver Resposta</summary>

```html
<table>
    <thead>
        <tr>
            <th>Nome</th>
            <th>Nota</th>
        </tr>
    </thead>
    <tbody>
        <tr th:each="aluno : ${alunos}">
            <td th:text="${aluno.nome}">Nome</td>
            <td th:text="${aluno.nota}">Nota</td>
        </tr>
    </tbody>
</table>
```

</details>

---

## NÍVEL 3: CONDIÇÕES 🤔

### Desafio 3.1: Mostrar mensagem se lista vazia
```java
model.addAttribute("produtos", new ArrayList<>());
```

**Sua missão:** 
- Se lista vazia → mostrar "Nenhum produto cadastrado"
- Se lista tem itens → mostrar a tabela

<details>
<summary>💡 Ver Resposta</summary>

```html
<!-- Mensagem quando vazio -->
<p th:if="${produtos.empty}">Nenhum produto cadastrado</p>

<!-- Tabela quando tem itens -->
<table th:if="${!produtos.empty}">
    <tr th:each="produto : ${produtos}">
        <td th:text="${produto.nome}">Nome</td>
    </tr>
</table>
```

</details>

---

### Desafio 3.2: Aprovado ou Reprovado
Lista de alunos com notas. Mostrar badge:
- Verde "Aprovado" se nota >= 7
- Vermelho "Reprovado" se nota < 7

<details>
<summary>💡 Ver Resposta</summary>

```html
<tr th:each="aluno : ${alunos}">
    <td th:text="${aluno.nome}">Nome</td>
    <td th:text="${aluno.nota}">Nota</td>
    <td>
        <span th:if="${aluno.nota >= 7}" style="color: green">
            ✅ Aprovado
        </span>
        <span th:if="${aluno.nota < 7}" style="color: red">
            ❌ Reprovado
        </span>
    </td>
</tr>
```

</details>

---

## NÍVEL 4: LINKS DINÂMICOS 🔗

### Desafio 4.1: Link para editar
Criar link de editar que vai para `/editar/ID`

<details>
<summary>💡 Ver Resposta</summary>

```html
<tr th:each="pessoa : ${pessoas}">
    <td th:text="${pessoa.id}">1</td>
    <td th:text="${pessoa.nome}">Nome</td>
    <td>
        <a th:href="@{/editar/{id}(id=${pessoa.id})}">✏️ Editar</a>
    </td>
</tr>
```

</details>

---

### Desafio 4.2: Link para excluir com confirmação
Link que pergunta "Tem certeza?" antes de excluir

<details>
<summary>💡 Ver Resposta</summary>

```html
<a th:href="@{/excluir/{id}(id=${pessoa.id})}" 
   onclick="return confirm('Tem certeza que deseja excluir?')">
    🗑️ Excluir
</a>
```

</details>

---

## NÍVEL 5: FORMULÁRIOS 📝

### Desafio 5.1: Form de cadastro simples
Criar formulário para cadastrar produto (nome e preço)

<details>
<summary>💡 Ver Resposta</summary>

```html
<form th:action="@{/salvar}" th:object="${produto}" method="post">
    <label>Nome:</label>
    <input type="text" th:field="*{nome}" required>
    
    <label>Preço:</label>
    <input type="number" th:field="*{preco}" step="0.01" required>
    
    <button type="submit">💾 Salvar</button>
</form>
```

</details>

---

### Desafio 5.2: Form que serve para NOVO e EDITAR
O mesmo formulário deve:
- Mostrar "Nova Pessoa" se for cadastro novo
- Mostrar "Editar Pessoa" se for edição
- Botão muda de "Salvar" para "Atualizar"

<details>
<summary>💡 Ver Resposta</summary>

```html
<!-- Título dinâmico -->
<h1 th:text="${pessoa.id == null} ? 'Nova Pessoa' : 'Editar Pessoa'">
    Formulário
</h1>

<form th:action="@{/salvar}" th:object="${pessoa}" method="post">
    <!-- Campo oculto para o ID -->
    <input type="hidden" th:field="*{id}">
    
    <label>Nome:</label>
    <input type="text" th:field="*{nome}" required>
    
    <label>Idade:</label>
    <input type="number" th:field="*{idade}" required>
    
    <!-- Botão dinâmico -->
    <button type="submit" 
            th:text="${pessoa.id == null} ? '💾 Salvar' : '💾 Atualizar'">
        Salvar
    </button>
</form>
```

</details>

---

## NÍVEL 6: DESAFIO FINAL - CRUD COMPLETO 🏆

### Sua Missão: Criar sistema de Livros

**Entidade Livro:**
- id (Long)
- titulo (String)
- autor (String)
- ano (Integer)
- preco (Double)

**Requisitos:**

1. **lista.html:**
   - Tabela com todos os livros
   - Colunas: Título, Autor, Ano, Preço
   - Botão "Adicionar Livro"
   - Botões "Editar" e "Excluir" em cada linha
   - Mensagem quando não há livros

2. **form.html:**
   - Formulário com todos os campos
   - Detectar automaticamente se é novo ou edição
   - Título muda: "Novo Livro" ou "Editar Livro"
   - Botão muda: "Salvar" ou "Atualizar"

3. **Extras:**
   - Se preço > 100, mostrar tag "CARO" em vermelho
   - Se ano < 2000, mostrar tag "CLÁSSICO"
   - Contador de quantos livros estão cadastrados

---

## ✅ GABARITO DO DESAFIO FINAL

<details>
<summary>🎯 Ver Solução Completa</summary>

### lista.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Lista de Livros</title>
</head>
<body>
    <h1>📚 Biblioteca</h1>
    
    <div>
        <span>Total de livros: </span>
        <strong th:text="${livros.size()}">0</strong>
    </div>
    
    <a th:href="@{/novo}">➕ Adicionar Livro</a>
    
    <!-- Mensagem quando vazio -->
    <p th:if="${livros.empty}">
        Nenhum livro cadastrado ainda.
    </p>
    
    <!-- Tabela quando tem livros -->
    <table th:if="${!livros.empty}">
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Autor</th>
                <th>Ano</th>
                <th>Preço</th>
                <th>Tags</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="livro : ${livros}">
                <td th:text="${livro.id}">1</td>
                <td th:text="${livro.titulo}">Título</td>
                <td th:text="${livro.autor}">Autor</td>
                <td th:text="${livro.ano}">2020</td>
                <td th:text="'R$ ' + ${livro.preco}">R$ 50</td>
                <td>
                    <span th:if="${livro.preco > 100}" 
                          style="color: red; font-weight: bold;">
                        💰 CARO
                    </span>
                    <span th:if="${livro.ano < 2000}" 
                          style="color: blue; font-weight: bold;">
                        📖 CLÁSSICO
                    </span>
                </td>
                <td>
                    <a th:href="@{/editar/{id}(id=${livro.id})}">
                        ✏️ Editar
                    </a>
                    <a th:href="@{/excluir/{id}(id=${livro.id})}"
                       onclick="return confirm('Excluir este livro?')">
                        🗑️ Excluir
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
```

### form.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Formulário de Livro</title>
</head>
<body>
    <h1 th:text="${livro.id == null} ? '📚 Novo Livro' : '✏️ Editar Livro'">
        Formulário
    </h1>
    
    <form th:action="@{/salvar}" th:object="${livro}" method="post">
        <input type="hidden" th:field="*{id}">
        
        <div>
            <label>Título:</label>
            <input type="text" th:field="*{titulo}" required>
        </div>
        
        <div>
            <label>Autor:</label>
            <input type="text" th:field="*{autor}" required>
        </div>
        
        <div>
            <label>Ano:</label>
            <input type="number" th:field="*{ano}" 
                   min="1000" max="2100" required>
        </div>
        
        <div>
            <label>Preço:</label>
            <input type="number" th:field="*{preco}" 
                   step="0.01" min="0" required>
        </div>
        
        <button type="submit" 
                th:text="${livro.id == null} ? '💾 Salvar' : '💾 Atualizar'">
            Salvar
        </button>
        
        <a th:href="@{/}">❌ Cancelar</a>
    </form>
</body>
</html>
```

</details>

---

## 🎯 CONCLUSÃO

Parabéns! Se você completou todos os desafios, você domina:

✅ Mostrar dados com `th:text`  
✅ Fazer loops com `th:each`  
✅ Criar condições com `th:if`  
✅ Montar links com `th:href`  
✅ Criar formulários com `th:field`  
✅ Construir um CRUD completo!  

**Você está pronto para criar qualquer aplicação web com Thymeleaf!** 🚀
