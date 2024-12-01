package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.ActionEvent;
import javafx.event.Event;

public class ScaleAction extends AbstractAction {

    public ScaleAction(PerspectiveModel perspective) {
        super(perspective);
    }

    @Override
    public void actionPerformed(Event event) {
        //perspective.setScaleFactor(event.get);
    }
}
