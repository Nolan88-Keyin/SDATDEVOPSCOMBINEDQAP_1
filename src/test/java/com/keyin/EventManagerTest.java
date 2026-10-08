package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventManagerTest {

    @Test
    public void testCreateEvent() {
        eventManager manager = new eventManager();

        event concert = manager.createEvent("Concert", 10);

        Assertions.assertEquals("Concert", concert.getTitle());
        Assertions.assertEquals(1, manager.getEventCount());
    }

    @Test
    public void testCreateDuplicateEventThrowsException() {
        eventManager manager = new eventManager();
        manager.createEvent("Concert", 10);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.createEvent("Concert", 20));

        Assertions.assertEquals(1, manager.getEventCount());
        Assertions.assertEquals(10, manager.getEvent("Concert").getCapacity());
    }

    @Test
    public void testGetEvent() {
        eventManager manager = new eventManager();
        event concert = manager.createEvent("Concert", 10);

        Assertions.assertSame(concert, manager.getEvent("Concert"));
    }

    @Test
    public void testGetMissingEventThrowsException() {
        eventManager manager = new eventManager();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.getEvent("Missing"));
    }

    @Test
    public void testCancelEvent() {
        eventManager manager = new eventManager();
        manager.createEvent("Concert", 10);

        manager.cancelEvent("Concert");

        Assertions.assertEquals(0, manager.getEventCount());
    }

    @Test
    public void testCancelMissingEventThrowsException() {
        eventManager manager = new eventManager();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.cancelEvent("Missing"));
    }

    @Test
    public void testRegisterAttendee() {
        eventManager manager = new eventManager();
        manager.createEvent("Concert", 10);
        attendee alex = new attendee("Alex Smith", "alex@example.com");

        manager.registerAttendee("Concert", alex);

        Assertions.assertTrue(manager.getEvent("Concert").isRegistered(alex));
    }

    @Test
    public void testCancelAttendee() {
        eventManager manager = new eventManager();
        manager.createEvent("Concert", 10);
        attendee alex = new attendee("Alex Smith", "alex@example.com");
        manager.registerAttendee("Concert", alex);

        manager.cancelAttendee("Concert", alex);

        Assertions.assertFalse(manager.getEvent("Concert").isRegistered(alex));
    }

    @Test
    public void testRegisterAttendeeForMissingEventThrowsException() {
        eventManager manager = new eventManager();
        attendee alex = new attendee("Alex Smith", "alex@example.com");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.registerAttendee("Missing", alex));
    }
}
