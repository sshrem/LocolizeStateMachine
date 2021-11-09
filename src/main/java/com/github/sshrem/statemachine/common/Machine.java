package com.github.sshrem.statemachine.common;

import java.io.IOException;

public class Machine<T, S> {
    private State<T, S> state;
    private final StateFactoryBase<T, S> stateFactory;
    private final StateDataUpdaterInterface<T, S> stateDataUpdater;

    public Machine(StateFactoryBase<T, S> stateFactory, StateDataUpdaterInterface<T, S> stateDataUpdater) {
        this.stateFactory = stateFactory;
        this.stateDataUpdater = stateDataUpdater;
    }

    public void init() throws IOException, UnknownStateException {
        init(false);
    }

    public void init(boolean shouldLoadState) throws IOException, UnknownStateException {
        if (shouldLoadState) {
            this.state = stateFactory.loadFromFile();
        } else {
            this.state = stateFactory.createInitState();
        }
    }

    public State<T, S> processEvent(Event<S> event) throws UnknownEventException, UnknownStateException {
        state = state.processEvent(event, stateDataUpdater, stateFactory);
        return state;
    }

    public void persist() throws IOException {
        stateFactory.saveToFile(state);
    }

    public State<T, S> getState() {
        return state;
    }
}
