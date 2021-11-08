package com.github.sshrem.statemachine.state;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sshrem.statemachine.event.EventData;
import com.github.sshrem.statemachine.common.State;
import com.github.sshrem.statemachine.common.StateFactoryInterface;
import com.github.sshrem.statemachine.common.UnknownStateException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StateFactory implements StateFactoryInterface<StateData, EventData> {
    private static final String DEFAULT_PATH = "./persistent/state.json";
    private final String path;
    private ObjectMapper objectMapper;

    public StateFactory(){
        this(DEFAULT_PATH, new ObjectMapper());
    }

    public StateFactory(String path, ObjectMapper objectMapper){
        this.path = path;
        this.objectMapper = objectMapper;
    }

    @Override
    public State<StateData, EventData> createInitState() {
        return new InitState();
    }

    @Override
    public State<StateData, EventData> create(int stateId, StateData data) throws UnknownStateException {
        return switch (stateId) {
            case 1 -> new FirstState(data);
            case 2 -> new SecondState(data);
            default -> throw new UnknownStateException(String.format("StateId: %d is unknown", stateId));
        };
    }

    @Override
    public State<StateData, EventData> loadFromFile() throws IOException, UnknownStateException {
        JsonNode jsonNode = objectMapper.readTree(new File(path));
        int stateId = jsonNode.get("id").asInt();
        return switch (stateId) {
            case 0 -> objectMapper.treeToValue(jsonNode, InitState.class);
            case 1 -> objectMapper.treeToValue(jsonNode, FirstState.class);
            case 2 -> objectMapper.treeToValue(jsonNode, SecondState.class);
            default -> throw new UnknownStateException(String.format("StateId: %d is unknown", stateId));
        };
    }

    @Override
    public void saveToFile(State<StateData, EventData> state) throws IOException {
        Path p = Path.of(path);

        if (Files.notExists(p.getParent())) {
            Files.createDirectory(p.getParent());
        }

        objectMapper.writeValue(new File(path), state);
    }
}
