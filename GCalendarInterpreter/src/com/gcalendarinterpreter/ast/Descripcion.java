package com.gcalendarinterpreter.ast;

public class Descripcion extends ASTNode {
    private String descripcion;

    public Descripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public Object evaluate() {
        System.out.println("Descripcion: " + descripcion);
        return descripcion;
    }
}
