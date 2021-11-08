package com.github.sshrem.statemachine.state;

import com.github.sshrem.statemachine.common.Event;

public class StateData {
    private int eventId;
    private int eventCount;

    public StateData(){
        this(0, 0);
    }
   
    public StateData(int eventId, int eventCount) {
        this.eventCount = eventCount;
        this.eventId = eventId;
    }

    public int getEventCount() {
        return eventCount;
    }
    
    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
