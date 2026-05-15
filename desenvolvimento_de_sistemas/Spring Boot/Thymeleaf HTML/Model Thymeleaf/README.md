
# 🔗 COMO FUNCIONA A LIGAÇÃO: Controller ↔ Thymeleaf

## 📌 PERGUNTA 1: De onde vem "${pessoa.nome}"?

### Resposta: Vem do **Model** no Controller!

---

## 🎯 FLUXO COMPLETO (Passo a Passo)

### PASSO 1: Controller adiciona dados no Model

```java
@Controller
public class PessoaController {

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        
        // 1. Busca a pessoa no banco
        Pessoa pessoa = service.buscarPorId(id);
        // Agora pessoa tem: id=5, nome="João Silva", idade=25
        
        // 2. AQUI É A MÁGICA! 
        // Adiciona o objeto no Model com o nome "pessoa"
        model.addAttribute("pessoa", pessoa);
        //                    ↑           ↑
        //                  NOME        OBJETO
        
        // 3. Retorna o nome da página
        return "form";  // ← Spring vai buscar templates/form.html
    }
}
```

---

### PASSO 2: Thymeleaf pega os dados do Model

```html
<!-- form.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <!-- O Thymeleaf pega assim: -->
    <p th:text="${pessoa.nome}">Nome placeholder</p>
    <!--           ↑       ↑                         -->
    <!--         NOME    CAMPO                       -->
    <!--       do Model  do objeto                   -->
    
    <!-- Resultado final no navegador: -->
    <p>João Silva</p>
</body>
</html>
```

---

## 📊 DIAGRAMA VISUAL DO FLUXO

```
┌─────────────────────────────────────────────────────────────┐
│  1. CONTROLLER                                              │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  Pessoa pessoa = new Pessoa();                              │
│  pessoa.setId(5L);                                          │
│  pessoa.setNome("João Silva");                              │
│  pessoa.setIdade(25);                                       │
│                                                              │
│  model.addAttribute("pessoa", pessoa);                      │
│         └─────┬──────┘           └──┬──┘                    │
│               │                     │                        │
│            NOME NO                OBJETO                     │
│           THYMELEAF              COM DADOS                   │
└───────────────┼──────────────────────┼──────────────────────┘
                │                      │
                │  PASSA PARA O        │
                │  THYMELEAF           │
                ▼                      ▼
┌─────────────────────────────────────────────────────────────┐
│  2. THYMELEAF (HTML)                                        │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ${pessoa.nome}  →  pega "pessoa" do Model                  │
│          └─┬─┘       depois pega pessoa.getNome()           │
│            │                                                 │
│    Chama getNome() do objeto                                │
│    Retorna: "João Silva"                                    │
│                                                              │
└─────────────────────────────────────────────────────────────┘
                │
                │  RENDERIZA NO
                │  NAVEGADOR
                ▼
┌─────────────────────────────────────────────────────────────┐
│  3. RESULTADO FINAL (HTML puro)                             │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  <p>João Silva</p>                                          │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔍 EXEMPLOS PRÁTICOS

### Exemplo 1: Lista de pessoas

**Controller:**
```java
@GetMapping("/")
public String listar(Model model) {
    List<Pessoa> pessoas = service.listar();
    model.addAttribute("pessoas", pessoas);
    //                    ↑          ↑
    //                  NOME       LISTA
    return "lista";
}
```

**HTML (lista.html):**
```html
<tr th:each="pessoa : ${pessoas}">
    <!--                  ↑        -->
    <!--         Pega do Model     -->
    
    <td th:text="${pessoa.nome}">Nome</td>
    <!--            ↑       ↑              -->
    <!--      VAR LOOP   CAMPO             -->
</tr>
```

---

### Exemplo 2: Formulário novo

**Controller:**
```java
@GetMapping("/novo")
public String novo(Model model) {
    model.addAttribute("pessoa", new Pessoa());
    //                    ↑            ↑
    //                  NOME      OBJETO VAZIO
    return "form";
}
```

**HTML (form.html):**
```html
<form th:object="${pessoa}">
    <!--              ↑       -->
    <!--      Pega do Model   -->
    
    <input th:field="*{nome}">
    <!--               ↑       -->
    <!--     *{} significa: pega do th:object -->
