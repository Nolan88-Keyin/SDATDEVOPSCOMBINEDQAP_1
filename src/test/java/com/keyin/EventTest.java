package com.keyin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTest {
    private event concert;
    private attendee alex;
    private attendee sam;

    @BeforeEach
    void setUp() {
        concert = new event("Concert", 1);
        alex = new attendee("Alex Smith", "alex@example.com");
        sam = new attendee("Sam Jones", "sam@example.com");
    }

    @Test
    void storesTitleAndCapacity() {
        assertEquals("Concert", concert.getTitle());
        assertEquals(1, concert.getCapacity());
    }

    @Test
    void registersAttendee() {
        concert.registerAttendee(alex);

        assertTrue(concert.isRegistered(alex));
        assertEquals(1, concert.getAttendees().size());
    }

    @Test
    void newEventHasAllSpotsLeft() {
        assertEquals(1, concert.getSpotsLeft());
        assertFalse(concert.isFull());
    }

    @Test
    void eventIsFullAtCapacity() {
        concert.registerAttendee(alex);

        assertTrue(concert.isFull());
        assertEquals(0, concert.getSpotsLeft());
    }

    @Test
    void rejectsRegistrationWhenFull() {
        concert.registerAttendee(alex);
        concert.registerAttendee(sam);

        assertFalse(concert.isRegistered(sam));
        assertEquals(1, concert.getAttendees().size());
    }

    @Test
    void preventsDuplicateRegistration() {
        event workshop = new event("Workshop", 5);
        workshop.registerAttendee(alex);
        workshop.registerAttendee(new attendee("Alex Again", "ALEX@example.com"));

        assertEquals(1, workshop.getAttendees().size());
    }

    @Test
    void cancelRemovesAttendeeAndFreesSpot() {
        concert.registerAttendee(alex);
        concert.cancel(alex);

        assertFalse(concert.isRegistered(alex));
        assertEquals(1, concert.getSpotsLeft());
    }

    @Test
    void cancelledSpotCanBeTakenByAnotherAttendee() {
        concert.registerAttendee(alex);
        concert.cancel(alex);
        concert.registerAttendee(sam);

        assertTrue(concert.isRegistered(sam));
    }

    @Test
    void cancellingUnregisteredAttendeeChangesNothing() {
        concert.registerAttendee(alex);
        concert.cancel(sam);

        assertTrue(concert.isRegistered(alex));
        assertEquals(1, concert.getAttendees().size());
    }
}
