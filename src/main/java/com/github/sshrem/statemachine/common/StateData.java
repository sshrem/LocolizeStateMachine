package com.github.sshrem.statemachine.common;

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

    public StateData(Event event){
        this(event.getId(), 0);
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
