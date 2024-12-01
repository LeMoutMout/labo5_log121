package com.example.labo5_log121.models;

import com.example.labo5_log121.views.Observer;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        System.out.println("Notifiant les observateurs : " + observers.size() + " observateur(s).");
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

}
