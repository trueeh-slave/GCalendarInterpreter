package com.gcalendarinterpreter.model;

public class Token {

    public static final int LAMBDA = 0;
    public static final int NEW = 1;
    public static final int FUNCTION = 2;
    public static final int DOBLE_DOT = 3;
    public static final int OPEN_BRACKET = 4;
    public static final int CLOSE_BRACKET = 5;
    public static final int TITULO = 6;
    public static final int FECHAINICIO = 7;
    public static final int FECHAFIN = 8;
    public static final int UBICACION = 9;
    public static final int DESCRIPCION = 10;
    public static final int COLOR = 11;
    public static final int HASTA = 12;
    public static final int STRING = 13;
    public static final int DATE = 14;
    public static final int COMMA = 15;
    public static final int HEX = 16;
    public static final int COMMENT = 17;

    
    public final int token;
    public final String lexeme;
    public final int pos;

    public Token(int token, String sequence, int pos) {
        super();
        this.token = token;
        this.lexeme = sequence;
        this.pos = pos;
    }
    
    public Token clone(){
        return new Token(this.token, this.lexeme, this.pos);
    }

}
