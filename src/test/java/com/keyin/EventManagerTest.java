package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventManagerTest {
    @Test
    void createsEvent() {
        EventManager manager = new EventManager();

        Event concert = manager.createEvent("Concert", 10);

        Assertions.assertEquals("Concert", concert.getTitle());
        Assertions.assertEquals(1, manager.getEventCount());
    }

    @Test
    void rejectsDuplicateEventTitle() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.createEvent("Concert", 20));

        Assertions.assertEquals(1, manager.getEventCount());
        Assertions.assertEquals(10, manager.getEvent("Concert").getCapacity());
    }

    @Test
    void rejectsLookupForMissingEvent() {
        EventManager manager = new EventManager();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.getEvent("Missing"));
    }

    @Test
    void cancelsExistingEvent() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);

        manager.cancelEvent("Concert");

        Assertions.assertEquals(0, manager.getEventCount());
    }

    @Test
    void rejectsCancellationOfMissingEvent() {
        EventManager manager = new EventManager();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.cancelEvent("Missing"));
    }

    @Test
    void preventsExternalModificationOfEvents() {
        EventManager manager = new EventManager();
        manager.createEvent("Concert", 10);

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> manager.getEvents().clear());
        Assertions.assertEquals(1, manager.getEventCount());
    }

    @Test
    void rejectsRegistrationForMissingEvent() {
        EventManager manager = new EventManager();
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.registerAttendee("Missing", alex));
    }

    @Test
    void rejectsAttendeeCancellationForMissingEvent() {
        EventManager manager = new EventManager();
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> manager.cancelAttendee("Missing", alex));
    }
}
