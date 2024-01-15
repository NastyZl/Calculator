package org.example.handlers;

import org.example.models.Lexeme;
import org.example.services.LexemeAnalysis;
import org.example.services.LexemeBuffer;
import org.example.services.RecursiveCalculation;

import java.util.List;

public class Calculate {
    private final LexemeAnalysis lexemes;
    private final RecursiveCalculation calculate;

    public Calculate(LexemeAnalysis lexemes, RecursiveCalculation calculate) {
        this.lexemes = lexemes;
        this.calculate = calculate;
    }

    public double execute(String expression) {
        List<Lexeme> analysis = lexemes.analysis(expression);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(analysis);
        return calculate.expr(lexemeBuffer);
    }
}
