package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.Event;
import com.github.sshrem.statemachine.common.State;
import com.github.sshrem.statemachine.common.StateData;
import com.github.sshrem.statemachine.common.UnknownEventException;

public class InitState extends State {

    public InitState(){
        this("InitState", 0, new StateData());
    }
    public InitState(String name, int id, StateData data) {
        super(name, id, data, new StateDataUpdater());
    }

    @Override
    public State handleEvent(Event event) throws UnknownEventException {
        switch(event.getId()){
            case 1:
                return new FirstState(event);
            case 2:
                return new SecondState(event);
            default:
                throw new UnknownEventException(this, event);
        }
    }
}
