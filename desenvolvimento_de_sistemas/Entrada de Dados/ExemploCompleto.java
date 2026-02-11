import java.util.Scanner;
//Biblioteca java.util (Caixa de Ferramentas)

public class TiposComScanner {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Byte: ");
        byte b = teclado.nextByte();

        System.out.print("Short: ");
        short s = teclado.nextShort();

        System.out.print("Int: ");
        int i = teclado.nextInt();

        System.out.print("Long: ");
        long l = teclado.nextLong();

        System.out.print("Float: ");
        float f = teclado.nextFloat();

        System.out.print("Double: ");
        double d = teclado.nextDouble();

        System.out.print("Char: ");
        char c = teclado.next().charAt(0);

        System.out.print("Boolean (true/false): ");
        boolean bo = teclado.nextBoolean();

        System.out.println("\n--- Valores digitados ---");
        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(c);
        System.out.println(bo);

        teclado.close();
    }
}
