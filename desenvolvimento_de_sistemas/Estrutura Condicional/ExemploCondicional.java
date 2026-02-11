import java.util.Scanner;

public class SistemaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SISTEMA DE NOTAS ESCOLARES ---");
        System.out.print("Digite a nota final do aluno (0 a 10): ");
        double nota = scanner.nextDouble();

        System.out.println("\n--- RESULTADO ---");

        // 1. Estrutura Simples (IF)
        // Verifica se a nota é válida (não pode ser menor que 0 ou maior que 10)
        if (nota < 0 || nota > 10) {
            System.out.println("ERRO: A nota digitada é inválida!");
        } 
        // 2. Estrutura Encadeada (ELSE IF)
        else if (nota >= 7.0) {
            System.out.println("Situação: APROVADO! Parabéns.");
        } 
        else if (nota >= 5.0) {
            System.out.println("Situação: RECUPERAÇÃO.");
            System.out.println("O aluno precisa fazer a prova final.");
        } 
        // 3. Estrutura Final (ELSE - O que sobrou)
        else {
            System.out.println("Situação: REPROVADO.");
            System.out.println("Estude mais no próximo semestre.");
        }

        scanner.close();
    }
}
