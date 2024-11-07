package com.gcalendarinterpreter.ast;

public class Symbol {
    public String name;
    public String type;
    public Object value;

    public Symbol(String name, String type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }
}
