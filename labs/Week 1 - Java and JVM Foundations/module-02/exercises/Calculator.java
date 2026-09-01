import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("First number: ");
        // TODO: read the first number as a double (hint: Double.parseDouble(scanner.nextLine()))
        double a = Double.parseDouble(scanner.nextLine());

        System.out.print("Second number: ");
        // TODO: read the second number as a double
        double b = Double.parseDouble(scanner.nextLine());

        // TODO: print Sum, Difference, Product, and Quotient using printf with %.2f
        // System.out.printf("Sum: %.2f%n", _____);
        System.out.printf("Sum: %.2f%n", a+b);
        // System.out.printf("Difference: %.2f%n", _____);
        System.out.printf("Difference: %.2f%n",a-b);
        // System.out.printf("Product: %.2f%n", _____);
        System.out.printf("Product: %.2f%n", a*b);
        // System.out.printf("Quotient: %.2f%n", _____);
        System.out.printf("Quotient: %.2f%n", a/b);

        scanner.close();
    }
}