package vetor;

public class ExemploVetor {


    public static void main(String[] args) {

        // ====================================================
        // 1. VETOR UNIDIMENSIONAL (Array 1D)
        // ====================================================
        System.out.println("===== VETOR UNIDIMENSIONAL =====");

        // Declaração e inicialização
        int[] numeros = {40, 15, 70, 5, 30, 90, 25};

        // --- FOR com .length ---
        // .length retorna o tamanho do vetor (não usa parênteses!)
        System.out.println("\n>> Percorrendo com for e .length:");
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("numeros[" + i + "] = " + numeros[i]);
        }

        // --- FOR-EACH (forma alternativa) ---
        System.out.println("\n>> Percorrendo com for-each:");
        for (int num : numeros) {
            System.out.print(num + "  ");
        }
        System.out.println();


        // ====================================================
        // 2. SOMA DOS ELEMENTOS DO VETOR
        // ====================================================
        System.out.println("\n===== SOMA DOS ELEMENTOS =====");

        int soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i]; // mesmo que: soma = soma + numeros[i]
        }
        System.out.println("Soma total: " + soma);
        System.out.println("Média:      " + (soma / numeros.length));


        // ====================================================
        // 3. BUSCA LINEAR (sequencial)
        // ====================================================
        System.out.println("\n===== BUSCA LINEAR =====");

        int valorBuscado = 70;
        int posicaoEncontrada = -1; // -1 indica "não encontrado"

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == valorBuscado) {
                posicaoEncontrada = i;
                break; // para ao encontrar o primeiro
            }
        }

        if (posicaoEncontrada != -1) {
            System.out.println("Valor " + valorBuscado + " encontrado na posição: " + posicaoEncontrada);
        } else {
            System.out.println("Valor " + valorBuscado + " NÃO encontrado.");
        }


        // ====================================================
        // 4. ORDENAÇÃO - BUBBLE SORT
        // ====================================================
        System.out.println("\n===== ORDENAÇÃO - BUBBLE SORT =====");

        // Antes de ordenar, vamos exibir o vetor original
        System.out.print("Antes:  ");
        for (int n : numeros) System.out.print(n + "  ");
        System.out.println();

        // Algoritmo Bubble Sort
        // A ideia: percorre repetidamente e troca elementos adjacentes fora de ordem
        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = 0; j < numeros.length - 1 - i; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    // Troca os valores (usando variável auxiliar)
                    int aux = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = aux;
                }
            }
        }

        System.out.print("Depois: ");
        for (int n : numeros) System.out.print(n + "  ");
        System.out.println();

        // Busca Binária funciona melhor com vetor ORDENADO!
        // ====================================================
        // 5. BUSCA BINÁRIA (vetor deve estar ordenado)
        // ====================================================
        System.out.println("\n===== BUSCA BINÁRIA =====");

        int alvo = 30;
        int inicio = 0;
        int fim = numeros.length - 1;
        int resultado = -1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (numeros[meio] == alvo) {
                resultado = meio;
                break;
            } else if (numeros[meio] < alvo) {
                inicio = meio + 1; // busca na metade direita
            } else {
                fim = meio - 1;    // busca na metade esquerda
            }
        }

        if (resultado != -1) {
            System.out.println("Valor " + alvo + " encontrado na posição: " + resultado);
        } else {
            System.out.println("Valor " + alvo + " NÃO encontrado.");
        }


        // ====================================================
        // 6. VETOR BIDIMENSIONAL (Matriz / Array 2D)
        // ====================================================
        System.out.println("\n===== VETOR BIDIMENSIONAL (MATRIZ) =====");

        // Declaração: [linhas][colunas]
        int[][] matriz = {
            {1,  2,  3},   // linha 0
            {4,  5,  6},   // linha 1
            {7,  8,  9}    // linha 2
        };

        // Percorrendo com for e .length
        // matriz.length         → número de LINHAS
        // matriz[i].length      → número de COLUNAS da linha i
        System.out.println("\n>> Exibindo a matriz:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%4d", matriz[i][j]); // formatado em 4 espaços
            }
            System.out.println(); // quebra de linha ao terminar a linha
        }


        // ====================================================
        // 7. SOMA DOS ELEMENTOS DA MATRIZ
        // ====================================================
        System.out.println("\n===== SOMA DOS ELEMENTOS DA MATRIZ =====");

        int somaMatriz = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                somaMatriz += matriz[i][j];
            }
        }
        System.out.println("Soma total da matriz: " + somaMatriz);


        // ====================================================
        // 8. BUSCA EM MATRIZ
        // ====================================================
        System.out.println("\n===== BUSCA EM MATRIZ =====");

        int valorMatriz = 6;
        boolean achou = false;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == valorMatriz) {
                    System.out.println("Valor " + valorMatriz +
                        " encontrado em [" + i + "][" + j + "]");
                    achou = true;
                }
            }
        }
        if (!achou) {
            System.out.println("Valor " + valorMatriz + " NÃO encontrado na matriz.");
        }


        // ====================================================
        // RESUMO DOS CONCEITOS
        // ====================================================
        System.out.println("\n===== RESUMO =====");
        System.out.println("vetor.length          → tamanho do vetor 1D");
        System.out.println("matriz.length         → número de linhas da matriz");
        System.out.println("matriz[i].length      → número de colunas da linha i");
        System.out.println("Bubble Sort           → ordena comparando pares adjacentes");
        System.out.println("Busca Linear          → percorre do início ao fim");
        System.out.println("Busca Binária         → divide ao meio (vetor ordenado!)");
    }
}


