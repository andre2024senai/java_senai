
# 🎨 CSS NO THYMELEAF - Guia Completo

## 📌 PERGUNTA 3: Na tag <style>, seria CSS puro?

### Resposta: SIM! CSS é **SEMPRE** CSS puro, independente de onde está!

---

## 🎯 3 FORMAS DE USAR CSS NO THYMELEAF

### 1️⃣ CSS INLINE (dentro do HTML)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <style>
        /* CSS PURO - funciona normalmente! */
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            font-family: Arial, sans-serif;
        }
        
        .btn {
            padding: 10px 20px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 5px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
    <button class="btn">Clique</button>
</body>
</html>
```

**✅ Vantagens:**
- Tudo em um arquivo só
- Fácil de compartilhar
- Não precisa configurar nada

**❌ Desvantagens:**
- Arquivo grande
- Não reutiliza CSS em outras páginas
- Dificulta manutenção

---

### 2️⃣ CSS EXTERNO (arquivo separado) - RECOMENDADO! ⭐

**Estrutura de pastas:**
```
src/main/resources/
├── templates/
│   ├── lista.html
│   └── form.html
└── static/
    └── css/
        └── style.css
```

**CSS (style.css):**
```css
/* CSS PURO - arquivo separado */
body {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 40px;
}

.btn-primary {
    background: #667eea;
    color: white;
    padding: 12px 30px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
}

.btn-primary:hover {
    background: #5568d3;
    transform: translateY(-2px);
}
```

**HTML (lista.html):**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Lista</title>
    
    <!-- REFERENCIANDO O CSS EXTERNO -->
    <link rel="stylesheet" th:href="@{/css/style.css}">
    <!--                      ↑                      -->
    <!--              Thymeleaf processa              -->
    
    <!-- OU sem Thymeleaf (também funciona): -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div class="container">
        <button class="btn-primary">Clique</button>
    </div>
</body>
</html>
```

**✅ Vantagens:**
- CSS reutilizável em todas as páginas
- HTML fica mais limpo
- Fácil de organizar e manter
- Pode ter cache no navegador (mais rápido)

**❌ Desvantagens:**
- Precisa criar pasta `static/css/`
- Mais um arquivo para gerenciar

---

### 3️⃣ CSS INLINE NOS ELEMENTOS (style="...")

```html
<!-- CSS direto no elemento -->
<div style="background: red; color: white; padding: 10px;">
    Isso é vermelho!
</div>

<p style="font-size: 20px; font-weight: bold;">
    Texto grande e negrito
</p>

<!-- Pode usar Thymeleaf para CSS dinâmico! -->
<div th:style="'background: ' + ${cor} + '; padding: 10px;'">
    Cor dinâmica!
</div>
```

**✅ Quando usar:**
- Estilos únicos em um elemento específico
- CSS dinâmico baseado em variáveis

**❌ Desvantagens:**
- Dificulta manutenção
- Não reutilizável
- Mistura HTML com CSS

---

## 🎨 THYMELEAF + CSS DINÂMICO

### Exemplo 1: Classe condicional

```html
<!-- Adiciona classe 'aprovado' ou 'reprovado' -->
<tr th:each="aluno : ${alunos}"
    th:class="${aluno.nota >= 7} ? 'aprovado' : 'reprovado'">
    <td th:text="${aluno.nome}">Nome</td>
    <td th:text="${aluno.nota}">Nota</td>
</tr>
```

**CSS:**
```css
.aprovado {
    background-color: #d4edda;
    color: #155724;
}

.reprovado {
    background-color: #f8d7da;
    color: #721c24;
}
```

---

### Exemplo 2: Cor dinâmica

```html
<!-- Controller: model.addAttribute("corFundo", "#667eea"); -->

<div th:style="'background-color: ' + ${corFundo}">
    Fundo colorido!
</div>
```

---

### Exemplo 3: Adicionar classe dinamicamente

```html
<button th:classappend="${ativo} ? 'btn-success' : 'btn-danger'">
    Status
</button>
```

**Resultado se ativo = true:**
```html
<button class="btn-success">Status</button>
```

**Resultado se ativo = false:**
```html
<button class="btn-danger">Status</button>
```

---

## 📚 FRAMEWORKS CSS + THYMELEAF

### Bootstrap (Framework CSS)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <!-- Bootstrap via CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" 
          rel="stylesheet">
</head>
<body>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Idade</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="pessoa : ${pessoas}">
                    <td th:text="${pessoa.nome}">Nome</td>
                    <td th:text="${pessoa.idade}">Idade</td>
                </tr>
            </tbody>
        </table>
        
        <a th:href="@{/novo}" class="btn btn-primary">
            Adicionar Pessoa
        </a>
    </div>
</body>
</html>
```

**✅ Vantagens:**
- Interface profissional rapidamente
- Componentes prontos (botões, tabelas, modals)
- Responsivo automaticamente

---

## 🎯 ESTRUTURA RECOMENDADA PARA PROJETOS

```
src/main/resources/
├── static/
│   ├── css/
│   │   ├── style.css          ← CSS principal
│   │   └── components.css     ← CSS de componentes
│   ├── js/
│   │   └── script.js
│   └── images/
│       └── logo.png
└── templates/
    ├── fragments/
    │   ├── header.html        ← Cabeçalho reutilizável
    │   └── footer.html        ← Rodapé reutilizável
    ├── lista.html
    └── form.html
```

---

## 🔧 EXEMPLO COMPLETO: Sistema com CSS Externo

### style.css (src/main/resources/static/css/style.css)
```css
/* Reset básico */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* Corpo */
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    min-height: 100vh;
    padding: 40px 20px;
}

