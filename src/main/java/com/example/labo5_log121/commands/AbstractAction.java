package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;

public abstract class AbstractAction {

    protected final PerspectiveModel perspective;

    public AbstractAction(PerspectiveModel perspective) {
        this.perspective = perspective;
    }

    public abstract void actionPerformed(Event event);
}
