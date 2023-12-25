package org.example;

import java.util.List;

public class Calculate {
    public void execute(String expression){
        LexemeAnalysis lexemes = new LexemeAnalysis();
        List<Lexeme> analysis = lexemes.analysis(expression);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(analysis);
        RecursiveCalculation calculate = new RecursiveCalculation();
        System.out.println(calculate.expr(lexemeBuffer));
    }
}
