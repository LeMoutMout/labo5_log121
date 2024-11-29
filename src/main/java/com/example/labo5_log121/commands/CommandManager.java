package com.example.labo5_log121.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandManager {
    private static final CommandManager commandManagerInstance = new CommandManager();
    private final Stack<AbstractAction> undoStack;
    private final Stack<AbstractAction> redoStack;
    private final List<Memento> history;

    private CommandManager(){
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        history = new ArrayList<>();
    }

    public static CommandManager getInstance(){
        return commandManagerInstance;
    }

    public void executeCommand(AbstractAction action){
        action.actionPerformed(null);
        undoStack.push(action);
        redoStack.clear();
    }

    public void undo(){
        AbstractAction action = undoStack.pop();
        action.actionPerformed(null);
        redoStack.push(action);
    }

    public void redo(){
        AbstractAction action = redoStack.pop();
        action.actionPerformed(null);
        undoStack.push(action);
    }
}
