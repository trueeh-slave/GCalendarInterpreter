package com.gcalendarinterpreter.view;

import javax.swing.*;
import java.awt.*;

public class LexicalAnalyzerView extends JFrame {
    public JTextPane textPane;
    public JButton compileButton;
    public JButton uploadButton;
    public JLabel resultLabel;

    public LexicalAnalyzerView() {
        initUI();
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
    }
}
