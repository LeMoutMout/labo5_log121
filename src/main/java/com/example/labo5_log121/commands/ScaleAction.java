package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.models.Subject;
import javafx.event.ActionEvent;

public class ScaleAction extends AbstractAction {

    public ScaleAction(PerspectiveModel perspective) {
        super(perspective);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //perspective.setScaleFactor(event.get);
    }
}
