package com.github.sshrem.statemachine.common;

public interface StateDataUpdaterInterface {
    public StateData update(State oldState, State newState, Event event);
}
