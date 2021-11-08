package com.github.sshrem.statemachine.common;

public class UnknownEventException extends Exception {

    public UnknownEventException(){
        super("State doesn't know event exception");
    }

    public UnknownEventException(State state, Event event){
        super(String.format("State %s (ID: %d) doesn't know how to handel event %s (ID: %d)", state.getName(), state.getId(), event.getName(), event.getId()));
    }
}
