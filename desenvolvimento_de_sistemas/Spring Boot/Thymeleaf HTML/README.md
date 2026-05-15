# 🎨 GUIA THYMELEAF PARA INICIANTES
## Os 5 Comandos que Você Precisa Saber

---

## 1️⃣ th:text - MOSTRAR TEXTO

**O que faz:** Coloca texto dentro de uma tag

### Exemplo Simples:
```html
<!-- HTML puro (fixo) -->
<p>João Silva</p>

<!-- Thymeleaf (dinâmico) -->
<p th:text="${pessoa.nome}">Nome aqui</p>
```

**Resultado no navegador:**
```html
<p>Maria Santos</p>  ← Veio do banco de dados!
```

### 💡 Explicação:
- `${pessoa.nome}` = "Vai no objeto pessoa e pega o nome"
- O texto dentro da tag `<p>Nome aqui</p>` é só um "placeholder" (exemplo)
- Quando a página carrega, o Thymeleaf SUBSTITUI pelo valor real

---

## 2️⃣ th:each - FAZER LOOP (REPETIR)

**O que faz:** Repete um elemento HTML para cada item de uma lista

### Exemplo Simples:
```html
<!-- Sem Thymeleaf: você teria que escrever cada linha -->
<tr><td>João</td><td>25</td></tr>
<tr><td>Maria</td><td>30</td></tr>
<tr><td>Pedro</td><td>28</td></tr>

<!-- Com Thymeleaf: uma linha só! -->
<tr th:each="pessoa : ${pessoas}">
    <td th:text="${pessoa.nome}">Nome</td>
    <td th:text="${pessoa.idade}">Idade</td>
</tr>
```

**Resultado:** Cria uma linha `<tr>` para CADA pessoa no banco!

### 💡 Explicação:
- `pessoa` = variável temporária (como em um for)
- `${pessoas}` = lista que vem do Controller
- É igual a um `for` do Java, mas no HTML!

### 🎯 Analogia:
```java
// Java
for (Pessoa pessoa : pessoas) {
    System.out.println(pessoa.getNome());
}

// Thymeleaf
<p th:each="pessoa : ${pessoas}" th:text="${pessoa.nome}"></p>
```

---

## 3️⃣ th:href - LINKS DINÂMICOS

**O que faz:** Cria links com partes que mudam

### Exemplo Simples:
```html
<!-- Link fixo (HTML puro) -->
<a href="/editar/5">Editar</a>

<!-- Link dinâmico (Thymeleaf) -->
<a th:href="@{/editar/{id}(id=${pessoa.id})}">Editar</a>
```

**Se pessoa.id = 5, vira:**
```html
<a href="/editar/5">Editar</a>
```

**Se pessoa.id = 10, vira:**
```html
<a href="/editar/10">Editar</a>
```

### 💡 Explicação:
- `@{...}` = sintaxe do Thymeleaf para URLs
- `{id}` = parte variável da URL
- `(id=${pessoa.id})` = substitui {id} pelo valor real

---

## 4️⃣ th:field - CAMPOS DE FORMULÁRIO

**O que faz:** Liga um input ao objeto Java (mágica do Spring!)

### Exemplo Simples:
```html
<!-- Formulário normal -->
<form action="/salvar" method="post">
    <input type="text" name="nome">
    <input type="number" name="idade">
</form>

<!-- Formulário com Thymeleaf -->
<form th:action="@{/salvar}" th:object="${pessoa}" method="post">
    <input type="text" th:field="*{nome}">
    <input type="number" th:field="*{idade}">
</form>
```

### 💡 Explicação:
- `th:object="${pessoa}"` = "Este form trabalha com objeto pessoa"
- `th:field="*{nome}"` = Liga este input ao campo pessoa.nome
- O Spring AUTOMATICAMENTE:
  - Preenche o input com pessoa.nome (na edição)
  - Envia o valor para pessoa.setNome() (ao salvar)

