public class TiposVariaveis {
    public static void main(String[] args) {
        System.out.println("--- TIPOS DE DADOS EM JAVA ---");

        // 1. INTEIROS (Números sem casa decimal)
        byte idade = 18;                // O menor (até 127)
        short anoAtual = 2024;          // Médio (até ~32 mil)
        int quantidadeAlunos = 45000;   // O padrão (até ~2 bilhões)
        long populacaoMundial = 7900000000L; // Gigante. *Note o 'L' no final*

        // 2. DECIMAIS (Números com ponto flutuante)
        float altura = 1.75f;           // Menos preciso. *Note o 'f' no final*
        double salario = 2500.99;       // Mais preciso. Padrão para cálculos

        // 3. CARACTERE E TEXTO
        char inicial = 'A';             // Apenas UM caractere (Aspas simples '')
        String nomeCompleto = "Andre do SENAI"; // Texto (Aspas duplas "")
        // Obs: String não é tipo primitivo, é uma Classe, mas é fundamental ensinar.

        // 4. LÓGICO
        boolean estaAprovado = true;    // Só aceita true ou false

        // SAÍDA DE DADOS
        System.out.println("Idade (byte): " + idade);
        System.out.println("Ano (short): " + anoAtual);
        System.out.println("Alunos (int): " + quantidadeAlunos);
        System.out.println("População (long): " + populacaoMundial);
        System.out.println("Altura (float): " + altura);
        System.out.println("Salário (double): " + salario);
        System.out.println("Inicial (char): " + inicial);
        System.out.println("Nome (String): " + nomeCompleto);
        System.out.println("Aprovado? (boolean): " + estaAprovado);
    }
}
