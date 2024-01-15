package org.example;

import org.example.handlers.Calculate;
import org.example.services.LexemeAnalysis;
import org.example.services.RecursiveCalculation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        acceptValidInput(scanner);
        scanner.close();
    }

    public static void acceptValidInput(Scanner scanner) {
        Calculate calculate = new Calculate(new LexemeAnalysis(), new RecursiveCalculation());
        String expression;
        do {
            System.out.println("Enter an expression! To shut down the calculator enter 'Exit'");
            try {
                expression = scanner.nextLine();
                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println(calculate.execute(expression));
            } catch (Exception e) {
                System.out.println("Error in calculating the expression: " + e.getMessage());
            }
        } while (true);
        System.out.println("The calculator is finished.");
    }
}
