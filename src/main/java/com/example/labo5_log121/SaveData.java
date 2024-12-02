package com.example.labo5_log121;

import com.example.labo5_log121.commands.Memento;
import com.example.labo5_log121.models.PerspectiveModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PerspectiveModel> perspectiveModels;
    private List<String> thumbnailPaths;
    private Map<PerspectiveModel, Stack<Memento>> undoStacks;

    public SaveData(List<PerspectiveModel> perspectiveModels, List<String> thumbnailPaths,
                    Map<PerspectiveModel, Stack<Memento>> undoStacks) {
        this.perspectiveModels = perspectiveModels;
        this.thumbnailPaths = thumbnailPaths;
        this.undoStacks = undoStacks;
    }

    public List<PerspectiveModel> getPerspectiveModels() {
        return perspectiveModels;
    }

    public List<String> getThumbnailPaths() {
        return thumbnailPaths;
    }

    public Map<PerspectiveModel, Stack<Memento>> getUndoStacks() {
        return undoStacks;
    }

}
