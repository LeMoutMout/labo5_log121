package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.models.Subject;
import javafx.event.ActionEvent;

public abstract class AbstractAction {

    protected final PerspectiveModel perspective;

    public AbstractAction(PerspectiveModel subject) {
        this.perspective = subject;
    }

    public abstract void actionPerformed(ActionEvent event);
}
