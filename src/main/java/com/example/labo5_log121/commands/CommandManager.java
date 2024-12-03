package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import com.example.labo5_log121.commands.Memento;


public class CommandManager {
    private static final CommandManager commandManagerInstance = new CommandManager();
    private final Map<String, Stack<Memento>> redoStackMap;
    private final Map<String, Stack<Memento>> undoStackMap;

    private CommandManager(){
        undoStackMap = new HashMap<>();
        redoStackMap = new HashMap<>();
    }

    public static CommandManager getInstance(){
        return commandManagerInstance;
    }

    public void undo(PerspectiveModel perspectiveModel){
        Stack<Memento> undoStack = getUndoStack(perspectiveModel.getUniqueId());
        if(!undoStack.isEmpty()){
            Memento memento = undoStack.pop();
            perspectiveModel.restore(memento);
            getRedoStack(perspectiveModel.getUniqueId()).push(memento);
        }
    }

    public void redo(PerspectiveModel perspectiveModel){
        Stack<Memento> redoStack = getRedoStack(perspectiveModel.getUniqueId());
        if(!redoStack.isEmpty()){
            Memento memento = redoStack.pop();
            perspectiveModel.restore(memento);
            getUndoStack(perspectiveModel.getUniqueId()).push(memento);
        }
    }

    public void add(String uniqueId, Memento memento){
        getUndoStack(uniqueId).push(memento);
        getRedoStack(uniqueId).clear();
    }

    public boolean isEmptyUndoStack(String uniqueId){
        Stack<Memento> undoStack = getUndoStack(uniqueId);
        return undoStack.isEmpty();
    }

    public boolean isEmptyRedoStack(String uniqueID){
        Stack<Memento> redoStack = getRedoStack(uniqueID);
        return redoStack.isEmpty();
    }

    private Stack<Memento> getUndoStack(String uniqueId) {
        return undoStackMap.computeIfAbsent(uniqueId, k -> new Stack<>());
    }

    private Stack<Memento> getRedoStack(String uniqueId) {
        return redoStackMap.computeIfAbsent(uniqueId, k -> new Stack<>());
    }

    public Map<String, Stack<Memento>> getUndoStacks() {
        return undoStackMap;
    }

    public void restoreUndoStacks(Map<String, Stack<Memento>> undoStacks) {
        this.undoStackMap.clear();
        this.undoStackMap.putAll(undoStacks);
    }
}