### ✨ Benefícios:
- **name** é criado automaticamente
- **value** é preenchido automaticamente
- **id** é gerado automaticamente

---

## 5️⃣ th:if - MOSTRAR SE (CONDICIONAL)

**O que faz:** Mostra ou esconde elementos conforme uma condição

### Exemplo Simples:
```html
<!-- Mostra SE a lista estiver vazia -->
<p th:if="${pessoas.empty}">
    Nenhuma pessoa cadastrada ainda
</p>

<!-- Mostra SE a lista NÃO estiver vazia -->
<table th:if="${!pessoas.empty}">
    <!-- tabela aqui -->
</table>
```

### Outro exemplo:
```html
<!-- Mostra texto diferente na edição -->
<h1 th:if="${pessoa.id == null}">Nova Pessoa</h1>
<h1 th:if="${pessoa.id != null}">Editar Pessoa</h1>
```

### 💡 Explicação:
- Funciona como um `if` do Java
- Se a condição for `true`, mostra o elemento
- Se for `false`, NÃO mostra (como se não existisse)

---

## 🎯 RESUMÃO DOS 5 COMANDOS

| Comando | O que faz | Exemplo |
|---------|-----------|---------|
| `th:text` | Mostra texto | `<p th:text="${nome}">` |
| `th:each` | Faz loop | `<tr th:each="p : ${pessoas}">` |
| `th:href` | Link dinâmico | `<a th:href="@{/editar/{id}(id=${p.id})}">` |
| `th:field` | Campo de form | `<input th:field="*{nome}">` |
| `th:if` | Mostra se... | `<div th:if="${lista.empty}">` |

---

## 📝 EXERCÍCIO PRÁTICO

Transforme este HTML fixo em Thymeleaf dinâmico:

### HTML Fixo:
```html
<table>
    <tr>
        <td>João Silva</td>
        <td>25 anos</td>
        <td><a href="/editar/1">Editar</a></td>
    </tr>
</table>
```

### Sua vez! Tente criar a versão Thymeleaf:
```html
<table>
    <tr th:each="pessoa : ${pessoas}">
        <td th:text="${pessoa.nome}">Nome</td>
        <td th:text="${pessoa.idade} + ' anos'">Idade</td>
        <td>
            <a th:href="@{/editar/{id}(id=${pessoa.id})}">Editar</a>
        </td>
    </tr>
</table>
```

---

## 🚀 PRÓXIMO NÍVEL (Depois que dominarem os 5)

Quando estiverem confortáveis, ensine:
- `th:action` - URL de formulário
- `th:object` - Objeto vinculado ao form
- `th:attr` - Atributos dinâmicos
- `th:classappend` - Adicionar classes CSS
- `th:unless` - Inverso do th:if

---

## 💡 DICA DE OURO

**Thymeleaf não quebra o HTML!**

Você pode abrir o arquivo `.html` direto no navegador (sem rodar o Spring) e ele mostra os textos de exemplo!

```html
<p th:text="${pessoa.nome}">João Silva</p>
```

- **Com Spring rodando:** Mostra o nome do banco
- **Abrindo direto:** Mostra "João Silva"

Isso é ótimo para designers/front-end trabalharem!

---

## ✅ CHECKLIST DO ALUNO

Marque quando dominar cada comando:

- [ ] Consigo usar `th:text` para mostrar dados
- [ ] Consigo usar `th:each` para fazer loops
- [ ] Consigo usar `th:href` para criar links dinâmicos
- [ ] Consigo usar `th:field` em formulários
- [ ] Consigo usar `th:if` para condições
- [ ] Consegui fazer o CRUD completo funcionando!

---

## 🎓 MENSAGEM FINAL

**Lembre-se:**
- Thymeleaf é só HTML com "super poderes"
- Você NÃO está aprendendo uma linguagem nova
- Está só adicionando `th:` em tags que já conhece
- 95% do seu código continua sendo HTML/CSS normal

**Se souber HTML, você JÁ SABE Thymeleaf!** 🎉
