package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AttendeeTest {
    @Test
    void normalizesEmailToLowercase() {
        Attendee person = new Attendee("Alex Smith", "Alex@Example.COM");

        Assertions.assertEquals("alex@example.com", person.getEmail());
    }

    @Test
    void rejectsNullName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Attendee(null, "alex@example.com"));
    }

    @Test
    void rejectsBlankName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Attendee("   ", "alex@example.com"));
    }

    @Test
    void rejectsNullEmail() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Attendee("Alex Smith", null));
    }

    @Test
    void rejectsBlankEmail() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Attendee("Alex Smith", "   "));
    }

    @Test
    void rejectsEmailWithoutAtSign() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Attendee("Alex Smith", "alex.example.com"));
    }

    @Test
    void attendeesWithSameEmailAreEqualRegardlessOfCase() {
        Attendee first = new Attendee("Alex Smith", "Alex@Example.COM");
        Attendee second = new Attendee("Different Name", "alex@example.com");

        Assertions.assertEquals(first, second);
    }

    @Test
    void attendeesWithDifferentEmailsAreNotEqual() {
        Attendee first = new Attendee("Alex Smith", "alex@example.com");
        Attendee second = new Attendee("Alex Smith", "other@example.com");

        Assertions.assertNotEquals(first, second);
    }

    @Test
    void equalAttendeesHaveSameHashCode() {
        Attendee first = new Attendee("Alex Smith", "Alex@Example.COM");
        Attendee second = new Attendee("Alex Smith", "alex@example.com");

        Assertions.assertEquals(first.hashCode(), second.hashCode());
    }
}
