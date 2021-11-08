package com.github.sshrem.statemachine.event;

public class EventData {
    private int data;

    public EventData(){
        this(0);
    }

    public EventData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

}
