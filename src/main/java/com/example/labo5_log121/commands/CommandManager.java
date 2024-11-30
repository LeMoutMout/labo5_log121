package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import java.util.Stack;

public class CommandManager {
    private static final CommandManager commandManagerInstance = new CommandManager();
    private final Stack<Memento> redoStack;
    private final Stack<Memento> undoStack;

    private CommandManager(){
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public static CommandManager getInstance(){
        return commandManagerInstance;
    }

    public void undo(PerspectiveModel perspectiveModel){
        Memento memento = undoStack.pop();
        perspectiveModel.restore(memento);
        redoStack.push(memento);
    }

    public void redo(PerspectiveModel perspectiveModel){
        Memento memento = redoStack.pop();
        perspectiveModel.restore(memento);
        undoStack.push(memento);
    }

    public void add(Memento memento){
        undoStack.add(memento);
    }
}
