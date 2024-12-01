package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
<<<<<<< HEAD
=======
import javafx.event.ActionEvent;
>>>>>>> thomas
import javafx.event.Event;

public abstract class AbstractAction {

    protected final PerspectiveModel perspective;

    public AbstractAction(PerspectiveModel perspective) {
        this.perspective = perspective;
    }

    public abstract void actionPerformed(Event event);
<<<<<<< HEAD
=======

    public Memento getMemento() {
        return perspective.createMemento();
    }
>>>>>>> thomas
}

