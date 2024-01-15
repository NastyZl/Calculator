package org.example;

import org.example.handlers.LexemeAnalysis;
import org.example.models.Lexeme;
import org.example.models.LexemeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LexemeAnalysisTest {
    private LexemeAnalysis analyser;
    private String expression;

    @BeforeEach
    void setUp() {
        analyser = new LexemeAnalysis();
        expression = "(2+2)*2";
    }

    @AfterEach
    void tearDown() {
    }
  
    @Test
    void analysis() {
    }
    @Test
    public void testAnalysisEmptyInput() {
        String emptyExpression = "";
        List<Lexeme> lexemes = analyser.analysis(emptyExpression);
        LexemeType[] expectedTypes = new LexemeType[]{
                LexemeType.EOF
        };
        assertAll(
                () -> assertEquals(expectedTypes.length, lexemes.size()),
                () -> assertEquals(expectedTypes[0], lexemes.get(0).getType())
        );
    }

}