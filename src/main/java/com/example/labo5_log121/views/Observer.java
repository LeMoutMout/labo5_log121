package com.example.labo5_log121.views;

import com.example.labo5_log121.models.Subject;

public interface Observer {
    void update(Subject subject, String message);
}
