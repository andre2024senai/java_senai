# Switch Case
## Sistema de Menu de Comida em Java

### Este exemplo demonstra:
### Entrada de dados com Scanner
### Estrutura de decisão switch case
### Estrutura de repetição while

---
```java
import java.util.Scanner; // Importa a classe Scanner para entrada de dados

public class MenuComida {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in); // Cria o objeto Scanner
        int opcao; // Variável para armazenar a opção escolhida pelo usuário
        double total = 0; // Variável para armazenar o valor total do pedido

        // Estrutura de repetição para manter o menu ativo
        do {
            // Exibição do menu
            System.out.println("===== MENU DE COMIDA =====");
            System.out.println("1 - Hambúrguer (R$ 15.00)");
            System.out.println("2 - Pizza (R$ 30.00)");
            System.out.println("3 - Refrigerante (R$ 5.00)");
            System.out.println("4 - Finalizar Pedido");
            System.out.print("Escolha uma opção: ");

            opcao = leitor.nextInt(); // Lê a opção digitada

            // Estrutura de decisão
            switch (opcao) {

                case 1:
                    System.out.println("Você escolheu Hambúrguer ");
                    total += 15.00; // Soma ao total
                    break;

                case 2:
                    System.out.println("Você escolheu Pizza ");
                    total += 30.00;
                    break;

                case 3:
                    System.out.println("Você escolheu Refrigerante ");
                    total += 5.00;
                    break;

                case 4:
                    System.out.println("Finalizando pedido...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            System.out.println("Total parcial: R$ " + total);
            System.out.println();

        } while (opcao != 4); // O menu continua até o usuário escolher 4

        System.out.println("Valor total do pedido: R$ " + total);
        System.out.println("Obrigado pela preferência!");

        leitor.close(); // Fecha o Scanner
    }
}
```
---

