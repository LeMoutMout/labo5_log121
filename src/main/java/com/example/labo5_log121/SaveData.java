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

    private final List<String> uniqueIds;
    private final String thumbnailPath;
    private final Map<String, Stack<Memento>> undoStacks;

    public SaveData(List<String> uniqueIds, String thumbnailPath,
                    Map<String, Stack<Memento>> undoStacks) {
        this.uniqueIds = uniqueIds;
        this.thumbnailPath = thumbnailPath;
        this.undoStacks = undoStacks;
    }

    public List<String> getUniqueIds() {
        return uniqueIds;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public Map<String, Stack<Memento>> getUndoStacks() {
        return undoStacks;
    }

}
