package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.event.EventData;
import com.github.sshrem.statemachine.common.State;
import com.github.sshrem.statemachine.common.StateFactoryInterface;
import com.github.sshrem.statemachine.common.UnknownStateException;

import java.io.IOException;

public class StateFactory implements StateFactoryInterface<StateData, EventData> {
    private static final String DEFAULT_PATH = "./";
    private final String path;

    public StateFactory(){
        this(DEFAULT_PATH);
    }

    public StateFactory(String path){
        this.path = path;
    }

    @Override
    public State<StateData, EventData> createInitState() {
        return new InitState();
    }

    @Override
    public State<StateData, EventData> create(int stateId, StateData data) throws UnknownStateException {
        return switch (stateId) {
            case 1 -> new FirstState(data);
            case 2 -> new SecondState(data);
            default -> throw new UnknownStateException(String.format("StateId: %d is unknown", stateId));
        };
    }

    @Override
    public State<StateData, EventData> loadFromFile() throws IOException{
        return null;
    }

    @Override
    public void saveToFile(State<StateData, EventData> state) throws IOException {
    }
}
