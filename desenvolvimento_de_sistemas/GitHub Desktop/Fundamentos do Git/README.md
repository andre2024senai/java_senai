# Guia: Conceitos Fundamentais do Git
## Branch, Merge, Master/Main e Origin Explicados

---

## 🌳 O que é BRANCH (Ramificação)?

### Conceito:
Uma **branch** é como uma **linha do tempo paralela** do seu código. É uma versão independente onde você pode trabalhar sem afetar o código principal.

### Analogia do Caderno:
```
Imagine que você está escrevendo um livro:

📘 CADERNO PRINCIPAL (branch main)
   - Capítulo 1: ✅ Pronto
   - Capítulo 2: ✅ Pronto
   
📗 CADERNO DE RASCUNHO (branch experimento)
   - Testando ideia nova para Capítulo 3
   - Se ficar bom → copia pro caderno principal
   - Se ficar ruim → joga fora, não afeta nada
```

### Exemplo Prático em POO:

```
main (código funcionando)
│
├─ Sistema com classes: Pessoa, Aluno, Professor
│
│
└─ branch: adicionar-notas
   │
   └─ Testando nova classe: SistemaNotas
      - Se funcionar bem → juntar com main
      - Se quebrar tudo → deletar branch, main fica intacto
```

### Visualização:

```
main:     A---B---C---D---E
                   \
                    F---G  (branch: nova-funcionalidade)
```

### Por que usar?

```
✅ Você pode testar código novo sem quebrar o que já funciona
✅ Vários alunos podem trabalhar ao mesmo tempo sem conflito
✅ Fácil voltar atrás se algo der errado
✅ Organiza diferentes funcionalidades
```

---

## 🔀 O que é MERGE (Mesclar/Juntar)?

### Conceito:
**Merge** é o ato de **juntar duas branches**, integrando as mudanças de uma branch na outra.

### Analogia:
```
Você escreveu o Capítulo 3 no caderno de rascunho.
Ficou bom!

MERGE = copiar o Capítulo 3 do rascunho para o caderno principal
```

### Exemplo Visual:

```
ANTES DO MERGE:

main:           A---B---C
                     \
nova-classe:          D---E (criou classe Venda)


DEPOIS DO MERGE:

main:           A---B---C-------F (agora tem classe Venda)
                     \         /
nova-classe:          D---E---
```

### Quando fazer merge?

```
1. Terminou de desenvolver uma funcionalidade
2. Testou e está funcionando
3. Quer integrar no código principal
```

### Como fazer no GitHub Desktop:

```
1. Mudar para a branch "main"
2. Menu: Branch → Merge into current branch
3. Selecionar a branch que quer juntar
4. Confirmar
```

---

## 📌 O que é MAIN (ou MASTER)?

### Conceito:
**Main** (antigamente chamada **master**) é a **branch principal** do projeto. É a versão "oficial" e estável do código.

### Explicação Simples:

```
main = Código OFICIAL, FUNCIONANDO, PRONTO

Outras branches = Código em DESENVOLVIMENTO, TESTE, EXPERIMENTO
```

### Mudança de Nome:

```
❌ ANTES (até 2020):
   - Branch padrão chamada "master"

✅ AGORA (2020+):
   - Branch padrão chamada "main"
   - Motivo: Linguagem mais inclusiva
   - Mesma função!
```

### Regra de Ouro:

```
⚠️ A branch MAIN deve sempre estar FUNCIONANDO!

✅ CERTO:
   - Desenvolver em outra branch
   - Testar bem
   - Só fazer merge na main quando tiver certeza

❌ ERRADO:
   - Fazer commit de código quebrado direto na main
   - Fazer experimentos direto na main
```

### No contexto de POO:

```
main:
  ├─ Classes funcionando
  ├─ Testes passando
  └─ Código que pode ser entregue ao professor

branch experimental:
  ├─ Testando polimorfismo
  ├─ Código ainda com bugs
  └─ NÃO pronto para entrega
```

---

