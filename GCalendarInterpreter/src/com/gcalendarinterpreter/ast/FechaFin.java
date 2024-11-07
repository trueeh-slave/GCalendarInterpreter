package com.gcalendarinterpreter.ast;

public class FechaFin extends ASTNode {
    private String fechaHora;

    public FechaFin(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public Object evaluate() {
        System.out.println("Fecha Fin: " + fechaHora);
        return fechaHora;
    }
}
