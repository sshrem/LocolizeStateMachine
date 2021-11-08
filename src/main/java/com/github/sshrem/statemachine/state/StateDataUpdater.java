package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.Event;
import com.github.sshrem.statemachine.common.State;
import com.github.sshrem.statemachine.common.StateData;
import com.github.sshrem.statemachine.common.StateDataUpdaterInterface;

public class StateDataUpdater implements StateDataUpdaterInterface{

    @Override
    public StateData update(State oldState, State newState, Event event) {
        StateData newData = newState.getData();
        StateData oldData = oldState.getData();

        if (oldData.getEventId() == newData.getEventId()){
            newData.setEventCount(oldData.getEventCount());
        }

        if (newData.getEventCount() == 3) {
            System.out.println("Event %s(ID: %d) has been fired 3 times in a row");
        }

        return newData;
    }
}
