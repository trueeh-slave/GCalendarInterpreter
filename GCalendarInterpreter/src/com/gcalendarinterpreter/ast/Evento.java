package com.gcalendarinterpreter.ast;

public class Evento extends ASTNode{
    private Titulo titulo;
    private FechaInicio fechaInicio;
    private FechaFin fechaFin;
    private Ubicacion ubicacion;
    private Descripcion descripcion;

    public Evento(Titulo titulo, FechaInicio fechaInicio, FechaFin fechaFin, Ubicacion ubicacion, Descripcion descripcion) {
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    @Override
    public Object evaluate() {
        System.out.println("Evento:");
        titulo.evaluate();
        fechaInicio.evaluate();
        fechaFin.evaluate();
        ubicacion.evaluate();
        descripcion.evaluate();
        return null;
    }
}
