package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.*;
import com.github.sshrem.statemachine.event.EventData;

public class InitState extends State<StateData, EventData> {

    public InitState() {
        this(new StateData());
    }

    public InitState(StateData data) {
        super("InitState", 0, data);
    }

    @Override
    public State<StateData, EventData> handleEvent(Event<EventData> event, StateFactoryBase<StateData, EventData> stateFactory) throws UnknownEventException, UnknownStateException {
        return switch (event.getId()) {
            case 1 -> stateFactory.create(1, new StateData(event.getId(), 1));
            case 2 -> stateFactory.create(2, new StateData(event.getId(), 1));
            default -> throw new UnknownEventException(this.getName(), this.getId(), event.getName(), event.getId());
        };
    }
}
