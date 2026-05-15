
# 🔗 COMO AS PÁGINAS SÃO LINKADAS AUTOMATICAMENTE

## 📌 PERGUNTA 2: As páginas são linkadas automaticamente?

### Resposta: SIM! O Spring faz isso por **CONVENÇÃO** (regras automáticas)

---

## 🎯 CONVENÇÃO SOBRE CONFIGURAÇÃO

O Spring Boot usa o princípio **"Convention over Configuration"** (Convenção sobre Configuração).

Isso significa: **SE você seguir as regras, funciona automaticamente!**

---

## 📂 REGRA 1: Localização dos Arquivos

### ✅ ONDE os arquivos HTML devem estar:

```
src/main/resources/templates/
```

**Por quê?**
- O Spring Boot **automaticamente** procura arquivos HTML nessa pasta
- É uma **convenção** do framework
- Você NÃO precisa configurar nada!

---

## 📝 REGRA 2: Nome do Arquivo = String Retornada

### Como funciona:

```java
@GetMapping("/")
public String listar() {
    return "lista";  // ← Nome do arquivo (SEM .html)
}
```

**O Spring automaticamente:**
1. Pega a string `"lista"`
2. Adiciona `.html` no final
3. Procura em `src/main/resources/templates/`
4. Encontra: `templates/lista.html`
5. Renderiza e envia pro navegador

---

## 🔄 MAPEAMENTO AUTOMÁTICO

### Tabela de Conversão:

| Controller retorna | Spring busca | Caminho completo |
|-------------------|--------------|------------------|
| `return "lista"` | `lista.html` | `src/main/resources/templates/lista.html` |
| `return "form"` | `form.html` | `src/main/resources/templates/form.html` |
| `return "produtos/lista"` | `produtos/lista.html` | `src/main/resources/templates/produtos/lista.html` |
| `return "admin/dashboard"` | `admin/dashboard.html` | `src/main/resources/templates/admin/dashboard.html` |

---

## 💻 EXEMPLOS PRÁTICOS

### Exemplo 1: Página Simples

**Controller:**
```java
@GetMapping("/")
public String home() {
    return "index";
}
```

**Spring procura automaticamente:**
```
src/main/resources/templates/index.html
```

---

### Exemplo 2: Com Subpastas

**Controller:**
```java
@GetMapping("/produtos")
public String listarProdutos() {
    return "produtos/lista";
}
```

**Spring procura:**
```
src/main/resources/templates/produtos/lista.html
```

**Estrutura de pastas:**
```
templates/
├── index.html
├── form.html
└── produtos/
    ├── lista.html
    └── detalhes.html
```

---

## 🚫 O QUE NÃO PRECISA FAZER

### ❌ NÃO precisa escrever:

```java
return "lista.html";  // ❌ Desnecessário
return "templates/lista";  // ❌ Errado
return "src/main/resources/templates/lista.html";  // ❌ Muito errado
```

### ✅ Basta:

```java
return "lista";  // ✅ Simples e correto
```

---

## 🔗 LINKS ENTRE PÁGINAS

### Como fazer links no HTML:

**Thymeleaf:**
```html
<a th:href="@{/}">Ir para Home</a>
<a th:href="@{/novo}">Adicionar</a>
<a th:href="@{/editar/5}">Editar ID 5</a>
```

**HTML puro (também funciona):**
```html
<a href="/">Ir para Home</a>
<a href="/novo">Adicionar</a>
```

**Por que usar `th:href="@{...}"`?**
- Funciona mesmo se a aplicação não estiver na raiz (ex: /meuapp/)
- Adiciona automaticamente o contexto da aplicação
- Mais seguro e profissional

---

## 🎯 FLUXO COMPLETO DE UMA REQUISIÇÃO

