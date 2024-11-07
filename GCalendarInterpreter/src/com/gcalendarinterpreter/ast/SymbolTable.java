package com.gcalendarinterpreter.ast;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Symbol> symbols = new HashMap<>();

    public void enter(Symbol symbol) {
        symbols.put(symbol.name, symbol);
    }

    public Symbol lookup(String name) {
        return symbols.get(name);
    }
}