package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;

public class UndoAction extends AbstractAction {

    public UndoAction(PerspectiveModel perspective) {
        super(perspective);
    }

    @Override
    public void actionPerformed(Event event) {
        CommandManager.getInstance().undo(perspective);
        perspective.setRedoButtonDisabled(false);
        if(CommandManager.getInstance().isEmptyUndoStack(perspective)){
            perspective.setUndoButtonDisabled(true);
        }
    }
}
