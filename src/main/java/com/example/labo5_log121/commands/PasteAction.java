package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;

public class PasteAction extends AbstractAction {

    public PasteAction(PerspectiveModel perspective) {
        super(perspective);
    }

    @Override
    public void actionPerformed(Event event) {
        CopyAction copyAction = CopyAction.getInstance(perspective);

        double translationX = copyAction.getTranslationX();
        double translationY = copyAction.getTranslationY();
        double scaleFactor = copyAction.getScaleFactor();

        perspective.setTranslation(translationX, translationY);
        perspective.setScaleFactor(scaleFactor);
    }
}
