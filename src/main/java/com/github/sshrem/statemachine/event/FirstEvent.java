package com.github.sshrem.statemachine.event;

import com.github.sshrem.statemachine.common.Event;
import com.github.sshrem.statemachine.common.EventData;

public class FirstEvent extends Event {

    public FirstEvent() {
        super("FirstEvent", 1, new EventData());
    }

    public FirstEvent(EventData eventData) {
        super("FirstEvent", 1, eventData);
    }
    
}
