# Strings

### Conceito, declaração, imutabilidade
### .equals(), .length(), .charAt(), .toUpperCase(), .toLowerCase()

## Código para Consulta
---

```java
import java.util.Scanner;

public class ExemploString {

		public static void main(String[] args) {
    
public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 1. DECLARAÇÃO E INICIALIZAÇÃO
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("=== 1. DECLARAÇÃO DE STRINGS ===\n");
	        
	        // Três formas de criar String
	        String nome1 = "Maria";              // Forma mais comum (literal)
	        String nome2 = new String("João");   // Usando construtor (menos usado)
	        String vazia = "";                   // String vazia (válida!)
	        
	        System.out.println("Nome 1: " + nome1);
	        System.out.println("Nome 2: " + nome2);
	        System.out.println("Vazia: '" + vazia + "'");
	        
	        // Lendo do usuário
	        System.out.print("\nDigite seu nome completo: ");
	        String nomeCompleto = sc.nextLine();  // Lê a linha inteira (com espaços)
	        System.out.println("Você digitou: " + nomeCompleto);
	        
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 2. COMPARAÇÃO - O MAIS IMPORTANTE!
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n=== 2. COMPARAÇÃO DE STRINGS ===\n");
	        
	        String senha1 = "java123";
	        String senha2 = "java123";
	        String senha3 = "JAVA123";
	        
	        // ERRADO - NUNCA USE == PARA STRINGS!
	        // O == compara se são o MESMO OBJETO na memória, não o conteúdo
	        System.out.println("senha1 == senha2: " + (senha1 == senha2));  // Pode dar true por acaso
	        
	        // CERTO - Use .equals() para comparar o CONTEÚDO
	        System.out.println("senha1.equals(senha2): " + senha1.equals(senha2));  // true
	        System.out.println("senha1.equals(senha3): " + senha1.equals(senha3));  // false (Case sensitive!)
	        
	        // CERTO - Use .equalsIgnoreCase() para ignorar maiúsculas/minúsculas
	        System.out.println("senha1.equalsIgnoreCase(senha3): " + senha1.equalsIgnoreCase(senha3));  // true
	        
	        // Exemplo prático: validação de senha
	        System.out.print("\nDigite a senha (senha correta: 'admin123'): ");
	        String senhaDigitada = sc.nextLine();
	        
	        if (senhaDigitada.equals("admin123")) {
	            System.out.println("Acesso liberado!");
	        } else {
	            System.out.println("Senha incorreta!");
	        }
	        
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 3. TAMANHO DA STRING - .length()
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n=== 3. TAMANHO (.length) ===\n");
	        
	        String palavra = "Desenvolvimento";
	        int tamanho = palavra.length();
	        
	        System.out.println("A palavra '" + palavra + "' tem " + tamanho + " caracteres");
	        
	        // Exemplo prático: validar tamanho mínimo de senha
	        System.out.print("\nCrie uma senha (mínimo 6 caracteres): ");
	        String novaSenha = sc.nextLine();
	        
	        if (novaSenha.length() >= 6) {
	            System.out.println("Senha aceita!");
	        } else {
	            System.out.println("Senha muito curta! Tem apenas " + novaSenha.length() + " caracteres.");
	        }
	        
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 4. ACESSAR CARACTERES - .charAt(indice)
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n=== 4. ACESSAR CARACTERES (.charAt) ===\n");
	        
	        String texto = "Java";
	        
	        // IMPORTANTE: índices começam em 0!
	        // J = posição 0
	        // a = posição 1
	        // v = posição 2
	        // a = posição 3
	        
	        char primeiraLetra = texto.charAt(0);  // 'J'
	        char ultimaLetra = texto.charAt(texto.length() - 1);  // 'a'
	        
	        System.out.println("Primeira letra de '" + texto + "': " + primeiraLetra);
	        System.out.println("Última letra de '" + texto + "': " + ultimaLetra);
	        
	        // Exemplo prático: percorrer a String letra por letra
	        System.out.println("\nPercorrendo cada letra:");
	        for (int i = 0; i < texto.length(); i++) {
	            System.out.println("Posição " + i + ": " + texto.charAt(i));
	        }
	        
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 5. CONVERSÃO DE CAIXA - .toUpperCase() / .toLowerCase()
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n=== 5. CONVERSÃO DE CAIXA ===\n");
	        
	        String frase = "Java é Poderoso";
	        
	        String maiuscula = frase.toUpperCase();  // "JAVA É PODEROSO"
	        String minuscula = frase.toLowerCase();  // "java é poderoso"
	        
	        System.out.println("Original: " + frase);
	        System.out.println("Maiúscula: " + maiuscula);
	        System.out.println("Minúscula: " + minuscula);
	        
	        // IMPORTANTE: Strings são IMUTÁVEIS!
	        // Os métodos não alteram a String original, criam uma NOVA
	        System.out.println("Original continua: " + frase);  // Não mudou!
	        
	        // Exemplo prático: aceitar entrada em qualquer caixa
	        System.out.print("\nVocê gosta de programar? (sim/não): ");
	        String resposta = sc.nextLine().toLowerCase();  // Converte para minúscula
	        
	        if (resposta.equals("sim")) {
	            System.out.println("Ótimo! Continue praticando!");
	        }
	        
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 6. BUSCAR TEXTO - .contains() / .indexOf()
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n=== 6. BUSCAR TEXTO ===\n");
	        
	        String email = "usuario@example.com";
	        
	        // .contains() - verifica se contém um texto (retorna true/false)
	        boolean temArroba = email.contains("@");
	        System.out.println("Email contém @? " + temArroba);
	        
	        // .indexOf() - retorna a POSIÇÃO onde o texto aparece (ou -1 se não existir)
	        int posicaoArroba = email.indexOf("@");
	        System.out.println("Posição do @: " + posicaoArroba);
	        
	        // Exemplo prático: validar email básico
	        System.out.print("\nDigite seu email: ");
	        String emailUsuario = sc.nextLine();
	        
	        if (emailUsuario.contains("@") && emailUsuario.contains(".")) {
	            System.out.println("Email parece válido!");
	        } else {
	            System.out.println("Email inválido! Falta @ ou .");
	        }
	        
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 7. EXTRAIR PARTES - .substring(inicio, fim)
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n=== 7. EXTRAIR PARTES (.substring) ===\n");
	        
	        String cpf = "123.456.789-00";
	        
	        // .substring(inicio, fim) - extrai do índice 'inicio' ATÉ (exclusive) 'fim'
	        String primeiros3 = cpf.substring(0, 3);  // "123"
	        
	        // .substring(inicio) - extrai do índice 'inicio' até o final
	        String ultimos2 = cpf.substring(cpf.length() - 2);  // "00"
	        
	        System.out.println("CPF completo: " + cpf);
	        System.out.println("Primeiros 3 dígitos: " + primeiros3);
	        System.out.println("Últimos 2 dígitos: " + ultimos2);
	        
	        // Exemplo prático: extrair nome de usuário do email
	        String emailExemplo = "joao.silva@empresa.com";
	        int posArroba = emailExemplo.indexOf("@");
	        String nomeUsuario = emailExemplo.substring(0, posArroba);
	        
	        System.out.println("\nEmail: " + emailExemplo);
	        System.out.println("Nome de usuário: " + nomeUsuario);
	        
	        
	        // ═══════════════════════════════════════════════════════════════
	        // 8. LIMPAR ESPAÇOS - .trim()
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n=== 8. LIMPAR ESPAÇOS (.trim) ===\n");
	        
	        String comEspacos = "   Java   ";
	        String semEspacos = comEspacos.trim();  // Remove espaços do início e fim
	        
	        System.out.println("Com espaços: '" + comEspacos + "' (tamanho: " + comEspacos.length() + ")");
	        System.out.println("Sem espaços: '" + semEspacos + "' (tamanho: " + semEspacos.length() + ")");
	        
	        // Exemplo prático: usuário digitou espaços por engano
	        System.out.print("\nDigite 'SAIR' para encerrar: ");
	        String comando = sc.nextLine().trim().toUpperCase();  // trim + maiúscula
	        
	        if (comando.equals("SAIR")) {
	            System.out.println("Encerrando...");
	        }
	        
	      	        
	        sc.close();
	        
	        // ═══════════════════════════════════════════════════════════════
	        // RESUMO DOS MÉTODOS PRINCIPAIS
	        // ═══════════════════════════════════════════════════════════════
	        
	        System.out.println("\n\n╔══════════════════════════════════════════════════════╗");
	        System.out.println("║         RESUMO - MÉTODOS DE STRING                  ║");
	        System.out.println("╠══════════════════════════════════════════════════════╣");
	        System.out.println("║ .equals(outra)            → Comparar conteúdo        ║");
	        System.out.println("║ .equalsIgnoreCase(outra)  → Comparar (ignora caixa) ║");
	        System.out.println("║ .length()                 → Tamanho                  ║");
	        System.out.println("║ .charAt(i)                → Caractere na posição i   ║");
	        System.out.println("║ .toUpperCase()            → MAIÚSCULAS              ║");
	        System.out.println("║ .toLowerCase()            → minúsculas              ║");
	        System.out.println("║ .contains(texto)          → Contém texto?           ║");
	        System.out.println("║ .indexOf(texto)           → Posição do texto        ║");
	        System.out.println("║ .substring(inicio, fim)   → Extrair parte           ║");
	        System.out.println("║ .trim()                   → Remover espaços pontas  ║");
	        System.out.println("╚══════════════════════════════════════════════════════╝");
	    }


	}
```

