package com.example.labo5_log121.models;

import com.example.labo5_log121.SaveData;
import com.example.labo5_log121.commands.CommandManager;
import com.example.labo5_log121.commands.Memento;
import com.example.labo5_log121.controllers.PerspectiveController;
import com.example.labo5_log121.views.PerspectiveView;
import com.example.labo5_log121.views.ThumbnailView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class MainModel extends Subject implements Serializable{

    private int perspectiveNumber;
    private String lastLoadedImagePath;
    private ImageModel lastLoadedImageModel;
    private ThumbnailView thumbnailView;
    private PerspectiveView lastPerspectiveView;
    private final List<String> uniqueIds;

    public MainModel() {
        this.uniqueIds = new ArrayList<>();
    }

    // Méthode pour ouvrir une image et l'afficher dans un onglet Thumbnail
    public void openImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            notifyObservers("clearTab");
            notifyObservers("enabledMenuItem");
            uniqueIds.clear();
            perspectiveNumber = 1;
            lastLoadedImagePath = file.getAbsolutePath();
            addThumbnailTab(lastLoadedImagePath);
            ImageModel imageModel = new ImageModel(lastLoadedImagePath);
            lastLoadedImageModel = imageModel;
        }
    }

    // Méthode pour ajouter un onglet avec une image statique (Thumbnail)
    private void addThumbnailTab(String imagePath) {
        thumbnailView = new ThumbnailView(imagePath);
        notifyObservers("addThumbnailTab");
    }

    // Méthode pour ouvrir l'état
    public void openState() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open State");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized Files", "*.ser"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                SaveData saveData = (SaveData) ois.readObject();

                restoreThumbnail(saveData.getThumbnailPath());
                CommandManager.getInstance().restoreUndoStacks(saveData.getUndoStacks());
                restoreAllPerspectives(saveData.getUniqueIds());

                notifyObservers("enabledMenuItem");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void restoreAllPerspectives(List<String> uniqueIds) {
        perspectiveNumber = 1;
        this.uniqueIds.clear();

        for (String uniqueId : uniqueIds) {
            PerspectiveView view = new PerspectiveView(lastLoadedImagePath);
            this.lastPerspectiveView = view;
            PerspectiveModel model = new PerspectiveModel(lastLoadedImageModel);
            model.setUniqueId(uniqueId);
            this.uniqueIds.add(model.getUniqueId());
            new PerspectiveController(view, model);
            notifyObservers("addPerspectiveTab");
            Memento lastMemento = CommandManager.getInstance().getUndoStacks().get(uniqueId).getLast();
            if(lastMemento != null) {
                model.restore(lastMemento);
                model.setUndoButtonDisabled(false);
            }
        }
    }


    private void restoreThumbnail(String thumbnailPath) {
        this.thumbnailView = new ThumbnailView(thumbnailPath);
        this.lastLoadedImagePath = thumbnailPath;
        ImageModel imageModel = new ImageModel(thumbnailPath);
        this.lastLoadedImageModel = imageModel;
        notifyObservers("clearTab");
        notifyObservers("addThumbnailTab");
    }

    // Méthode pour sauvegarder l'état
    public void saveState() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save State");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized Files", "*.ser"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                Map<String, Stack<Memento>> undoStacks = CommandManager.getInstance().getUndoStacks();
                SaveData saveData = new SaveData(uniqueIds, lastLoadedImagePath, undoStacks);
                oos.writeObject(saveData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeTab() {
        perspectiveNumber = 1;
        notifyObservers("clearTab");
        notifyObservers("disabledMenuItem");
    }

    public void exit(){
        System.exit(0);
    }

    // Méthode pour créer une nouvelle perspective et l'ajouter dans l'onglet
    public void addPerspectiveTab() {
        if (!lastLoadedImagePath.isEmpty() && lastLoadedImageModel != null) {
            PerspectiveView perspectiveView = new PerspectiveView(lastLoadedImagePath);
            this.lastPerspectiveView = perspectiveView;
            PerspectiveModel perspectiveModel = new PerspectiveModel(lastLoadedImageModel);
            this.uniqueIds.add(perspectiveModel.getUniqueId());
            new PerspectiveController(perspectiveView, perspectiveModel);
            notifyObservers("addPerspectiveTab");
        }
    }

    public String getLastLoadedImagePath() {
        File file = new File(lastLoadedImagePath);
        return file.getName();
    }
    public ThumbnailView getThumbnailView() {
        return thumbnailView;
    }
    public PerspectiveView getLastPerspectiveView() {
        return lastPerspectiveView;
    }
    public int getPerspectiveNumber() {
        return perspectiveNumber;
    }

    public void setPerspectiveNumber(int i) {
        perspectiveNumber = i;
    }
}