```
┌─────────────────────────────────────────────────────────┐
│  1. USUÁRIO digita: http://localhost:8080/              │
└────────────────────────┬────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│  2. SPRING encontra o Controller com @GetMapping("/")   │
├─────────────────────────────────────────────────────────┤
│  @GetMapping("/")                                       │
│  public String listar(Model model) {                    │
│      model.addAttribute("pessoas", lista);              │
│      return "lista";  ← Retorna STRING                  │
│  }                                                       │
└────────────────────────┬────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│  3. SPRING procura automaticamente:                     │
│     templates/lista.html                                │
└────────────────────────┬────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│  4. THYMELEAF processa o HTML                           │
│     - Substitui ${...} pelos valores                    │
│     - Processa th:each, th:if, etc.                     │
└────────────────────────┬────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│  5. NAVEGADOR recebe HTML puro processado               │
│     (sem th:, sem ${}, só HTML/CSS/JS)                  │
└─────────────────────────────────────────────────────────┘
```

---

## 🌐 EXEMPLO COMPLETO: Sistema de Navegação

### Controller:
```java
@Controller
public class PessoaController {

    // Página inicial
    @GetMapping("/")
    public String home() {
        return "index";  // → templates/index.html
    }
    
    // Lista de pessoas
    @GetMapping("/pessoas")
    public String listar(Model model) {
        model.addAttribute("pessoas", service.listar());
        return "lista";  // → templates/lista.html
    }
    
    // Formulário novo
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "form";  // → templates/form.html
    }
    
    // Sobre
    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";  // → templates/sobre.html
    }
}
```

### Navegação no HTML:
```html
<!-- index.html -->
<nav>
    <a th:href="@{/}">Home</a>
    <a th:href="@{/pessoas}">Pessoas</a>
    <a th:href="@{/novo}">Adicionar</a>
    <a th:href="@{/sobre}">Sobre</a>
</nav>
```

**Resultado:**
- Clicou em "Pessoas" → chama `/pessoas` → retorna `"lista"` → mostra `lista.html`
- Clicou em "Adicionar" → chama `/novo` → retorna `"form"` → mostra `form.html`

---

## 🎨 REFERENCIANDO CSS/JS

### Arquivos estáticos vão em: `src/main/resources/static/`

**Estrutura:**
```
src/main/resources/
├── templates/
│   ├── lista.html
│   └── form.html
└── static/
    ├── css/
    │   └── style.css
    ├── js/
    │   └── script.js
    └── images/
        └── logo.png
```

### No HTML:
```html
<!DOCTYPE html>
<html>
<head>
    <!-- Referencia CSS -->
    <link rel="stylesheet" th:href="@{/css/style.css}">
    
    <!-- Ou sem Thymeleaf: -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <!-- Referencia imagem -->
    <img th:src="@{/images/logo.png}" alt="Logo">
    
    <!-- Referencia JS -->
    <script th:src="@{/js/script.js}"></script>
</body>
</html>
```

**Spring automaticamente serve arquivos de `/static/` na URL raiz!**

---

## ❓ PERGUNTAS FREQUENTES

### P: Preciso configurar algo para as páginas funcionarem?
**R:** NÃO! Se colocar em `templates/` e retornar o nome correto, funciona automaticamente.

### P: Posso usar subpastas dentro de templates/?
**R:** SIM! `return "admin/usuarios"` → `templates/admin/usuarios.html`

### P: Preciso reiniciar a aplicação quando mudo o HTML?
**R:** Depende:
- Com DevTools: NÃO (atualiza automaticamente)
- Sem DevTools: SIM (precisa reiniciar)

### P: O que acontece se o arquivo não existir?
**R:** Erro 500 (Internal Server Error) ou Whitelabel Error Page

---

## ✅ CHECKLIST DE VERIFICAÇÃO

Para as páginas funcionarem automaticamente:

- [ ] Arquivos `.html` estão em `src/main/resources/templates/`
- [ ] Controller retorna o nome do arquivo (sem `.html`)
- [ ] Nome retornado está correto (case-sensitive!)
- [ ] Dependência Thymeleaf está no `pom.xml`
- [ ] HTML tem `<html xmlns:th="http://www.thymeleaf.org">`

---

## 🎯 RESUMÃO

**SIM, as páginas são linkadas AUTOMATICAMENTE porque:**

1. ✅ Spring Boot procura em `templates/` por convenção
2. ✅ Você só retorna o nome do arquivo (sem `.html`)
3. ✅ Spring adiciona `.html` e procura automaticamente
4. ✅ Thymeleaf processa e retorna para o navegador

**É uma "mágica" do framework, mas seguindo regras claras!** ✨
