package com.github.sshrem.statemachine.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class StateFactoryBase<T, S> {
    private static final String DEFAULT_PATH = "./persistent/state.json";
    private final String path;
    private final ObjectMapper objectMapper;

    public StateFactoryBase() {
        this(DEFAULT_PATH, new ObjectMapper());
    }

    public StateFactoryBase(String path, ObjectMapper objectMapper) {
        this.path = path;
        this.objectMapper = objectMapper;
    }

    abstract public State<T, S> createInitState();

    abstract public State<T, S> create(int stateId, T data) throws UnknownStateException;

    abstract public <K extends State<T, S>> Class<K> getConcreteClass(int stateId) throws UnknownStateException;


    public State<T, S> loadFromFile() throws IOException, UnknownStateException {
        JsonNode jsonNode = objectMapper.readTree(new File(path));
        int stateId = jsonNode.get("id").asInt();
        return objectMapper.treeToValue(jsonNode, getConcreteClass(stateId));
    }

    public void saveToFile(State<T, S> state) throws IOException {
        Path p = Path.of(path);
        if (Files.notExists(p.getParent())) {
            Files.createDirectory(p.getParent());
        }

        objectMapper.writeValue(new File(path), state);
    }

}
