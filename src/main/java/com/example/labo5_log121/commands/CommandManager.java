package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import com.example.labo5_log121.commands.Memento;


public class CommandManager {
    private static final CommandManager commandManagerInstance = new CommandManager();
    private final Map<PerspectiveModel, Stack<Memento>> redoStackMap;
    private final Map<PerspectiveModel, Stack<Memento>> undoStackMap;

    private CommandManager(){
        undoStackMap = new HashMap<>();
        redoStackMap = new HashMap<>();
    }

    public static CommandManager getInstance(){
        return commandManagerInstance;
    }

    public void undo(PerspectiveModel perspectiveModel){
        Stack<Memento> undoStack = getUndoStack(perspectiveModel);
        if(!undoStack.isEmpty()){
            Memento memento = undoStack.pop();
            perspectiveModel.restore(memento);
            getRedoStack(perspectiveModel).push(memento);
        }
    }

    public void redo(PerspectiveModel perspectiveModel){
        Stack<Memento> redoStack = getRedoStack(perspectiveModel);
        if(!redoStack.isEmpty()){
            Memento memento = redoStack.pop();
            perspectiveModel.restore(memento);
            getUndoStack(perspectiveModel).push(memento);
        }
    }

    public void add(PerspectiveModel perspectiveModel, Memento memento){
        getUndoStack(perspectiveModel).push(memento);
        getRedoStack(perspectiveModel).clear();
    }

    public boolean isEmptyUndoStack(PerspectiveModel perspectiveModel){
        Stack<Memento> undoStack = getUndoStack(perspectiveModel);
        return undoStack.isEmpty();
    }

    public boolean isEmptyRedoStack(PerspectiveModel perspectiveModel){
        Stack<Memento> redoStack = getRedoStack(perspectiveModel);
        return redoStack.isEmpty();
    }

    private Stack<Memento> getUndoStack(PerspectiveModel perspectiveModel) {
        return undoStackMap.computeIfAbsent(perspectiveModel, k -> new Stack<>());
    }

    private Stack<Memento> getRedoStack(PerspectiveModel perspectiveModel) {
        return redoStackMap.computeIfAbsent(perspectiveModel, k -> new Stack<>());
    }
}
