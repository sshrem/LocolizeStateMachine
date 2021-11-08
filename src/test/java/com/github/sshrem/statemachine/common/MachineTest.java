package com.github.sshrem.statemachine.common;

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
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MachineTest {
    private Machine<StateData, EventData> machine;

    final private String utf8 = StandardCharsets.UTF_8.name();
    private ByteArrayOutputStream baos;
    private PrintStream ps;

    @BeforeEach
    public void beforeEach() throws IOException {
        StateFactoryInterface<StateData, EventData> stateFactory = new StateFactory();

        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos, true, utf8);
        StateDataUpdaterInterface<StateData, EventData> stateDataUpdater = new StateDataUpdater(ps);

        machine = new Machine<>(stateFactory, stateDataUpdater);
        machine.init();
    }

    @AfterEach
    public void afterEach(){
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
        State<StateData, EventData>  state = machine.processEvent(event2);
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
    public void testLoadState() {
        assertTrue(true);
    }

    @Test
    public void testSaveState() {
        assertTrue(true);
    }

}