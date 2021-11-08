package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.*;
import com.github.sshrem.statemachine.event.EventData;

public class SecondState extends State<StateData, EventData> {

    public SecondState() {
        this(new StateData());
    }

    public SecondState(StateData stateData){
        super("SecondState", 2, stateData);
    }

    @Override
    public State<StateData, EventData> handleEvent(Event<EventData> event, StateFactoryInterface<StateData, EventData> stateFactory) throws UnknownEventException, UnknownStateException {
        return switch (event.getId()) {
            case 1 -> stateFactory.create(1, new StateData(event.getId(), 1));
            case 2 -> this;
            default -> throw new UnknownEventException(this.getName(), this.getId(), event.getName(), event.getId());
        };
    }
}
