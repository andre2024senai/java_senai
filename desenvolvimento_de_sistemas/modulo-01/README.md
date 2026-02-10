# Aula 01 - Entrada de Dados com Scanner

Nesta aula vamos aprender a capturar dados que o usuário digita no teclado utilizando a classe `Scanner`.

---

## 1️⃣ Configurando o Scanner

Todo programa com entrada de dados precisa importar a ferramenta no início do arquivo:

```java
import java.util.Scanner;

public class Exemplo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // Seus comandos de leitura aqui...
        
        teclado.close(); // Sempre feche o scanner no final!
    }
}
