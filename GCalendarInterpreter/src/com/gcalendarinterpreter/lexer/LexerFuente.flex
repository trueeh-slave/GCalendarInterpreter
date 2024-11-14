package com.gcalendarinterpreter.lexer;

import java_cup.runtime.Symbol;
import com.gcalendarinterpreter.parser.sym;

%%

%class Lexer
%line
%column
%cup
%cupdebug

%{

  private Symbol sym(int sym) {
    return new Symbol(sym);
  }

  private Symbol sym(int sym, Object val) {
    return new Symbol(sym, val);
  }

  public void printData(String type, String text){
    System.out.println("[" + type + ": " + text + "  Line: " + yyline + " Column: " + yycolumn + "]");
  }
%}

/* Definición de tokens */
Variable = [a-zA-Z_][a-zA-Z_0-9]*

%%

"new"              { printData("NEW", yytext()); return sym(sym.new); }
"Evento"           { printData("EVENTO", yytext()); return sym(sym.Evento); }
"Titulo"           { printData("TITULO", yytext()); return sym(sym.Titulo); }
"FechaInicio"      { printData("FECHAINICIO", yytext()); return sym(sym.FechaInicio); }
"FechaFin"         { printData("FECHAFIN", yytext()); return sym(sym.FechaFin); }
"Ubicacion"        { printData("UBICACION", yytext()); return sym(sym.Ubicacion); }
"Descripcion"      { printData("DESCRIPCION", yytext()); return sym(sym.Descripcion); }
":"                { printData("DOBLE_DOT", yytext()); return sym(sym.dos_puntos); }
[a-zA-Z0-9#\-\ ]+   { printData("STRING_LITERAL", yytext()); return sym(sym.STRING_LITERAL, yytext()); }

// Expresión regular para FECHA_LITERAL en formato DD-MM-AAAA
(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4} { printData("FECHA_LITERAL", yytext()); return sym(sym.FECHA_LITERAL, yytext()); }

// Expresión regular para HORA_LITERAL en formato HH:MM
([01]\\d|2[0-3]):[0-5]\\d                      { printData("HORA_LITERAL", yytext()); return sym(sym.HORA_LITERAL, yytext()); }

// Expresión regular para cadenas entre comillas
\"[^\"]*\"         { printData("STRING_LITERAL", yytext()); return sym(sym.STRING_LITERAL, yytext()); }

[\ \t\b\f\r\n]+    { /* eat whitespace */ }
"//"[^\n]*         { /* one-line comment */ }
.                  { throw new Error("Unexpected character ["+yytext()+"]"); }