## 🌐 O que é ORIGIN?

### Conceito:
**Origin** é o **apelido** (alias) do repositório remoto no GitHub. É de onde você baixa (pull) e para onde envia (push) o código.

### Analogia da Nuvem:

```
📱 SEU COMPUTADOR (local)
   ├─ Pasta: C:\Users\Você\projetos-poo
   └─ Código aqui é LOCAL (só você vê)

☁️ GITHUB (origin/remoto)
   ├─ URL: github.com/voce/projetos-poo
   └─ Código aqui é REMOTO (todo mundo vê)

Origin = "apelido" para não precisar digitar a URL toda hora
```

### Exemplo Prático:

```
Quando você clona um repositório:

git clone https://github.com/joao/projetos-poo

O Git automaticamente cria o "atalho":
origin → https://github.com/joao/projetos-poo

Então você pode usar:
- git push origin main
  (em vez de: git push https://github.com/joao/projetos-poo main)
```

### No GitHub Desktop:

```
Quando você vê:
"Push origin" = Enviar para o GitHub
"Pull origin" = Baixar do GitHub
"Fetch origin" = Verificar atualizações no GitHub

Origin = Seu repositório no GitHub
```

### Múltiplos Remotos (Avançado):

```
Você pode ter vários remotos:

origin → github.com/voce/projeto (seu repositório)
upstream → github.com/professor/projeto (repositório do professor)
```

---

## 🎯 Tudo Junto: Fluxo Completo

### Cenário Real:

```
Você está desenvolvendo um Sistema de Vendas em POO.
Quer adicionar uma nova classe sem quebrar o código existente.
```

### Passo a Passo:

```
1️⃣ CRIAR BRANCH
   main (código funcionando)
     └─ criar branch: adicionar-carrinho

2️⃣ TRABALHAR NA BRANCH
   adicionar-carrinho:
     - Criar classe CarrinhoCompras.java
     - Testar
     - Fazer commits

3️⃣ ENVIAR PARA O GITHUB
   Push para: origin/adicionar-carrinho
   (seu GitHub agora tem essa branch)

4️⃣ MERGE (quando terminar)
   Voltar para main
   Fazer merge de adicionar-carrinho → main
   
5️⃣ PUSH FINAL
   Push para: origin/main
   (GitHub atualizado com novo código)
```

### Diagrama Completo:

```
LOCAL (seu computador):

main:              A---B---C-----------G (merge)
                        \             /
adicionar-carrinho:      D---E---F---


REMOTE (GitHub/origin):

origin/main:       A---B---C-----------G (após push)
                        \             /
origin/adicionar:        D---E---F---
```

---

## 📚 Vocabulário Git Traduzido

| Termo em Inglês | Tradução | Explicação |
|-----------------|----------|------------|
| **Branch** | Ramificação | Linha do tempo paralela |
| **Main/Master** | Principal | Branch oficial do projeto |
| **Origin** | Origem/Remoto | Repositório no GitHub |
| **Merge** | Mesclar/Juntar | Combinar duas branches |
| **Commit** | Confirmar | Salvar mudanças |
| **Push** | Enviar | Mandar código para GitHub |
| **Pull** | Puxar | Baixar código do GitHub |
| **Fetch** | Buscar | Verificar atualizações |
| **Clone** | Clonar | Copiar repositório |
| **Fork** | Bifurcar | Copiar repo de outra pessoa |

---

## 🤔 Perguntas Frequentes

### "Preciso usar branches para POO?"

```
Para trabalho individual básico: NÃO é obrigatório
└─ Você pode trabalhar só na main

Para trabalhos em grupo: SIM, é MUITO útil
└─ Cada pessoa trabalha em uma branch
└─ Evita conflitos
```

### "Quando devo criar uma branch?"

```
✅ Criar branch quando:
   - Vai testar algo novo
   - Trabalho em equipe
   - Funcionalidade grande/complexa
   - Não quer arriscar quebrar o código

❌ Não precisa de branch quando:
   - Pequenas correções
   - Trabalho individual simples
   - Exercícios rápidos de aula
```

