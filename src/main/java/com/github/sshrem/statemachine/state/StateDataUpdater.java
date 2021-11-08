package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.*;
import com.github.sshrem.statemachine.event.EventData;

import java.io.OutputStream;
import java.io.PrintStream;

public class StateDataUpdater implements StateDataUpdaterInterface<StateData, EventData> {

    private PrintStream printStream = System.out;
    public StateDataUpdater() {
    }

    public StateDataUpdater(PrintStream  printStream){
        this.printStream = printStream;
    }

    @Override
    public StateData update(State<StateData, EventData> oldState, State<StateData, EventData> newState, Event<EventData> event) {
        StateData newData = newState.getData();
        StateData oldData = oldState.getData();

        if (oldData.getEventId() == newData.getEventId()){
            newData.setEventCount(oldData.getEventCount() + 1);
        } else {
            newData.setEventId(event.getId());
            newData.setEventCount(1);
        }

        if (newData.getEventCount() == 3) {
            printStream.printf("Event %s(ID: %d) has been fired 3 times in a row%n", event.getName(), event.getId());
        }

        return newData;
    }
}
