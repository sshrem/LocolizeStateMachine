package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.Event;
import com.github.sshrem.statemachine.common.State;
import com.github.sshrem.statemachine.common.StateData;
import com.github.sshrem.statemachine.common.UnknownEventException;

public class SecondState extends State {

    public SecondState() {
        super("SecondState", 2, new StateData(), new StateDataUpdater());
    }

    public SecondState(Event event){
        super("SecondState", 2, new StateData(event), new StateDataUpdater());
    }

    @Override
    public State handleEvent(Event event) throws UnknownEventException {
        switch(event.getId()){
            case 1:
                return new FirstState(event);
            case 2:
                return this;
            default:
                throw new UnknownEventException(this, event);
        }
    }
}
