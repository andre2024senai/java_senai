# Aula 01 - Entrada de Dados com Scanner

Nesta aula vamos aprender a capturar dados que o usuário digita no teclado.

## 1️⃣ Configurando o Scanner
Todo programa com entrada de dados precisa importar a ferramenta:

```java
import java.util.Scanner;

Scanner teclado = new Scanner(System.in);
// Seus comandos aqui...
teclado.close(); // Encerra o scanner