/* Container principal */
.container {
    max-width: 1200px;
    margin: 0 auto;
    background: white;
    border-radius: 20px;
    padding: 40px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
}

/* Título */
h1 {
    color: #667eea;
    text-align: center;
    margin-bottom: 30px;
}

/* Tabela */
table {
    width: 100%;
    border-collapse: collapse;
}

thead {
    background: #667eea;
    color: white;
}

th, td {
    padding: 15px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

tr:hover {
    background: #f5f5f5;
}

/* Botões */
.btn {
    padding: 12px 30px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    text-decoration: none;
    display: inline-block;
    transition: all 0.3s ease;
}

.btn-primary {
    background: #667eea;
    color: white;
}

.btn-primary:hover {
    background: #5568d3;
    transform: translateY(-2px);
}

.btn-edit {
    background: #4CAF50;
    color: white;
    padding: 8px 16px;
}

.btn-delete {
    background: #f44336;
    color: white;
    padding: 8px 16px;
}
```

### lista.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Lista de Pessoas</title>
    <link rel="stylesheet" th:href="@{/css/style.css}">
</head>
<body>
    <div class="container">
        <h1>Sistema de Cadastro</h1>
        
        <a th:href="@{/novo}" class="btn btn-primary">
            ➕ Adicionar Pessoa
        </a>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Idade</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="pessoa : ${pessoas}">
                    <td th:text="${pessoa.id}">1</td>
                    <td th:text="${pessoa.nome}">Nome</td>
                    <td th:text="${pessoa.idade}">Idade</td>
                    <td>
                        <a th:href="@{/editar/{id}(id=${pessoa.id})}" 
                           class="btn btn-edit">
                            ✏️ Editar
                        </a>
                        <a th:href="@{/excluir/{id}(id=${pessoa.id})}" 
                           class="btn btn-delete">
                            🗑️ Excluir
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```

---

## ✅ RESUMO DAS 3 FORMAS

| Método | Onde fica | Quando usar |
|--------|-----------|-------------|
| **Inline (tag style)** | Dentro do `<head>` | Protótipos rápidos, páginas únicas |
| **Externo (arquivo .css)** | `static/css/` | Projetos reais (RECOMENDADO) |
| **No elemento (style="")** | No próprio elemento | Estilos únicos ou dinâmicos |

---

## 💡 DICAS DE OURO

1. **Use CSS externo** para projetos sérios
2. **CSS é sempre CSS puro** - Thymeleaf não muda isso
3. **Thymeleaf serve para adicionar classes/estilos dinamicamente**
4. **Frameworks como Bootstrap funcionam perfeitamente**
5. **Arquivos em static/ são servidos automaticamente**

---

## 🎯 CONCLUSÃO

**Sim, na tag `<style>` é CSS puro!**

Thymeleaf **NÃO** altera o CSS. Ele apenas:
- Processa atributos `th:style`, `th:class`, `th:classappend`
- Permite CSS dinâmico baseado em variáveis
- Mas o CSS em si é 100% puro e normal!

**Você pode usar TODO seu conhecimento de CSS normalmente!** 🎨
