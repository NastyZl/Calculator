package org.example.models;

public enum LexemeType {
    OPEN_BRACKET("("), CLOSE_BRACKET(")"),
    PLUS("+"), MINUS("-"),
    MULTIPLY("*"), DIVIDE("/"),
    NUMBER("1"),
    EOF("");

    private final String value;

    LexemeType(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
