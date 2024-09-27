package com.gcalendarinterpreter.controller;

import com.gcalendarinterpreter.model.Token;
import com.gcalendarinterpreter.model.Tokenizer;
import com.gcalendarinterpreter.model.exceptions.LexerException;
import com.gcalendarinterpreter.parser.Parser;

public class testing {
    public static void main(String[] args) throws LexerException {
        Parser parser = new Parser();
        Tokenizer tokenizer = new Tokenizer();


        tokenizer.add("\\bnew\\b", Token.NEW);
        tokenizer.add("\\bEvento\\b", Token.EVENTO);
        tokenizer.add("\\bTarea\\b", Token.EVENTO);
        tokenizer.add("\\bTitulo\\b", Token.TITULO);
        tokenizer.add("\\bFechaInicio\\b", Token.FECHAINICIO);
        tokenizer.add("\\bFechaFin\\b", Token.FECHAFIN);
        tokenizer.add("\\bUbicacion\\b", Token.UBICACION);
        tokenizer.add("\\bDescripcion\\b", Token.DESCRIPCION);
        tokenizer.add("\\bColor\\b", Token.COLOR);
        tokenizer.add(":", Token.DOBLE_DOT);
        tokenizer.add("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}", Token.DATE);
        tokenizer.add("(?:[01]\\d|2[0-3]):[0-5]\\d", Token.HOUR);
        tokenizer.add("\"[^\"]*\"", Token.STRING);
        tokenizer.add("[|]", Token.SEPARATOR);


        tokenizer.tokenize("new Evento:\n" +
                "Titulo: \"reunion de #proyecto de grado\"\n" +
                "FechaInicio: 11-11-2023 | 11:11\n" +
                "FechaFin: 12-12-2023 | 12:00\n" +
                "Ubicacion: \"calle 160 #60-07\"\n" +
                "Descripcion: \"yupi\"\n");

        parser.parse(tokenizer.getTokens());

    }
}
