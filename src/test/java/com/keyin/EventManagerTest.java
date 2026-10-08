package com.keyin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventManagerTest {
    @Test
    void createsEvent() {
        EventManager manager = new EventManager();

        Event concert = manager.createEvent("Concert", 10);

        assertEquals("Concert", concert.getTitle());
        assertEquals(1, manager.getEventCount());
    }

    @Test
    void rejectsDuplicateEventTitle() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);

        assertThrows(IllegalArgumentException.class,
                () -> manager.createEvent("Concert", 20));

        assertEquals(1, manager.getEventCount());
        assertEquals(10, manager.getEvent("Concert").getCapacity());
    }

    @Test
    void returnsCreatedEventByTitle() {
        EventManager manager = new EventManager();
        Event concert = manager.createEvent("Concert", 10);

        assertSame(concert, manager.getEvent("Concert"));
    }

    @Test
    void rejectsLookupForMissingEvent() {
        EventManager manager = new EventManager();

        assertThrows(IllegalArgumentException.class,
                () -> manager.getEvent("Missing"));
    }

    @Test
    void cancelsExistingEvent() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);

        manager.cancelEvent("Concert");

        assertEquals(0, manager.getEventCount());
    }

    @Test
    void rejectsCancellationOfMissingEvent() {
        EventManager manager = new EventManager();

        assertThrows(IllegalArgumentException.class,
                () -> manager.cancelEvent("Missing"));
    }

    @Test
    void preventsExternalModificationOfEvents() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);

        assertThrows(UnsupportedOperationException.class,
                () -> manager.getEvents().clear());
        assertEquals(1, manager.getEventCount());
    }

    @Test
    void registersAttendeeForEvent() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        manager.registerAttendee("Concert", alex);

        assertTrue(manager.getEvent("Concert").isRegistered(alex));
    }

    @Test
    void cancelsAttendeeRegistration() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");
        manager.registerAttendee("Concert", alex);

        manager.cancelAttendee("Concert", alex);

        assertFalse(manager.getEvent("Concert").isRegistered(alex));
    }

    @Test
    void rejectsRegistrationForMissingEvent() {
        EventManager manager = new EventManager();
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        assertThrows(IllegalArgumentException.class,
                () -> manager.registerAttendee("Missing", alex));
    }
}
