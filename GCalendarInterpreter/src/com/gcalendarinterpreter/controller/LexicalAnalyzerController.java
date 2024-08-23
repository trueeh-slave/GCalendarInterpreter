
package com.gcalendarinterpreter.controller;

import com.gcalendarinterpreter.model.Token;
import com.gcalendarinterpreter.model.Tokenizer;
import com.gcalendarinterpreter.model.exceptions.LexerException;
import com.gcalendarinterpreter.view.LexicalAnalyzerView;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzerController {
    private LexicalAnalyzerView view;
    private Tokenizer tokenizer;

    public LexicalAnalyzerController(LexicalAnalyzerView view) {
        this.view = view;
        this.tokenizer = new Tokenizer();
        configureTokenizer();
        initController();
    }

    private void configureTokenizer() {
        tokenizer.add("\\bnew\\b", Token.NEW);
        tokenizer.add("\\bEvento\\b", Token.FUNCTION);
        tokenizer.add("\\bTarea\\b", Token.FUNCTION);
        tokenizer.add("\\bTitulo\\b", Token.TITULO);
        tokenizer.add("\\bFechaInicio\\b", Token.FECHAINICIO);
        tokenizer.add("\\bFechaFin\\b", Token.FECHAFIN);
        tokenizer.add("\\bHasta\\b", Token.HASTA);
        tokenizer.add("\\bUbicacion\\b", Token.UBICACION);
        tokenizer.add("\\bDescripcion\\b", Token.DESCRIPCION);
        tokenizer.add("\\bColor\\b", Token.COLOR);
        tokenizer.add(":", Token.DOBLE_DOT);
        tokenizer.add("\\[", Token.OPEN_BRACKET);
        tokenizer.add("]", Token.CLOSE_BRACKET);
        tokenizer.add(",", Token.COMMA);
        tokenizer.add("\\b(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{2} ([01][0-9]|2[0-3]):[0-5][0-9]\\b", Token.DATE);
        tokenizer.add("[“\"][^“”\"]*[”\"]", Token.STRING);
        tokenizer.add("#[0-9a-fA-F]{6}\\b", Token.COLOR);
    }

    private void initController() {
        view.compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compileText();
            }
        });

        view.uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFile();
            }
        });
    }

    private void compileText() {
        String text = view.textPane.getText();
        StyledDocument doc = view.textPane.getStyledDocument();
        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);

        try {
            tokenizer.tokenize(text);
            view.resultLabel.setText("Sintaxis correcta");
            displayTokens();
        } catch (LexerException ex) {
            view.resultLabel.setText("Sintaxis incorrecta: " + ex.getMessage());
            System.out.println(ex.getMessage());
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

    private void displayTokens() {
        StringBuilder tokensText = new StringBuilder();
        for (Token tok : tokenizer.getTokens()) {
            tokensText.append("[Token: ").append(tok.token)
                    .append(" Lexema: ").append(tok.lexeme)
                    .append(" Posicion: ").append(tok.pos).append("]\n");
        }
        view.tokensArea.setText(tokensText.toString());
    }
}
