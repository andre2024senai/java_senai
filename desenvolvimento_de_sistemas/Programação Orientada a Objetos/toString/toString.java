
import java.util.ArrayList;

/**
 * AULA: toString() - Representação de Objetos
 * 
 * Este exemplo demonstra:
 * - Como criar toString()
 * - Diferença COM e SEM toString()
 * - Uso com ArrayList
 */

// Classe SEM toString()
class ProdutoSemToString {
    private String nome;
    private double preco;
    
    public ProdutoSemToString(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
}

// Classe COM toString()
class ProdutoComToString {
    private String nome;
    private double preco;
    
    public ProdutoComToString(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    
    // toString() sobrescreve o método de Object
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}

public class Exemplo05_ToString {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO toString() ===\n");
        
        // ========================================
        // PARTE 1: Objeto único
        // ========================================
        System.out.println("--- PARTE 1: Objeto Único ---\n");
        
        ProdutoSemToString p1 = new ProdutoSemToString("Mouse", 50.00);
        ProdutoComToString p2 = new ProdutoComToString("Teclado", 150.00);
        
        System.out.println("SEM toString():");
        System.out.println(p1); // Saída: ProdutoSemToString@15db9742 (INÚTIL!)
        
        System.out.println("\nCOM toString():");
        System.out.println(p2); // Saída: Teclado - R$ 150.00 (ÚTIL!)
        
        // ========================================
        // PARTE 2: ArrayList
        // ========================================
        System.out.println("\n\n--- PARTE 2: ArrayList ---\n");
        
        ArrayList<ProdutoSemToString> listaSem = new ArrayList<>();
        listaSem.add(new ProdutoSemToString("Notebook", 2500.00));
        listaSem.add(new ProdutoSemToString("Mouse", 50.00));
        listaSem.add(new ProdutoSemToString("Teclado", 150.00));
        
        ArrayList<ProdutoComToString> listaCom = new ArrayList<>();
        listaCom.add(new ProdutoComToString("Notebook", 2500.00));
        listaCom.add(new ProdutoComToString("Mouse", 50.00));
        listaCom.add(new ProdutoComToString("Teclado", 150.00));
        
        System.out.println("SEM toString() - Lista:");
        System.out.println(listaSem);
        // Saída: [ProdutoSemToString@1b6d3586, ProdutoSemToString@4554617c, ...]
        // ❌ Completamente INÚTIL!
        
        System.out.println("\nCOM toString() - Lista:");
        System.out.println(listaCom);
        // Saída: [Notebook - R$ 2500.00, Mouse - R$ 50.00, Teclado - R$ 150.00]
        // ✅ Informação CLARA e ÚTIL!
        
        // ========================================
        // PARTE 3: Listando produtos
        // ========================================
        System.out.println("\n\n--- PARTE 3: Listar Produtos ---\n");
        
        System.out.println("SEM toString() - Precisa dos getters:");
        for (ProdutoSemToString p : listaSem) {
            System.out.println(p.getNome() + " - R$ " + 
                             String.format("%.2f", p.getPreco()));
        }
        
        System.out.println("\nCOM toString() - Só dar print:");
        for (ProdutoComToString p : listaCom) {
            System.out.println(p); // Chama toString() automaticamente!
        }
        
        // ========================================
        // PARTE 4: Formatação personalizada
        // ========================================
        System.out.println("\n\n--- PARTE 4: Formatações Diferentes ---\n");
        
        // Exemplo de diferentes formatações de toString()
        Livro livro = new Livro("Java Completo", "Autor X", 89.90);
        System.out.println("Livro: " + livro);
        
        Pessoa pessoa = new Pessoa("João Silva", 25, "Engenheiro");
        System.out.println("Pessoa: " + pessoa);
        
        System.out.println("\n✓ Demonstração concluída!");
    }
}

// Classes auxiliares com diferentes formatações de toString()

class Livro {
    private String titulo;
    private String autor;
    private double preco;
    
    public Livro(String titulo, String autor, double preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }
    
    @Override
    public String toString() {
        return "\"" + titulo + "\" por " + autor + 
               " (R$ " + String.format("%.2f", preco) + ")";
    }
}

class Pessoa {
    private String nome;
    private int idade;
    private String profissao;
    
    public Pessoa(String nome, int idade, String profissao) {
        this.nome = nome;
        this.idade = idade;
        this.profissao = profissao;
    }
    
    @Override
    public String toString() {
        return nome + ", " + idade + " anos - " + profissao;
    }
}
