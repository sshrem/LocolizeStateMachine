package com.github.sshrem.statemachine.common;

import java.io.IOException;

public class Machine<T,S> {
    private State<T,S> state;
    private StateFactoryInterface<T,S> stateFactory;
    private StateDataUpdaterInterface<T,S> stateDataUpdater;

    public Machine(StateFactoryInterface<T,S> stateFactory, StateDataUpdaterInterface<T,S> stateDataUpdater) {
        this.stateFactory = stateFactory;
        this.stateDataUpdater = stateDataUpdater;
    }

    public void init() throws IOException{
        init(false);
    }

    public void init(boolean shouldLoadState) throws IOException{
        if (shouldLoadState){
            this.state = stateFactory.loadFromFile();
        } else {
            this.state = stateFactory.createInitState();
        }
    }

    public State<T,S> processEvent(Event<S> event) throws UnknownEventException, IOException, UnknownStateException {
        state = state.processEvent(event, stateDataUpdater, stateFactory);
        stateFactory.saveToFile(state);
        return state;
    }

    public State<T,S> getState(){
        return state;
    }
}
