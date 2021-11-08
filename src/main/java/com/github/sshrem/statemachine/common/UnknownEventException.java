package com.github.sshrem.statemachine.common;

public class UnknownEventException extends Exception {

    public UnknownEventException() {
        super();
    }

    public UnknownEventException(String message) {
        super(message);
    }

    public UnknownEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownEventException(Throwable cause) {
        super(cause);
    }

    public UnknownEventException(String stateName, int stateId, String eventName, int eventId){
        super(String.format("UnknownEventException State %s (ID: %d) doesn't know how to handle event %s (ID: %d)", stateName, stateId, eventName, eventId));
    }
}
