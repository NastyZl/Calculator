package org.example.argument_providers;

import org.example.models.Lexeme;
import org.example.models.LexemeType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MulDivArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
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
}
