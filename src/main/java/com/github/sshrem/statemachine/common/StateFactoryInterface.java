package com.github.sshrem.statemachine.common;

import java.io.IOException;

public interface StateFactoryInterface<T,S> {
    State<T,S> createInitState();
    State<T,S> create(int stateId, T data) throws UnknownStateException;

    State<T,S> loadFromFile() throws IOException;

    void saveToFile(State<T,S> state) throws IOException;
}
