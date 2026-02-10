
import java.util.Scanner;

public class EntradaDados {
    public static void main(String[] args) {
 
        Scanner teclado = new Scanner(System.in);

        System.out.println("--- ENTRADA DE DADOS ---");

        // 1. Byte (Números pequenos: -128 a 127)
        System.out.print("Digite um valor byte (ex: idade, nivel): ");
        byte valorByte = teclado.nextByte();

        // 2. Short (Números médios: -32.768 a 32.767)
        System.out.print("Digite um valor short (ex: ano atual): ");
        short valorShort = teclado.nextShort();

        // 3. Int (Inteiro padrão)
        System.out.print("Digite um valor int (ex: quantidade): ");
        int valorInt = teclado.nextInt();

        // 4. Long (Inteiros gigantes)
        System.out.print("Digite um valor long (ex: milissegundos): ");
        long valorLong = teclado.nextLong();

        // 5. Float (Decimal menos preciso)
        System.out.print("Digite um valor float (use ponto .): ");
        float valorFloat = teclado.nextFloat();

        // 6. Double (Decimal preciso - padrão para dinheiro/médias)
        System.out.print("Digite um valor double (use ponto .): ");
        double valorDouble = teclado.nextDouble();

        // 7. Char (Apenas um caractere)
        System.out.print("Digite um caractere (apenas 1 letra): ");
        // O Scanner não tem nextChar, então pegamos a palavra e a 1ª letra dela
        char letra = teclado.next().charAt(0);

        // 8. Boolean (Verdadeiro ou Falso)
        System.out.print("Digite true ou false: ");
        boolean resposta = teclado.nextBoolean();

        // Saída dos dados
        System.out.println("\n--- RELATÓRIO DOS DADOS ---");
        System.out.println("Byte digitado: " + valorByte);
        System.out.println("Short digitado: " + valorShort);
        System.out.println("Int digitado: " + valorInt);
        System.out.println("Long digitado: " + valorLong);
        System.out.println("Float digitado: " + valorFloat);
        System.out.println("Double digitado: " + valorDouble);
        System.out.println("Char digitado: " + letra);
        System.out.println("Boolean digitado: " + resposta);

        // Boa prática: fechar o scanner para liberar memória
        teclado.close();
    }
}
