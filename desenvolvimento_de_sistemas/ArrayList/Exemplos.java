import java.util.ArrayList;
import java.util.Scanner;

/**
 * ARRAYLIST (LISTA DINÂMICA)
 * 
 * Objetivos:
 * - Entender a diferença entre Array (fixo) e ArrayList (dinâmico)
 * - Usar os métodos principais: add, remove, get, set, size
 * - Percorrer ArrayList com for tradicional e for-each
 * - Entender Generics: ArrayList<Tipo>
 * 
 * Tópicos cobertos:
 * 1. Por que ArrayList? (Limitações do array)
 * 2. Criação e Generics <Tipo>
 * 3. Adicionar elementos (.add)
 * 4. Acessar elementos (.get)
 * 5. Modificar elementos (.set)
 * 6. Remover elementos (.remove)
 * 7. Tamanho (.size)
 * 8. Verificar se está vazio (.isEmpty)
 * 9. Percorrer (for e for-each)
 */

public class Aula3ArrayList {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // ═══════════════════════════════════════════════════════════════
        // 1. POR QUE ARRAYLIST? LIMITAÇÕES DO ARRAY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("=== 1. LIMITAÇÕES DO ARRAY TRADICIONAL ===\n");
        
        // Problema 1: Tamanho fixo
        int[] arrayFixo = new int[3];
        arrayFixo[0] = 10;
        arrayFixo[1] = 20;
        arrayFixo[2] = 30;
        // arrayFixo[3] = 40;  //  ERRO! Não posso adicionar mais elementos!
        
        System.out.println("Array tem tamanho fixo: " + arrayFixo.length);
        System.out.println("Não posso adicionar mais elementos!");
        
        // Problema 2: Remover é complexo (precisa deslocar elementos)
        System.out.println("\nPara remover elemento de um array, preciso:");
        System.out.println("1. Criar um novo array menor");
        System.out.println("2. Copiar todos os elementos, pulando o removido");
        System.out.println("3. Muito trabalhoso!");
        
        System.out.println("\n SOLUÇÃO: ArrayList!");
        System.out.println("ArrayList é uma lista DINÂMICA que:");
        System.out.println("- Cresce automaticamente quando precisa");
        System.out.println("- Remove elementos facilmente");
        System.out.println("- Tem métodos prontos para tudo");
        
        
        // ═══════════════════════════════════════════════════════════════
        // 2. CRIAÇÃO E GENERICS <Tipo>
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 2. CRIANDO ARRAYLIST ===\n");
        
        // Sintaxe: ArrayList<Tipo> nome = new ArrayList<>();
        //                    ↑                        ↑
        //              Tipo dos elementos      Diamond operator (vazio)
        
        ArrayList<String> nomes = new ArrayList<>();      // Lista de Strings
        ArrayList<Integer> numeros = new ArrayList<>();   // Lista de inteiros
        ArrayList<Double> precos = new ArrayList<>();     // Lista de doubles
        
        // IMPORTANTE: Dentro de <> só pode usar CLASSES, não tipos primitivos
        // ArrayList<int> errado →  ArrayList<Integer> certo
        //  ArrayList<double> errado →  ArrayList<Double> certo
        //  ArrayList<boolean> errado →  ArrayList<Boolean> certo
        
        System.out.println("ArrayList<String> nomes criado");
        System.out.println("ArrayList<Integer> numeros criado");
        System.out.println("ArrayList<Double> precos criado");
        
        // No início, todos estão vazios
        System.out.println("\nTamanho inicial de 'nomes': " + nomes.size());  // 0
        
        
        // ═══════════════════════════════════════════════════════════════
        // 3. ADICIONAR ELEMENTOS - .add()
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 3. ADICIONAR ELEMENTOS (.add) ===\n");
        
        ArrayList<String> frutas = new ArrayList<>();
        
        // .add(elemento) - adiciona no final da lista
        frutas.add("Maçã");
        frutas.add("Banana");
        frutas.add("Laranja");
        
        System.out.println("Frutas adicionadas: " + frutas);
        System.out.println("Tamanho agora: " + frutas.size());  // 3
        
        // .add(indice, elemento) - adiciona em posição específica
        frutas.add(1, "Uva");  // Adiciona "Uva" na posição 1
        // Resultado: [Maçã, Uva, Banana, Laranja]
        
        System.out.println("Após adicionar Uva na posição 1: " + frutas);
        
        // Exemplo prático: ler nomes do usuário
        ArrayList<String> listaAlunos = new ArrayList<>();
        
        System.out.print("\nQuantos alunos deseja cadastrar? ");
        int qtd = sc.nextInt();
        sc.nextLine();  // Limpar buffer
        
        for (int i = 0; i < qtd; i++) {
            System.out.print("Nome do aluno " + (i+1) + ": ");
            String nome = sc.nextLine();
            listaAlunos.add(nome);  // Adiciona dinamicamente!
        }
        
