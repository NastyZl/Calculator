package org.example.handlers;

import org.example.models.Lexeme;
import org.example.models.LexemeType;
import org.example.services.LexemeAnalysis;
import org.example.services.LexemeBuffer;
import org.example.services.RecursiveCalculation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateTest {
    @Spy
    private RecursiveCalculation recursiveCalculation;
    @Mock
    private LexemeAnalysis lexemeAnalysis;
    @InjectMocks
    private Calculate calculate;

    @ParameterizedTest
    @MethodSource("generate")
    void execute(List<Lexeme> lexemeList) {
        String expression = "(2+2)*2/2";
        double expected = 4.0;
        when(lexemeAnalysis.analysis(anyString())).thenReturn(lexemeList);

        assertEquals(expected, calculate.execute(expression));

        verifyNoMoreInteractions(lexemeAnalysis);
        verify(recursiveCalculation, never()).lambdaOperations(LexemeType.MINUS);
        verify(recursiveCalculation, atLeastOnce()).factor(any(LexemeBuffer.class));
        verify(recursiveCalculation, atLeastOnce()).plusMinus(any(LexemeBuffer.class));
        verify(recursiveCalculation, atLeastOnce()).mulDiv(any(LexemeBuffer.class));
        verify(recursiveCalculation, atLeastOnce()).lambdaOperations(any(LexemeType.class));
    }

    private static Stream<Arguments> generate() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(
                                new Lexeme(LexemeType.OPEN_BRACKET),
                                new Lexeme(LexemeType.NUMBER, "2"),
                                new Lexeme(LexemeType.PLUS),
                                new Lexeme(LexemeType.NUMBER, "2"),
                                new Lexeme(LexemeType.CLOSE_BRACKET),
                                new Lexeme(LexemeType.MULTIPLY),
                                new Lexeme(LexemeType.NUMBER, "2"),
                                new Lexeme(LexemeType.DIVIDE),
                                new Lexeme(LexemeType.NUMBER, "2"),
                                new Lexeme(LexemeType.EOF)
                        ))
                )
        );
    }
}