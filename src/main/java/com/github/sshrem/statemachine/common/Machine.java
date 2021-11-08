package com.github.sshrem.statemachine.common;

import com.github.sshrem.statemachine.state.InitState;

public class Machine {

    private String defultPath = "./";
    private State state;

    public Machine(){
        this(false, new InitState());
    }

    public Machine(State state){
        this(false, state);
    }

    public Machine(boolean shouldLoadState){
        this(shouldLoadState, new InitState());
    }

    public Machine(boolean shouldLoadState, State state){
        if (shouldLoadState){
            this.state = loadState(defultPath);
        } else {
            this.state = state;
        }
    }



    public State loadState(String path){
        return null;
    }

    public void saveState(String path){
        
    }

    public void handleEvent(Event event) throws UnknownEventException{
        state = state.handleEvent(event);
        saveState(defultPath);
    }
}
