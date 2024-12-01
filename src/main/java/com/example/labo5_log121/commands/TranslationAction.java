package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.ScrollEvent;

public class TranslationAction extends AbstractAction {

    public TranslationAction(PerspectiveModel perspective) {
        super(perspective);
    }

    @Override
    public void actionPerformed(Event event) {
        //perspective.setTranslation(event. , event.);
    }
}
