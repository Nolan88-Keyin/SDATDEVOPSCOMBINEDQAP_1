package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void registersAttendee() {
        Event concert = new Event("Concert", 1);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        concert.registerAttendee(alex);

        Assertions.assertTrue(concert.isRegistered(alex));
        Assertions.assertEquals(1, concert.getAttendees().size());
    }

    @Test
    void reportsFullAtCapacity() {
        Event concert = new Event("Concert", 1);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        concert.registerAttendee(alex);

        Assertions.assertTrue(concert.isFull());
        Assertions.assertEquals(0, concert.getSpotsLeft());
    }

    @Test
    void rejectsRegistrationWhenFull() {
        Event concert = new Event("Concert", 1);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");
        Attendee sam = new Attendee("Sam Jones", "sam@example.com");

        concert.registerAttendee(alex);
        concert.registerAttendee(sam);

        Assertions.assertFalse(concert.isRegistered(sam));
        Assertions.assertEquals(1, concert.getAttendees().size());
    }

    @Test
    void preventsDuplicateRegistration() {
        Event workshop = new Event("Workshop", 5);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        workshop.registerAttendee(alex);
        workshop.registerAttendee(new Attendee("Alex Again", "ALEX@example.com"));

        Assertions.assertTrue(workshop.isRegistered(alex));
        Assertions.assertEquals(1, workshop.getAttendees().size());
    }

    @Test
    void cancellationRemovesAttendeeAndFreesSpot() {
        Event concert = new Event("Concert", 1);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");

        concert.registerAttendee(alex);
        concert.cancelAttendee(alex);

        Assertions.assertFalse(concert.isRegistered(alex));
        Assertions.assertEquals(1, concert.getSpotsLeft());
    }

    @Test
    void cancelledSpotCanBeTakenByAnotherAttendee() {
        Event concert = new Event("Concert", 1);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");
        Attendee sam = new Attendee("Sam Jones", "sam@example.com");

        concert.registerAttendee(alex);
        concert.cancelAttendee(alex);
        concert.registerAttendee(sam);

        Assertions.assertTrue(concert.isRegistered(sam));
    }

    @Test
    void cancellingUnregisteredAttendeeChangesNothing() {
        Event concert = new Event("Concert", 1);
        Attendee alex = new Attendee("Alex Smith", "alex@example.com");
        Attendee sam = new Attendee("Sam Jones", "sam@example.com");

        concert.registerAttendee(alex);
        concert.cancelAttendee(sam);

        Assertions.assertTrue(concert.isRegistered(alex));
        Assertions.assertEquals(1, concert.getAttendees().size());
    }
}
