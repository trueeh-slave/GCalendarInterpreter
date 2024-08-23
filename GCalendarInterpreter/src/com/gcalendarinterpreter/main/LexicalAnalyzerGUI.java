package com.gcalendarinterpreter.main;

import com.gcalendarinterpreter.model.exceptions.LexerException;
import com.gcalendarinterpreter.model.Token;
import com.gcalendarinterpreter.model.Tokenizer;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzerGUI extends JFrame {

    private JTextPane textPane;
    private JButton compileButton;
    private JButton uploadButton;
    private JLabel resultLabel;
    private Tokenizer tokenizer;

    public LexicalAnalyzerGUI() {
        tokenizer = new Tokenizer();
        configureTokenizer();
        initUI();
        for (Token tok : tokenizer.getTokens()) {
            System.out.println("[Token: " + tok.token + ", Lexema: '" + tok.lexeme + "', Posición: " + tok.pos + "]");
        }
    }

    public void configureTokenizer() {
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

    private void initUI() {
        setTitle("Lexical Analyzer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane = new JTextPane();
        compileButton = new JButton("Compilar");
        uploadButton = new JButton("Subir archivo");
        resultLabel = new JLabel("");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(uploadButton);
        buttonPanel.add(compileButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(textPane), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(resultLabel, BorderLayout.NORTH);

        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compileText();
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFile();
            }
        });
    }

    private void compileText() {
        String text = textPane.getText();
        StyledDocument doc = textPane.getStyledDocument();
        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);

        try {
            tokenizer.tokenize(text);
            resultLabel.setText("Sintaxis correcta");
            colorTokens(doc);
        } catch (LexerException ex) {
            resultLabel.setText("Sintaxis incorrecta: " + ex.getMessage());
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
                textPane.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void colorTokens(StyledDocument doc) {
        for (Token token : tokenizer.getTokens()) {
            SimpleAttributeSet attrSet = new SimpleAttributeSet();
            StyleConstants.setForeground(attrSet, getColorForToken(token.token));
            doc.setCharacterAttributes(token.pos, token.lexeme.length(), attrSet, false);
        }
    }

    private Color getColorForToken(int tokenType) {
        switch (tokenType) {
            case Token.NEW: return Color.BLUE;
            case Token.FUNCTION: return Color.MAGENTA;
            case Token.TITULO:
            case Token.FECHAINICIO:
            case Token.FECHAFIN:
            case Token.HASTA:
            case Token.UBICACION:
            case Token.DESCRIPCION:
            case Token.COLOR: return Color.ORANGE;
            case Token.DOBLE_DOT:
            case Token.OPEN_BRACKET:
            case Token.CLOSE_BRACKET:
            case Token.COMMA: return Color.RED;
            case Token.DATE: return Color.GREEN;
            case Token.STRING: return Color.DARK_GRAY;
            default: return Color.BLACK;
        }
    }

    public static void main(String[] args) {
            LexicalAnalyzerGUI gui = new LexicalAnalyzerGUI();
            gui.setVisible(true);
    }
}