        System.out.println("\nAlunos cadastrados: " + listaAlunos);
        
        
        // ═══════════════════════════════════════════════════════════════
        // 4. ACESSAR ELEMENTOS - .get(indice)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 4. ACESSAR ELEMENTOS (.get) ===\n");
        
        ArrayList<String> cores = new ArrayList<>();
        cores.add("Vermelho");
        cores.add("Verde");
        cores.add("Azul");
        
        // Em array: cores[0]
        // Em ArrayList: cores.get(0)
        
        String primeira = cores.get(0);        // "Vermelho"
        String ultima = cores.get(cores.size() - 1);  // "Azul"
        
        System.out.println("Primeira cor: " + primeira);
        System.out.println("Última cor: " + ultima);
        
        //  ERRO se tentar acessar índice que não existe
        // cores.get(10);  // IndexOutOfBoundsException!
        
        
        // ═══════════════════════════════════════════════════════════════
        // 5. MODIFICAR ELEMENTOS - .set(indice, novoValor)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 5. MODIFICAR ELEMENTOS (.set) ===\n");
        
        ArrayList<String> cidades = new ArrayList<>();
        cidades.add("São Paulo");
        cidades.add("Rio de Janeiro");
        cidades.add("Belo Horizonte");
        
        System.out.println("Lista original: " + cidades);
        
        // .set(indice, novoValor) - substitui o valor na posição
        cidades.set(1, "Curitiba");  // Troca "Rio de Janeiro" por "Curitiba"
        
        System.out.println("Após modificar posição 1: " + cidades);
        
        
        // ═══════════════════════════════════════════════════════════════
        // 6. REMOVER ELEMENTOS - .remove()
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 6. REMOVER ELEMENTOS (.remove) ===\n");
        
        ArrayList<Integer> numList = new ArrayList<>();
        numList.add(10);
        numList.add(20);
        numList.add(30);
        numList.add(40);
        
        System.out.println("Lista original: " + numList);
        
        // Forma 1: .remove(indice) - remove pela posição
        numList.remove(1);  // Remove posição 1 (o número 20)
        System.out.println("Após remove(1): " + numList);  // [10, 30, 40]
        
        // Forma 2: .remove(Object) - remove o elemento específico
        numList.remove(Integer.valueOf(30));  // Remove o número 30
        System.out.println("Após remove(30): " + numList);  // [10, 40]
        
        // Exemplo com Strings
        ArrayList<String> tarefas = new ArrayList<>();
        tarefas.add("Estudar Java");
        tarefas.add("Fazer exercícios");
        tarefas.add("Revisar matéria");
        
        System.out.println("\nTarefas: " + tarefas);
        
        tarefas.remove("Fazer exercícios");  // Remove pelo conteúdo
        System.out.println("Após remover tarefa: " + tarefas);
        
        
        // ═══════════════════════════════════════════════════════════════
        // 7. TAMANHO - .size() (COM PARÊNTESES!)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 7. TAMANHO DA LISTA ===\n");
        
        ArrayList<String> compras = new ArrayList<>();
        System.out.println("Lista vazia - tamanho: " + compras.size());  // 0
        
        compras.add("Arroz");
        compras.add("Feijão");
        compras.add("Macarrão");
        System.out.println("Após adicionar 3 itens: " + compras.size());  // 3
        
        compras.remove(0);
        System.out.println("Após remover 1 item: " + compras.size());  // 2
        
        // COMPARAÇÃO:
        // Array:     array.length       (SEM parênteses)
        // ArrayList: lista.size()       (COM parênteses)
        // String:    texto.length()     (COM parênteses)
        
        
        // ═══════════════════════════════════════════════════════════════
        // 8. VERIFICAR SE ESTÁ VAZIO - .isEmpty()
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 8. VERIFICAR SE ESTÁ VAZIO ===\n");
        
        ArrayList<String> teste = new ArrayList<>();
        
        System.out.println("Lista vazia? " + teste.isEmpty());  // true
        
        teste.add("Item");
        System.out.println("Após adicionar, ainda vazia? " + teste.isEmpty());  // false
        
        // Equivalente a:
        // teste.size() == 0
        
        
        // ═══════════════════════════════════════════════════════════════
        // 9. PERCORRER ARRAYLIST
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n=== 9. PERCORRER ARRAYLIST ===\n");
        
        ArrayList<String> animais = new ArrayList<>();
        animais.add("Cachorro");
        animais.add("Gato");
        animais.add("Coelho");
        animais.add("Pássaro");
        
        // Forma 1: For tradicional (quando precisa do índice)
        System.out.println("--- For tradicional ---");
        for (int i = 0; i < animais.size(); i++) {
            System.out.println(i + ": " + animais.get(i));
        }
        
