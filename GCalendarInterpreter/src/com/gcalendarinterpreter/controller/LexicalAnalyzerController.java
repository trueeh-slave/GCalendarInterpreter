
package com.gcalendarinterpreter.controller;

//import com.gcalendarinterpreter.ast.Token;
//import com.gcalendarinterpreter.ast.Tokenizer;
//import com.gcalendarinterpreter.ast.exceptions.LexerException;
import com.gcalendarinterpreter.ast.Program;
import com.gcalendarinterpreter.lexer.*;
import com.gcalendarinterpreter.parser.*;
import com.gcalendarinterpreter.view.LexicalAnalyzerView;

import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class LexicalAnalyzerController {
    private LexicalAnalyzerView view;

    private Lexer lexer;
    private parser parser;
    private Program program = null;

    //    private Tokenizer tokenizer;

    public LexicalAnalyzerController(LexicalAnalyzerView view) {
        this.view = view;
//        this.tokenizer = new Tokenizer();
//        configureTokenizer();

        initController();

    }


//    private void configureTokenizer() {
//        tokenizer.add("\\bnew\\b", Token.NEW);
//        tokenizer.add("\\bEvento\\b", Token.EVENTO);
//        tokenizer.add("\\bTarea\\b", Token.EVENTO);
//        tokenizer.add("\\bTitulo\\b", Token.TITULO);
//        tokenizer.add("\\bFechaInicio\\b", Token.FECHAINICIO);
//        tokenizer.add("\\bFechaFin\\b", Token.FECHAFIN);
//        tokenizer.add("\\bUbicacion\\b", Token.UBICACION);
//        tokenizer.add("\\bDescripcion\\b", Token.DESCRIPCION);
//        tokenizer.add("\\bColor\\b", Token.COLOR);
//        tokenizer.add(":", Token.DOBLE_DOT);
//        tokenizer.add("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}", Token.DATE);
//        tokenizer.add("(?:[01]\\d|2[0-3]):[0-5]\\d", Token.HOUR);
//        tokenizer.add("\"[^\"]*\"", Token.STRING);
//        tokenizer.add("[|]", Token.SEPARATOR);
//    }

    private void initController() {
        view.compileButton.addActionListener(e -> compileText());

        view.uploadButton.addActionListener(e -> uploadFile());
    }

    private void compileText() {
        String text = view.textPane.getText();
        StyledDocument doc = view.textPane.getStyledDocument();
        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);

        StringReader reader = new StringReader(text);


        try {
//            tokenizer.tokenize(text);
//            view.resultLabel.setText("Sintaxis correcta");
//            parseTokens();
            lexer = new Lexer(reader);
            parser = new parser(lexer);
            program = (Program) parser.parse().value;

        } catch (Exception ex) {
            view.resultLabel.setText("Sintaxis incorrecta: " + ex.getMessage());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }



    private void uploadFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.replaceAll("##.*?##", "");
                    content.append(line).append("\n");
                }
                view.textPane.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
