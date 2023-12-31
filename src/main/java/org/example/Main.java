package org.example;

import java.util.Scanner;

public class Main
{
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        acceptValidInput(scanner);
        scanner.close();
    }

    public static void acceptValidInput(Scanner scanner) {
        Calculate calculate = new Calculate();
        String expression;
        do {
            System.out.println("Enter an expression! To shut down the calculator enter 'Exit'");
            try {
                expression = scanner.nextLine();
                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }
                calculate.execute(expression);
            } catch (Exception e) {
                System.out.println("Error in calculating the expression: " + e.getMessage());
            }
        } while (true);
        System.out.println("The calculator is finished.");
    }
}
