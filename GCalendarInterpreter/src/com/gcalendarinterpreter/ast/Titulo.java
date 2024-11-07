package com.gcalendarinterpreter.ast;

public class Titulo extends ASTNode {
    private String titulo;

    public Titulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public Object evaluate() {
        System.out.println("Titulo: " + titulo);
        return titulo;
    }
}
