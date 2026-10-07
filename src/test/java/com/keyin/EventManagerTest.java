package com.keyin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EventManagerTest {

    @Test
    public void returnEventCount() {
        eventManager eventManager = new eventManager();
        eventManager.createEvent("Event 1", 10);
        eventManager.createEvent("Event 2", 20);
        assert eventManager.getEventCount() == 2;
    }

    @Test
    public void sameNameThrowsException() {
        eventManager eventManager = new eventManager();
        eventManager.createEvent("Same Event", 15);
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> eventManager.createEvent("Same Event", 15)
        );
        Assertions.assertEquals("Event already exists: Same Event", exception.getMessage());
    }


}
