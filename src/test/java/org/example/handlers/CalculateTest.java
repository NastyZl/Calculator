package org.example.handlers;

import org.example.argument_providers.ExecuteArgumentsProvider;
import org.example.models.Lexeme;
import org.example.models.LexemeType;
import org.example.services.LexemeAnalysis;
import org.example.services.LexemeBuffer;
import org.example.services.RecursiveCalculation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateTest {
    @Spy
    private RecursiveCalculation recursiveCalculation;
    @Mock
    private LexemeAnalysis lexemeAnalysis;
    @InjectMocks
    private Calculate calculate;

    @ParameterizedTest
    @ArgumentsSource(ExecuteArgumentsProvider.class)
    void executeTest(List<Lexeme> lexemeList, String expression, double expected) {
        when(lexemeAnalysis.analysis(expression)).thenReturn(lexemeList);
        when(recursiveCalculation.expr(any(LexemeBuffer.class))).thenReturn(expected);
        double actual = calculate.execute(expression);
        assertEquals(expected, actual);
        verify(recursiveCalculation).lambdaOperations(LexemeType.PLUS);
        verify(recursiveCalculation).expr(any(LexemeBuffer.class));

    }
}