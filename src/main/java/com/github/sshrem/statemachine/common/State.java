package com.github.sshrem.statemachine.common;

public abstract class State<T, S> {

    private String name;
    private int id;
    private T data;

    public State(String name, int id, T data) {
        this.name = name;
        this.id = id;
        this.data = data;
    }

    protected abstract State<T, S> handleEvent(Event<S> event, StateFactoryBase<T, S> stateFactory) throws UnknownEventException, UnknownStateException;

    public State<T, S> processEvent(Event<S> event, StateDataUpdaterInterface<T, S> stateDataUpdater, StateFactoryBase<T, S> stateFactory) throws UnknownEventException, UnknownStateException {
        State<T, S> newState = handleEvent(event, stateFactory);
        stateDataUpdater.update(this, newState, event);

        return newState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
