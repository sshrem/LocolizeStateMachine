package com.github.sshrem.statemachine.common;

public abstract class Event<S> {
    private String name;
    private int id;
    private S data;

    public Event(String name, int id, S data) {
        this.name = name;
        this.id = id;
        this.data = data;
    }

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

    public S getData() {
        return data;
    }

    public void setData(S data) {
        this.data = data;
    }

}
