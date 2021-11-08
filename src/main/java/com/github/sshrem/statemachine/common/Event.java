package com.github.sshrem.statemachine.common;

public abstract class Event {
    private String name;
    private int id;
    private EventData data;

    public Event(String name, int id, EventData data) {
        this.name = name;
        this.id = id;
        this.data = data;
    }
  
    public EventData getData() {
        return data;
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
    public void setData(EventData data) {
        this.data = data;
    }
    
}
