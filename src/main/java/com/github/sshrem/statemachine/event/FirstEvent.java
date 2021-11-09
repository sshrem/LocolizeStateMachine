package com.github.sshrem.statemachine.event;

import com.github.sshrem.statemachine.common.Event;

public class FirstEvent extends Event<EventData> {

    public FirstEvent() {
        this(new EventData());
    }

    public FirstEvent(EventData eventData) {
        super("FirstEvent", 1, eventData);
    }

}
