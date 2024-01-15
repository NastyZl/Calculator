package org.example.services;

import org.example.argument_providers.ExprArgumentsProvider;
import org.example.argument_providers.MulDivArgumentsProvider;
import org.example.argument_providers.PlusMinusArgumentsProvider;
import org.example.argument_providers.UnexpectedArgumentsProvider;
import org.example.models.Lexeme;
import org.example.models.LexemeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursiveCalculationTest {

    private static RecursiveCalculation recursiveCalculation;

    private LexemeBuffer buffer;

    @BeforeAll
    static void setUpAll() {
        recursiveCalculation = new RecursiveCalculation();
    }

    @ParameterizedTest
    @ArgumentsSource(PlusMinusArgumentsProvider.class)
    void plusMinusValidInput(List<Lexeme> lexemeList, double expected) {
        buffer = new LexemeBuffer(lexemeList);
        double actual = recursiveCalculation.plusMinus(buffer);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(MulDivArgumentsProvider.class)
    void mulDivValidInput(List<Lexeme> lexemeList, double expected) {
        buffer = new LexemeBuffer(lexemeList);
        double actual = recursiveCalculation.mulDiv(buffer);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(ExprArgumentsProvider.class)
    void exprValidInput(List<Lexeme> lexemeList, double expected) {
        buffer = new LexemeBuffer(lexemeList);
        double actual = recursiveCalculation.expr(buffer);
        assertEquals(expected, actual);
    }

    @Test
    void testLambdaOperationsUnexpectedType() {
        assertThrows(RuntimeException.class,
                () -> recursiveCalculation.lambdaOperations(LexemeType.UNKNOWN),
                "Unexpected operator:" + 1);
    }

    @ParameterizedTest
    @ArgumentsSource(UnexpectedArgumentsProvider.class)
    void testFactorWithUnexpectedTokenShouldThrow(List<Lexeme> lexemeList, int position) {
        buffer = new LexemeBuffer(lexemeList);
        assertThrows(RuntimeException.class,
                () -> recursiveCalculation.factor(buffer),
                "Unexpected token " + lexemeList.get(position - 1).getValue() + " at position " + (position));
    }

}