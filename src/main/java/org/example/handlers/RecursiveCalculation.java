package org.example.handlers;

import org.example.models.Lexeme;
import org.example.models.LexemeType;

import java.util.function.BinaryOperator;

public class RecursiveCalculation {
    BinaryOperator<Double> binaryOperator;
    public double factor(LexemeBuffer lexemes){
        Lexeme lexeme = lexemes.next();
        switch (lexeme.getType()){
            case MINUS:
                double value = factor(lexemes);
                return -value;
            case NUMBER:
                return Double.parseDouble(lexeme.getValue());
            case OPEN_BRACKET:
                value = expr(lexemes);
                lexeme = lexemes.next();
                if (lexeme.getType() != LexemeType.CLOSE_BRACKET) {
                    throw new RuntimeException("Unexpected token " + lexeme.getValue() + " at position " + lexemes.getPosition());
                }
                return value;
            default: {
                throw new RuntimeException("Unexpected token " + lexeme.getValue() + " at position " + lexemes.getPosition());
            }
        }
    }
    public double plusMinus(LexemeBuffer lexemes){
        double value = mulDiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            if (lexeme.getType() == LexemeType.PLUS || lexeme.getType() == LexemeType.MINUS){
                lambdaOperations(lexeme.getType());
                value = binaryOperator.apply(value, mulDiv(lexemes));
            } else {
                lexemes.back();
                return value;
            }
        }
    }
    public double mulDiv(LexemeBuffer lexemes){
        double value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            if (lexeme.getType() == LexemeType.MULTIPLY || lexeme.getType() == LexemeType.DIVIDE){
                lambdaOperations(lexeme.getType());
                value = binaryOperator.apply(value, factor(lexemes));
            } else {
                lexemes.back();
                return value;
            }
        }
    }
    public double expr(LexemeBuffer lexemes){
        Lexeme lexeme = lexemes.next();
        if (lexeme.getType() == LexemeType.EOF){
            return 0;
        } else {
            lexemes.back();
            return plusMinus(lexemes);
        }
    }

    public void lambdaOperations(LexemeType operator) {
        switch (operator) {
            case PLUS: {
                binaryOperator = Double::sum;
                break;
            }
            case MINUS: {
                binaryOperator = (x, y) -> x - y;
                break;
            }
            case MULTIPLY: {
                binaryOperator = (x, y) -> x * y;
                break;
            }
            case DIVIDE: {
                binaryOperator = (x, y) -> x / y;
                break;
            }
            default: {
                throw new RuntimeException("Unexpected operator: " + operator);
            }
        }
    }
}
