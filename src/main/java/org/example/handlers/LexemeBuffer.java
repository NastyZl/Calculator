package org.example.handlers;

import org.example.models.Lexeme;
import org.example.models.LexemeType;

import java.util.List;

public class LexemeBuffer {
    private int position;
    public List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
        setLastItemIfNotEof();
    }

    public void back() {
        position--;
    }

    public Lexeme next() {
        return lexemes.get(position++);
    }

    public int getPosition() {
        return position;
    }

    private void setLastItemIfNotEof() {
        if (lexemes != null && !lexemes.isEmpty() &&
                lexemes.get(lexemes.size() - 1).getType() != LexemeType.EOF) {
            lexemes.add(new Lexeme(LexemeType.EOF, ""));
        }
    }
}
