package org.example.models;

public class Lexeme {
    LexemeType type;
    String value;

    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    public Lexeme(LexemeType type) {
        this.value = type.getValue();
        this.type = type;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }

    public LexemeType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
