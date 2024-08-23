package com.gcalendarinterpreter.controller;

import com.gcalendarinterpreter.view.LexicalAnalyzerView;

public class Main {
    public static void main(String[] args) {
        LexicalAnalyzerView view = new LexicalAnalyzerView();
        LexicalAnalyzerController controller = new LexicalAnalyzerController(view);
        view.setVisible(true);
    }
}
