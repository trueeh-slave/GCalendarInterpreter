package com.gcalendarinterpreter.ast;

public class Program extends ASTNode {
    private InstructionsList instructions;

    public Program(InstructionsList instructions) {
        this.instructions = instructions;
    }

    @Override
    public Object evaluate() {
        return instructions.evaluate();
    }
}
