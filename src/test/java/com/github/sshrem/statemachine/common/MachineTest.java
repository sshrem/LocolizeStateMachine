package com.github.sshrem.statemachine.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sshrem.statemachine.event.EventData;
import com.github.sshrem.statemachine.event.FirstEvent;
import com.github.sshrem.statemachine.event.SecondEvent;
import com.github.sshrem.statemachine.state.StateData;
import com.github.sshrem.statemachine.state.StateDataUpdater;
import com.github.sshrem.statemachine.state.StateFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MachineTest {
    final private String utf8 = StandardCharsets.UTF_8.name();
    private Machine<StateData, EventData> machine;
    private ByteArrayOutputStream baos;
    private PrintStream ps;
    private StateFactoryBase<StateData, EventData> stateFactory;
    private StateDataUpdaterInterface<StateData, EventData> stateDataUpdater;

    @BeforeEach
    public void beforeEach() throws IOException, UnknownStateException {
        stateFactory = new StateFactory();

        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos, true, utf8);
        stateDataUpdater = new StateDataUpdater(ps);

        machine = new Machine<>(stateFactory, stateDataUpdater);
        machine.init();
    }

    @AfterEach
    public void afterEach() {
        ps.close();
    }

    @Test
    public void testInit() throws UnsupportedEncodingException {
        State<StateData, EventData> state = machine.getState();
        StateData data = state.getData();
        assertEquals(0, state.getId());
        assertEquals(0, data.getEventId());
        assertEquals(0, data.getEventCount());
        assertEquals("", baos.toString(utf8));
    }

    @Test
    public void testHandleEventBasic() throws UnknownStateException, UnknownEventException, IOException {
        FirstEvent event1 = new FirstEvent();
        State<StateData, EventData> state = machine.processEvent(event1);
        StateData data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        SecondEvent event2 = new SecondEvent();
        state = machine.processEvent(event2);
        data = state.getData();
        assertEquals(2, state.getId());
        assertEquals(2, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));
    }

    @Test
    public void testHandleEvent() throws UnknownStateException, UnknownEventException, IOException {
        FirstEvent event1 = new FirstEvent();
        State<StateData, EventData> state = machine.processEvent(event1);
        StateData data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        SecondEvent event2 = new SecondEvent();
        state = machine.processEvent(event2);
        data = state.getData();
        assertEquals(2, state.getId());
        assertEquals(2, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        state = machine.processEvent(event1);
        data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        event2 = new SecondEvent();
        state = machine.processEvent(event2);
        data = state.getData();
        assertEquals(2, state.getId());
        assertEquals(2, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));
    }

    @Test
    public void testHandleEvent2() throws UnknownStateException, UnknownEventException, IOException {

        SecondEvent event2 = new SecondEvent();
        State<StateData, EventData> state = machine.processEvent(event2);
        StateData data = state.getData();
        assertEquals(2, state.getId());
        assertEquals(2, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        event2 = new SecondEvent();
        state = machine.processEvent(event2);
        data = state.getData();
        assertEquals(2, state.getId());
        assertEquals(2, data.getEventId());
        assertEquals(2, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        FirstEvent event1 = new FirstEvent();
        state = machine.processEvent(event1);
        data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));


        state = machine.processEvent(event1);
        data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(2, data.getEventCount());
        assertEquals("", baos.toString(utf8));

    }

    @Test
    public void testHandleEventConsecutiveEvent() throws UnknownStateException, UnknownEventException, IOException {
        FirstEvent event1 = new FirstEvent();
        State<StateData, EventData> state = machine.processEvent(event1);
        StateData data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(1, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        state = machine.processEvent(event1);
        data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(2, data.getEventCount());
        assertEquals("", baos.toString(utf8));

        state = machine.processEvent(event1);
        data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(3, data.getEventCount());
        assertEquals("Event FirstEvent(ID: 1) has been fired 3 times in a row" + System.lineSeparator(), baos.toString(utf8));
        baos.reset();

        state = machine.processEvent(event1);
        data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(4, data.getEventCount());
        assertEquals("", baos.toString(utf8));
    }

    @Test
    public void testUnknownEventException() {
        Event<EventData> event = new Event<>("UnknownEvent", 3, null) {
        };
        Throwable exception = assertThrows(UnknownEventException.class, () -> machine.processEvent(event));
        assertEquals("UnknownEventException State InitState (ID: 0) doesn't know how to handle event UnknownEvent (ID: 3)", exception.getMessage());
    }

    @Test
    public void testLoadState() throws UnknownStateException, UnknownEventException, IOException {
        StateFactoryBase<StateData, EventData> stateFactory1 = new StateFactory("./persistent/state1.json", new ObjectMapper());
        machine = new Machine<>(stateFactory1, stateDataUpdater);
        machine.init();
        FirstEvent event1 = new FirstEvent();
        machine.processEvent(event1);
        machine.persist();

        Machine<StateData, EventData> newMachine = new Machine<>(stateFactory1, stateDataUpdater);
        newMachine.init(true);

        State<StateData, EventData> state = newMachine.getState();
        StateData data = state.getData();
        assertEquals(1, state.getId());
        assertEquals(1, data.getEventId());
        assertEquals(1, data.getEventCount());
    }

    @Test
    public void testLoadInitState() throws UnknownStateException, IOException {
        StateFactoryBase<StateData, EventData> stateFactory1 = new StateFactory("./persistent/state2.json", new ObjectMapper());
        machine = new Machine<>(stateFactory1, stateDataUpdater);
        machine.init();
        machine.persist();

        Machine<StateData, EventData> newMachine = new Machine<>(stateFactory1, stateDataUpdater);
        newMachine.init(true);

        State<StateData, EventData> state = newMachine.getState();
        StateData data = state.getData();
        assertEquals(0, state.getId());
        assertEquals(0, data.getEventId());
        assertEquals(0, data.getEventCount());
    }
}