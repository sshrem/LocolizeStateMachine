package com.github.sshrem.statemachine.common;

public class UnknownStateException extends Exception {
    public UnknownStateException() {
        super();
    }

    public UnknownStateException(String message) {
        super(message);
    }

    public UnknownStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownStateException(Throwable cause) {
        super(cause);
    }
}
