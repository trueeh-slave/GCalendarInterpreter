package com.gcalendarinterpreter.ast;

public class FechaInicio extends ASTNode {
    private String fechaHora;

    public FechaInicio(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public Object evaluate() {
        System.out.println("Fecha Inicio: " + fechaHora);
        return fechaHora;
    }
}