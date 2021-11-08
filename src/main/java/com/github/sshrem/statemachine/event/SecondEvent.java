package com.github.sshrem.statemachine.event;

import com.github.sshrem.statemachine.common.Event;

public class SecondEvent extends Event<EventData> {

    public SecondEvent() {
        this(new EventData());
    }

    public SecondEvent(EventData eventData) {
        super("SecondEvent", 2, eventData);
    }

}
