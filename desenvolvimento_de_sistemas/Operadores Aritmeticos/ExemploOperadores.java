import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- CALCULADORA ---");
        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();

        // 1. OPERADORES ARITMÉTICOS BÁSICOS
        System.out.println("\n--- Operadores Básicos ---");
        System.out.println("Soma (+): " + (num1 + num2));
        System.out.println("Subtração (-): " + (num1 - num2));
        System.out.println("Multiplicação (*): " + (num1 * num2));
        System.out.println("Divisão (/): " + (num1 / num2));
        System.out.println("Módulo/Resto (%): " + (num1 % num2));

        // 2. CLASSE MATH (Matemática Avançada)
        System.out.println("\n--- Classe Math (Avançado) ---");
        
        // Potência (num1 elevado a num2)
        System.out.println("Potência (POW): " + Math.pow(num1, num2));
        
        // Raiz Quadrada
        System.out.println("Raiz Quadrada de " + num1 + ": " + Math.sqrt(num1));
        
        // Valor Absoluto (torna positivo)
        System.out.println("Valor Absoluto de -10: " + Math.abs(-10));
        
        // Maior e Menor
        System.out.println("O maior número é: " + Math.max(num1, num2));
        
        // Arredondamento
        System.out.println("Arredondando 5.8 para cima (Ceil): " + Math.ceil(5.8));
        System.out.println("Arredondando 5.8 para baixo (Floor): " + Math.floor(5.8));
        System.out.println("Arredondando 5.8 aritmeticamente (Round): " + Math.round(5.8));

        // Número Aleatório (0.0 até 1.0)
        System.out.println("Número Aleatório: " + Math.random());

        scanner.close();
    }
}
