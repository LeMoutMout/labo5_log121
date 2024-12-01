package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;

public class RedoAction extends AbstractAction {

    public RedoAction(PerspectiveModel perspective) {
        super(perspective);
    }

    @Override
    public void actionPerformed(Event event) {
        CommandManager.getInstance().redo(perspective);
        if(CommandManager.getInstance().isEmptyRedoStack(perspective)){
            perspective.setRedoButtonDisabled(true);
        }
    }
}
