package org.example;

import org.example.handlers.LexemeBuffer;
import org.example.handlers.RecursiveCalculation;
import org.example.models.Lexeme;
import org.example.models.LexemeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecursiveCalculationTest {
    private static RecursiveCalculation recursiveCalculation;
    private List<Lexeme> lexemeList;

    @BeforeAll
    static void setUpAll() {
        recursiveCalculation = new RecursiveCalculation();
    }

    @BeforeEach
    void setUp() {
        lexemeList = new ArrayList<>();
    }

    @Test
    void factor() {
    }

    @Test
    void plusMinusValidInput() {
        lexemeList.add(new Lexeme(LexemeType.NUMBER, "2"));
        lexemeList.add(new Lexeme(LexemeType.PLUS));
        lexemeList.add(new Lexeme(LexemeType.NUMBER, "2"));

        LexemeBuffer buffer = new LexemeBuffer(lexemeList);

        double actual = recursiveCalculation.plusMinus(buffer);
        double expected = 4.0;

        assertEquals(expected, actual);
    }

    @Test
    public void testLambdaOperationPlus() {

    }

    @ParameterizedTest
    @MethodSource("generator")
    void mulDiv(List<Lexeme> lexemeList, double expected) {

        LexemeBuffer buffer = new LexemeBuffer(lexemeList);

        double actual = recursiveCalculation.plusMinus(buffer);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(
                        new Lexeme(LexemeType.NUMBER, "2"),
                        new Lexeme(LexemeType.DIVIDE),
                        new Lexeme(LexemeType.NUMBER, "2")
                )), 1.0),
                Arguments.of(new ArrayList<>(List.of(
                        new Lexeme(LexemeType.NUMBER, "2"),
                        new Lexeme(LexemeType.MULTIPLY),
                        new Lexeme(LexemeType.NUMBER, "2")
                )), 4.0));
    }

    @Test
    void expr() {
        lexemeList.add(new Lexeme(LexemeType.OPEN_BRACKET));
        lexemeList.add(new Lexeme(LexemeType.NUMBER, "2"));
        lexemeList.add(new Lexeme(LexemeType.PLUS));
        lexemeList.add(new Lexeme(LexemeType.NUMBER, "2"));
        lexemeList.add(new Lexeme(LexemeType.CLOSE_BRACKET));
        lexemeList.add(new Lexeme(LexemeType.MULTIPLY));
        lexemeList.add(new Lexeme(LexemeType.NUMBER, "2"));

        LexemeBuffer buffer = new LexemeBuffer(lexemeList);

        double actual = recursiveCalculation.expr(buffer);
        double expected = 8.0;

        assertEquals(expected, actual);
    }

    @Test
    void lambdaOperations() {
    }
}