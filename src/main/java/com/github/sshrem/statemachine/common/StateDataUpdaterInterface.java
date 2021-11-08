package com.github.sshrem.statemachine.common;

public interface StateDataUpdaterInterface {
    StateData update(State oldState, State newState, Event event);
}
