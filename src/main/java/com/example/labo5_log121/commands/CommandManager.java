package com.example.labo5_log121.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.example.labo5_log121.commands.Memento;


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
        if (action == null) {
            throw new IllegalArgumentException("La commande ne peut pas être null.");
        }

        try {
            action.actionPerformed(null);
            undoStack.push(action);
            redoStack.clear();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la commande : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void undo(){
        if(!undoStack.isEmpty()) {
            AbstractAction action = undoStack.pop();
            action.actionPerformed(null);
            redoStack.push(action);
        }
    }

    public void redo(){
        if(!redoStack.isEmpty()) {
            AbstractAction action = redoStack.pop();
            action.actionPerformed(null);
            undoStack.push(action);
        }
    }

}
