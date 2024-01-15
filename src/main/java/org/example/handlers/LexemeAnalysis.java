package org.example.handlers;

import org.example.models.Lexeme;
import org.example.models.LexemeType;

import java.util.ArrayList;
import java.util.List;

public class LexemeAnalysis {

    public List<Lexeme> analysis(String expression) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int position = 0;
        while (position < expression.length()) {
            char symbol = expression.charAt(position);
            switch (symbol) {
                case '(': {
                    lexemes.add(new Lexeme(LexemeType.OPEN_BRACKET, symbol));
                    position++;
                    break;
                }
                case ')': {
                    lexemes.add(new Lexeme(LexemeType.CLOSE_BRACKET, symbol));
                    position++;
                    break;
                }
                case '+': {
                    lexemes.add(new Lexeme(LexemeType.PLUS, symbol));
                    position++;
                    break;
                }
                case '-': {
                    lexemes.add(new Lexeme(LexemeType.MINUS, symbol));
                    position++;
                    break;
                }
                case '/': {
                    lexemes.add(new Lexeme(LexemeType.DIVIDE, symbol));
                    position++;
                    break;
                }
                case '*': {
                    lexemes.add(new Lexeme(LexemeType.MULTIPLY, symbol));
                    position++;
                    break;
                }
                default: {
                    if (symbol <= '9' && symbol >= '0') {
                        StringBuilder number = new StringBuilder();
                        do {
                            number.append(symbol);
                            position++;
                            if (position >= expression.length()) {
                                break;
                            }
                            symbol = expression.charAt(position);
                        } while (symbol <= '9' && symbol >= '0');
                        lexemes.add(new Lexeme(LexemeType.NUMBER
                                , number.toString()));
                    } else {
                        if (symbol != ' ') {
                            throw new RuntimeException("Unexpected character: " + symbol);
                        }
                        position++;
                    }
                }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

}
