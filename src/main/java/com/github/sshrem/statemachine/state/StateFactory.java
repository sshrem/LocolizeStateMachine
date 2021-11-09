package com.github.sshrem.statemachine.state;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sshrem.statemachine.common.State;
import com.github.sshrem.statemachine.common.StateFactoryBase;
import com.github.sshrem.statemachine.common.UnknownStateException;
import com.github.sshrem.statemachine.event.EventData;


public class StateFactory extends StateFactoryBase<StateData, EventData> {


    public StateFactory() {
        super();
    }

    public StateFactory(String path, ObjectMapper objectMapper) {
        super(path, objectMapper);
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
    public  Class getConcreteClass(int stateId) throws UnknownStateException {
        return switch (stateId) {
            case 0 -> InitState.class;
            case 1 -> FirstState.class;
            case 2 -> SecondState.class;
            default -> throw new UnknownStateException(String.format("StateId: %d is unknown", stateId));
        };
    }
}
