package com.github.sshrem.statemachine.common;

import com.github.sshrem.statemachine.state.StateData;

public interface StateDataUpdaterInterface<T,S> {
    StateData update(State<T,S> oldState, State<T,S> newState, Event<S> event);
}
