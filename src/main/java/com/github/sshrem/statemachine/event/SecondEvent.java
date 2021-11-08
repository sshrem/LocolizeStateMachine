package com.github.sshrem.statemachine.event;

import com.github.sshrem.statemachine.common.Event;
import com.github.sshrem.statemachine.common.EventData;

public class SecondEvent extends Event {

    public SecondEvent() {
        this(new EventData());
    }

    public SecondEvent(EventData eventData) {
        super("SecondEvent", 2, eventData);
    }

}