        // Forma 2: For-each (quando só precisa ler)
        System.out.println("\n--- For-each ---");
        for (String animal : animais) {
            System.out.println("- " + animal);
        }
        
        
        // ═══════════════════════════════════════════════════════════════
        // COMPARAÇÃO: ARRAY vs ARRAYLIST
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║        ARRAY vs ARRAYLIST                          ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║                  ARRAY       │      ARRAYLIST      ║");
        System.out.println("╟────────────────────────────────────────────────────╢");
        System.out.println("║ Tamanho          FIXO        │      DINÂMICO       ║");
        System.out.println("║ Criar            int[]       │   ArrayList<Int>    ║");
        System.out.println("║ Adicionar        arr[i]=val  │   lista.add(val)    ║");
        System.out.println("║ Acessar          arr[i]      │   lista.get(i)      ║");
        System.out.println("║ Modificar        arr[i]=val  │   lista.set(i,val)  ║");
        System.out.println("║ Remover          complexo    │   lista.remove(i)   ║");
        System.out.println("║ Tamanho          arr.length  │   lista.size()      ║");
        System.out.println("║                                                    ║");
        System.out.println("║ Quando usar?                                       ║");
        System.out.println("║ Array: quando tamanho fixo e performance crítica   ║");
        System.out.println("║ ArrayList: quase sempre! Mais prático e flexível  ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        
        
        // ═══════════════════════════════════════════════════════════════
        // EXERCÍCIO INTEGRADO: LISTA DE CONTATOS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n\n╔════════════════════════════════════════╗");
        System.out.println("║    EXERCÍCIO: LISTA DE CONTATOS       ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        ArrayList<String> contatos = new ArrayList<>();
        String opcao;
        
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Remover contato");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextLine();
            
            switch (opcao) {
                case "1":
                    // Adicionar
                    System.out.print("Nome do contato: ");
                    String novoContato = sc.nextLine();
                    contatos.add(novoContato);
                    System.out.println("Contato adicionado!");
                    break;
                    
                case "2":
                    // Listar
                    if (contatos.isEmpty()) {
                        System.out.println("Lista vazia!");
                    } else {
                        System.out.println("\n=== LISTA DE CONTATOS ===");
                        for (int i = 0; i < contatos.size(); i++) {
                            System.out.println((i+1) + ". " + contatos.get(i));
                        }
                        System.out.println("Total: " + contatos.size() + " contatos");
                    }
                    break;
                    
                case "3":
                    // Buscar
                    System.out.print("Nome para buscar: ");
                    String busca = sc.nextLine();
                    
                    boolean achou = false;
                    for (int i = 0; i < contatos.size(); i++) {
                        if (contatos.get(i).equalsIgnoreCase(busca)) {
                            System.out.println("Encontrado na posição " + (i+1));
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) {
                        System.out.println(" Contato não encontrado!");
                    }
                    break;
                    
                case "4":
                    // Remover
                    if (contatos.isEmpty()) {
                        System.out.println(" Lista vazia!");
                    } else {
                        System.out.println("\n=== CONTATOS ===");
                        for (int i = 0; i < contatos.size(); i++) {
                            System.out.println((i+1) + ". " + contatos.get(i));
                        }
                        System.out.print("Digite o número do contato para remover: ");
                        int num = sc.nextInt();
                        sc.nextLine();  // Limpar buffer
                        
                        if (num > 0 && num <= contatos.size()) {
                            String removido = contatos.remove(num - 1);
                            System.out.println(" " + removido + " removido!");
                        } else {
                            System.out.println("Número inválido!");
                        }
                    }
                    break;
                    
                case "5":
                    System.out.println("Encerrando...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (!opcao.equals("5"));
        
        sc.close();
        
        
        // ═══════════════════════════════════════════════════════════════
        // RESUMO - ARRAYLIST
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              RESUMO - ARRAYLIST                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ ArrayList<Tipo> lista = new ArrayList<>();          ║");
        System.out.println("║                                                      ║");
        System.out.println("║ .add(elemento)         → Adiciona no final          ║");
        System.out.println("║ .add(i, elemento)      → Adiciona na posição i      ║");
        System.out.println("║ .get(i)                → Acessa posição i            ║");
        System.out.println("║ .set(i, novo)          → Modifica posição i          ║");
        System.out.println("║ .remove(i)             → Remove posição i            ║");
        System.out.println("║ .remove(objeto)        → Remove elemento específico  ║");
        System.out.println("║ .size()                → Tamanho da lista            ║");
        System.out.println("║ .isEmpty()             → Verifica se está vazia      ║");
        System.out.println("║                                                      ║");
        System.out.println("║      Use CLASSES nos generics: <Integer>, não <int>  ║");
        System.out.println("║                                                      ║");
        System.out.println("║     Na próxima aula: ArrayList + String juntos!      ║");
        System.out.println("║    Preparação para POO com classes e objetos!       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
