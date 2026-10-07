package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEventDetails() {
        event concert = new event("Concert", 1);

        Assertions.assertEquals("Concert", concert.getTitle());
        Assertions.assertEquals(1, concert.getCapacity());
    }

    @Test
    public void testRegisterAttendee() {
        event concert = new event("Concert", 1);
        attendee alex = new attendee("Alex Smith", "alex@example.com");

        concert.registerAttendee(alex);

        Assertions.assertTrue(concert.isRegistered(alex));
        Assertions.assertEquals(1, concert.getAttendees().size());
    }

    @Test
    public void testNewEventHasAllSpotsLeft() {
        event concert = new event("Concert", 1);

        Assertions.assertEquals(1, concert.getSpotsLeft());
        Assertions.assertFalse(concert.isFull());
    }

    @Test
    public void testEventIsFullAtCapacity() {
        event concert = new event("Concert", 1);
        attendee alex = new attendee("Alex Smith", "alex@example.com");

        concert.registerAttendee(alex);

        Assertions.assertTrue(concert.isFull());
        Assertions.assertEquals(0, concert.getSpotsLeft());
    }

    @Test
    public void testRejectRegistrationWhenFull() {
        event concert = new event("Concert", 1);
        attendee alex = new attendee("Alex Smith", "alex@example.com");
        attendee sam = new attendee("Sam Jones", "sam@example.com");

        concert.registerAttendee(alex);
        concert.registerAttendee(sam);

        Assertions.assertFalse(concert.isRegistered(sam));
        Assertions.assertEquals(1, concert.getAttendees().size());
    }

    @Test
    public void testPreventDuplicateRegistration() {
        event workshop = new event("Workshop", 5);
        attendee alex = new attendee("Alex Smith", "alex@example.com");

        workshop.registerAttendee(alex);
        workshop.registerAttendee(new attendee("Alex Again", "ALEX@example.com"));

        Assertions.assertTrue(workshop.isRegistered(alex));
        Assertions.assertEquals(1, workshop.getAttendees().size());
    }

    @Test
    public void testCancelRemovesAttendeeAndFreesSpot() {
        event concert = new event("Concert", 1);
        attendee alex = new attendee("Alex Smith", "alex@example.com");

        concert.registerAttendee(alex);
        concert.cancel(alex);

        Assertions.assertFalse(concert.isRegistered(alex));
        Assertions.assertEquals(1, concert.getSpotsLeft());
    }

    @Test
    public void testCancelledSpotCanBeTakenByAnotherAttendee() {
        event concert = new event("Concert", 1);
        attendee alex = new attendee("Alex Smith", "alex@example.com");
        attendee sam = new attendee("Sam Jones", "sam@example.com");

        concert.registerAttendee(alex);
        concert.cancel(alex);
        concert.registerAttendee(sam);

        Assertions.assertTrue(concert.isRegistered(sam));
    }

    @Test
    public void testCancellingUnregisteredAttendeeChangesNothing() {
        event concert = new event("Concert", 1);
        attendee alex = new attendee("Alex Smith", "alex@example.com");
        attendee sam = new attendee("Sam Jones", "sam@example.com");

        concert.registerAttendee(alex);
        concert.cancel(sam);

        Assertions.assertTrue(concert.isRegistered(alex));
        Assertions.assertEquals(1, concert.getAttendees().size());
    }
}
