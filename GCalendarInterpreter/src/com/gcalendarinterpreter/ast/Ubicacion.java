package com.gcalendarinterpreter.ast;

public class Ubicacion extends ASTNode {
    private String ubicacion;

    public Ubicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public Object evaluate() {
        System.out.println("Ubicacion: " + ubicacion);
        return ubicacion;
    }
}
