package com.github.sshrem.statemachine.common;

public abstract class State {

    private String name;
    private int id;
    private StateData data;
    private StateDataUpdaterInterface stateDataUpdater;
    
    public State(String name, int id, StateData data, StateDataUpdaterInterface stateDataUpdater) {
        this.name = name;
        this.id = id;
        this.data = data;
        this.stateDataUpdater = stateDataUpdater;
    }

    protected abstract State handleEvent(Event event) throws UnknownEventException;

    public State proccessEvent(Event event) throws UnknownEventException {
        State newState = handleEvent(event);
        stateDataUpdater.update(this, newState, event);

        return newState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StateData getData() {
        return data;
    }

    public void setData(StateData data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
