package org.example;

import java.util.List;

public class LexemeBuffer {
    private int position;
    public  List<Lexeme> lexemes;
    public LexemeBuffer(List<Lexeme> lexemes){
        this.lexemes = lexemes;
    }
    public void back() {
        position--;
    }

    public Lexeme next(){
        return lexemes.get(position++);
    }

    public int getPosition() {
        return position;
    }
}