</form>
```

---

## 🎓 REGRA DE OURO

```
model.addAttribute("NOME_AQUI", objeto);
                       ↓
           ${NOME_AQUI} no HTML
```

**Exemplos:**
- `model.addAttribute("pessoa", p)` → `${pessoa}`
- `model.addAttribute("alunos", lista)` → `${alunos}`
- `model.addAttribute("total", 100)` → `${total}`

---

## ⚠️ ERROS COMUNS

### ❌ ERRO 1: Nome diferente
```java
// Controller
model.addAttribute("usuario", pessoa);

// HTML
${pessoa.nome}  // ❌ ERRO! Não existe "pessoa", só "usuario"
```

**Correção:**
```html
${usuario.nome}  // ✅ CORRETO!
```

---

### ❌ ERRO 2: Esquecer de adicionar no Model
```java
// Controller
Pessoa pessoa = service.buscar(1L);
// Esqueceu: model.addAttribute("pessoa", pessoa);
return "form";

// HTML
${pessoa.nome}  // ❌ ERRO! pessoa não existe no Model
```

**Correção:**
```java
Pessoa pessoa = service.buscar(1L);
model.addAttribute("pessoa", pessoa);  // ✅ Adiciona!
return "form";
```

---

## 💡 RESUMÃO

| No Controller | No HTML | O que acontece |
|---------------|---------|----------------|
| `model.addAttribute("pessoa", obj)` | `${pessoa.nome}` | Pega pessoa.getNome() |
| `model.addAttribute("pessoas", lista)` | `${pessoas}` | Pega a lista toda |
| `model.addAttribute("total", 100)` | `${total}` | Mostra: 100 |
| `model.addAttribute("ativo", true)` | `${ativo}` | Mostra: true |

---

## 🔄 FLUXO COMPLETO EM AÇÃO

```java
// 1. USUÁRIO acessa: http://localhost:8080/editar/5

// 2. SPRING chama:
@GetMapping("/editar/{id}")
public String editar(@PathVariable Long id, Model model) {
    
    // 3. Busca no banco
    Pessoa p = service.buscarPorId(5);  // Retorna: João, 25 anos
    
    // 4. Coloca no Model
    model.addAttribute("pessoa", p);
    
    // 5. Retorna nome da página
    return "form";  // ← Spring busca: templates/form.html
}

// 6. THYMELEAF processa form.html
<input th:field="*{nome}">
// Pega pessoa do Model → pessoa.getNome() → "João"
// Gera: <input name="nome" value="João">

// 7. NAVEGADOR recebe HTML processado e mostra na tela
```

---

## 🎯 EXERCÍCIO PARA FIXAR

Complete o código:

**Controller:**
```java
@GetMapping("/perfil")
public String perfil(Model model) {
    Aluno aluno = new Aluno("Maria", 8.5);
    model.addAttribute("________", aluno);
    return "perfil";
}
```

**HTML (perfil.html):**
```html
<p>Nome: <span th:text="${________.nome}">Nome</span></p>
<p>Nota: <span th:text="${________.nota}">Nota</span></p>
```

<details>
<summary>💡 Ver Resposta</summary>

**Controller:**
```java
model.addAttribute("aluno", aluno);
```

**HTML:**
```html
<p>Nome: <span th:text="${aluno.nome}">Nome</span></p>
<p>Nota: <span th:text="${aluno.nota}">Nota</span></p>
```

</details>

---

## ✅ CONCLUSÃO

**${pessoa.nome}** vem de:
1. Controller coloca objeto no Model
2. Thymeleaf pega do Model
3. Chama o método getter (pessoa.getNome())
4. Mostra o resultado no HTML

**É uma ponte automática entre Java e HTML!** 🌉
