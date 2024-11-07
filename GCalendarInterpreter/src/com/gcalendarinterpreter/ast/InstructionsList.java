package com.gcalendarinterpreter.ast;

import java.util.ArrayList;
import java.util.List;

public class InstructionsList extends ASTNode{
    private List<ASTNode> instructions;

    public InstructionsList() {
        this.instructions = new ArrayList<>();
    }

    public InstructionsList(InstructionsList list, Evento event) {
        this.instructions = new ArrayList<>(list.getInstructions());
        this.instructions.add(event);
    }

    public InstructionsList(Evento event) {
        this.instructions = new ArrayList<>();
        this.instructions.add(event);
    }

    public List<ASTNode> getInstructions() {
        return instructions;
    }

    @Override
    public Object evaluate() {
        for (ASTNode instruction : instructions) {
            instruction.evaluate();
        }
        return null;
    }
}
