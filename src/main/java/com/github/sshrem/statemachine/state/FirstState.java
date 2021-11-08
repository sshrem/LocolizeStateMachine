package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.Event;
import com.github.sshrem.statemachine.common.State;
import com.github.sshrem.statemachine.common.StateData;
import com.github.sshrem.statemachine.common.UnknownEventException;

public class FirstState extends State {
    public FirstState(){
        super("FirstState", 1, new StateData(), new StateDataUpdater());
    }

    public FirstState(Event event){
        super("FirstState", 1, new StateData(event), new StateDataUpdater());
    }


    @Override
    public State handleEvent(Event event) throws UnknownEventException {
        switch(event.getId()){
            case 1:
                return this;
            case 2:
                return new SecondState(event);
            default:
                throw new UnknownEventException(this, event);
        }
    }
}
