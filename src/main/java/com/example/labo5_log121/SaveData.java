package com.example.labo5_log121;

import com.example.labo5_log121.commands.Memento;
import com.example.labo5_log121.models.PerspectiveModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SaveData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<PerspectiveModel> perspectiveModels;
    private final String thumbnailPath;
    private final Map<PerspectiveModel, Stack<Memento>> undoStacks;

    public SaveData(List<PerspectiveModel> perspectiveModels, String thumbnailPath,
                    Map<PerspectiveModel, Stack<Memento>> undoStacks) {
        this.perspectiveModels = perspectiveModels;
        this.thumbnailPath = thumbnailPath;
        this.undoStacks = undoStacks;
    }

    public List<PerspectiveModel> getPerspectiveModels() {
        return perspectiveModels;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public Map<PerspectiveModel, Stack<Memento>> getUndoStacks() {
        return undoStacks;
    }

}
