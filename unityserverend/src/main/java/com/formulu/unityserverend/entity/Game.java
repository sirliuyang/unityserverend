package com.formulu.unityserverend.entity;

import java.util.List;

public class Game {
    private int id;
    private String name;
    private List<StringBuilder> steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StringBuilder> getSteps() {
        return steps;
    }

    public void setSteps(List<StringBuilder> steps) {
        this.steps = steps;
    }


}