### "O que é origin/main?"

```
origin/main = Como está a branch main NO GITHUB

main (local) = Como está a branch main NO SEU COMPUTADOR

Podem ser diferentes se:
- Você fez commits e não deu push ainda
- Alguém fez push e você não deu pull ainda
```

### "Master e Main são a mesma coisa?"

```
✅ SIM! Mesma função, apenas mudou o nome.

Repositórios antigos: usam "master"
Repositórios novos: usam "main"

Você pode renomear se quiser:
GitHub → Settings → Branches → Rename
```

---

## 🎮 Exercício Prático

### Teste para Entender:

```
1. Crie um projeto simples no Eclipse
2. Faça commit na main
3. No GitHub Desktop:
   - Crie uma nova branch: "teste-branch"
   - Modifique um arquivo
   - Faça commit
   - Volte para main → nada mudou!
   - Faça merge da teste-branch
   - Agora main tem as mudanças!
```

---

## 📊 Comparação: Local vs Remote

```
┌─────────────────────────────┐
│   SEU COMPUTADOR (Local)    │
│                              │
│  main ──────────────────┐   │
│  branch-teste ────────┐ │   │
│                       │ │   │
└───────────────────────┼─┼───┘
                        │ │
                    push│ │pull
                        ↓ ↑
┌───────────────────────┼─┼───┐
│      GITHUB (Origin)  │ │   │
│                       │ │   │
│  origin/main ─────────┘ │   │
│  origin/branch-teste ───┘   │
│                              │
└──────────────────────────────┘
```

---

## 🚀 Comandos GitHub Desktop (resumo)

### Trabalhando com Branches:

```
Criar nova branch:
└─ Current Branch → New Branch

Trocar de branch:
└─ Current Branch → Selecionar

Fazer merge:
└─ Branch → Merge into Current Branch

Ver branches remotas:
└─ Current Branch → origem = remote (GitHub)
```

---

## 💡 Dicas para Professores

### Para aulas de POO:

```
OPÇÃO SIMPLES (recomendado para iniciantes):
└─ Não precisa ensinar branches no começo
└─ Trabalhem só na main
└─ Foquem em: commit, push, pull

OPÇÃO AVANÇADA (alunos intermediários):
└─ Ensine branches para trabalhos em grupo
└─ Cada aluno = uma branch
└─ Merge ao final = integração
```

### Exemplo de Trabalho em Grupo:

```
Projeto: Sistema Bancário (POO)

main (código base)
  ├─ branch: maria-classe-conta (Maria fazendo)
  ├─ branch: joao-classe-cliente (João fazendo)
  └─ branch: pedro-classe-banco (Pedro fazendo)

No final:
└─ Todos fazem merge → main tem sistema completo
```

---

## 📖 Resumo Executivo

```
╔════════════════════════════════════════════════════════╗
║ BRANCH    = Versão paralela do código                 ║
║ MAIN      = Branch principal (código oficial)         ║
║ ORIGIN    = Apelido do repositório no GitHub          ║
║ MERGE     = Juntar duas branches                      ║
║                                                        ║
║ Push origin = Enviar para GitHub                      ║
║ Pull origin = Baixar do GitHub                        ║
╚════════════════════════════════════════════════════════╝
```

---

## 🎓 Para Estudantes

**Você precisa saber:**
- ✅ Main é a branch principal
- ✅ Origin é o GitHub (remoto)
- ✅ Push = enviar, Pull = baixar
- ⚠️ Branches = só se trabalho em grupo ou quer testar

**Você NÃO precisa se preocupar (no começo):**
- ❌ Criar várias branches
- ❌ Merges complexos
- ❌ Conflitos

**Foque no básico:**
```
1. Fazer código no Eclipse
2. Commit (GitHub Desktop)
3. Push para origin
4. Pronto!
```

---

**Agora você entende os 4 conceitos fundamentais do Git! 🎉**
