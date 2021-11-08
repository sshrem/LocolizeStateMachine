package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.*;
import com.github.sshrem.statemachine.event.EventData;

public class FirstState extends State<StateData, EventData> {

    public FirstState(){
        this(new StateData());
    }
    public FirstState(StateData stateData){
        super("FirstState", 1, stateData);
    }

    @Override
    public State<StateData, EventData> handleEvent(Event<EventData> event, StateFactoryInterface<StateData, EventData> stateFactory) throws UnknownEventException, UnknownStateException {
        return switch (event.getId()) {
            case 1 -> this;
            case 2 -> stateFactory.create(2, new StateData(event.getId(), 1));
            default -> throw new UnknownEventException(this.getName(), this.getId(), event.getName(), event.getId());
        };
    }
}
