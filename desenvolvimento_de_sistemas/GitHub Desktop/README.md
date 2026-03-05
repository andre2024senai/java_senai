# Guia: GitHub Desktop + Eclipse 
---

## 🚀 Parte 1: Configuração Inicial (Fazer UMA VEZ)

### Passo 1: Instalar o GitHub Desktop

1. Acesse: https://desktop.github.com/
2. Baixe e instale o GitHub Desktop
3. Abra o programa e faça login com sua conta GitHub
4. Configure seu nome e email (se solicitado)

### Passo 2: Criar o Repositório

**No GitHub Desktop:**

1. Clique em **"File" → "New Repository"**
2. Preencha:
   - **Name:** `projetos-poo` (ou o nome que preferir)
   - **Description:** "Projetos de Programação Orientada a Objetos"
   - **Local Path:** Escolha onde salvar (ex: `C:\Users\SeuNome\Documents\GitHub`)
   - ✅ Marque: **"Initialize this repository with a README"**
   - **Git ignore:** Selecione **"Java"**
   - **License:** Pode deixar "None"
3. Clique em **"Create Repository"**

### Passo 3: Publicar no GitHub

1. No GitHub Desktop, clique em **"Publish repository"**
2. ✅ Certifique-se que está **DESMARCADO** "Keep this code private" (para ser público e acessível)
   - *Ou marque se quiser repositório privado*
3. Clique em **"Publish Repository"**

**Pronto!** O repositório está criado localmente e online.

---

## 💻 Parte 2: Trabalhando com Projetos do Eclipse

### Opção A: Criar Novo Projeto (Recomendado)

**No Eclipse:**

1. Vá em **File → New → Java Project**
2. Em **"Project name":** digite o nome do projeto (ex: `Aula01-Classes`)
3. **IMPORTANTE:** Em **"Location"**, clique em **"Browse"** e navegue até:
   ```
   C:\Users\SeuNome\Documents\GitHub\projetos-poo
   ```
4. Crie uma pasta para o projeto dentro do repositório:
   ```
   C:\Users\SeuNome\Documents\GitHub\projetos-poo\Aula01-Classes
   ```
5. Clique em **Finish**

### Opção B: Copiar Projeto Existente

Se o projeto já existe fora do repositório:

1. **No Windows Explorer:**
   - Copie a pasta completa do projeto
   - Cole dentro de: `C:\Users\SeuNome\Documents\GitHub\projetos-poo\`

2. **No Eclipse:**
   - Vá em **File → Import**
   - Selecione **General → Existing Projects into Workspace**
   - Em **"Select root directory"**, navegue até a pasta do repositório
   - Selecione o projeto e clique em **Finish**

---

## 📤 Parte 3: Salvando Alterações (FAZER SEMPRE)

### Quando terminar de programar:

**No GitHub Desktop:**

1. Você verá as alterações na aba **"Changes"**
2. No campo **"Summary"**, escreva uma mensagem curta:
   ```
   Exemplo: "Adiciona classe Pessoa e métodos get/set"
   ```
3. Clique em **"Commit to main"**
4. Clique em **"Push origin"** (seta para cima ↑)

**Pronto!** Suas alterações estão salvas online.

---

## 📥 Parte 4: Recuperar Projetos em Outro Laboratório

### Primeira vez no novo computador:

1. **Instale o GitHub Desktop e faça login**

2. **Clone o repositório:**
   - No GitHub Desktop, clique em **"File" → "Clone Repository"**
   - Selecione `projetos-poo` da lista
   - Escolha onde salvar (ex: `C:\Users\SeuNome\Documents\GitHub`)
   - Clique em **"Clone"**

3. **Importe no Eclipse:**
   - Abra o Eclipse
   - Vá em **File → Import**
   - Selecione **General → Existing Projects into Workspace**
   - Navegue até `C:\Users\SeuNome\Documents\GitHub\projetos-poo`
   - Selecione os projetos que aparecerem
   - Clique em **Finish**

### Nas próximas vezes (atualizar código):

**Antes de começar a programar:**

1. Abra o GitHub Desktop
2. Clique em **"Fetch origin"**
3. Se houver atualizações, clique em **"Pull origin"** (seta para baixo ↓)
4. Abra o Eclipse - os projetos estarão atualizados!

---

## 🔄 Fluxo de Trabalho Semanal

### 📍 Laboratório A :
```
1. Abrir GitHub Desktop
2. Fetch/Pull origin (buscar atualizações)
3. Abrir Eclipse e trabalhar
4. Ao terminar: Commit + Push
```

### 📍 Laboratório B :
```
1. Abrir GitHub Desktop
2. Fetch/Pull origin (baixar trabalho da segunda)
3. Abrir Eclipse e continuar trabalhando
4. Ao terminar: Commit + Push
```

---

## ⚠️ Problemas Comuns e Soluções

### "Não vejo meu projeto no Eclipse após clonar"
**Solução:** Você precisa importar manualmente:
- File → Import → Existing Projects into Workspace

### "Changes not staged" ou arquivos não aparecem
**Solução:** O .gitignore pode estar bloqueando. Verifique se não está ignorando pastas importantes como `src/`

### "Conflito ao fazer Pull"
**Solução:** 
1. Faça commit das suas alterações locais primeiro
2. Depois faça Pull
3. Se houver conflito, o GitHub Desktop mostrará - resolva manualmente

### "Projeto não compila no outro laboratório"
**Solução:** Verifique se:
- A versão do Java é a mesma nos dois laboratórios
- As bibliotecas externas (JARs) estão incluídas no repositório

---

## 📁 Estrutura Recomendada do Repositório

```
projetos-poo/
│
├── README.md
├── .gitignore
│
├── Aula01-Classes/
│   ├── src/
│   ├── bin/
│   └── .classpath
│
├── Aula02-Heranca/
│   ├── src/
│   ├── bin/
│   └── .classpath
│
└── Aula03-Polimorfismo/
    ├── src/
    ├── bin/
    └── .classpath
```

---

## 💡 Dicas Importantes

1. **Sempre faça Pull antes de começar** a trabalhar
2. **Sempre faça Commit + Push ao terminar** a aula
3. **Escreva mensagens de commit descritivas**
4. **Não commite a pasta `bin/`** (já está no .gitignore)
5. **Faça commits frequentes** (não apenas no final da aula)

---

## 📚 Comandos Úteis do GitHub Desktop

| Ação | Quando usar |
|------|-------------|
| **Fetch origin** | Verificar se há atualizações |
| **Pull origin** | Baixar atualizações do GitHub |
| **Commit** | Salvar alterações localmente |
| **Push origin** | Enviar alterações para o GitHub |
| **View on GitHub** | Ver repositório no navegador |

---

---

**Criado para facilitar o aprendizado de POO com mobilidade entre laboratórios! 🚀**
