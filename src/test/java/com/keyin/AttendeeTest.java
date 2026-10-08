package com.keyin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttendeeTest {
    @Test
    void normalizesEmailToLowercase() {
        Attendee person = new Attendee("Alex Smith", "Alex@Example.COM");

        assertEquals("alex@example.com", person.getEmail());
    }

    @Test
    void rejectsNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Attendee(null, "alex@example.com"));
    }

    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Attendee("   ", "alex@example.com"));
    }

    @Test
    void rejectsNullEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Attendee("Alex Smith", null));
    }

    @Test
    void rejectsEmailWithoutAtSign() {
        assertThrows(IllegalArgumentException.class,
                () -> new Attendee("Alex Smith", "alex.example.com"));
    }

    @Test
    void attendeesWithSameEmailAreEqualRegardlessOfCase() {
        Attendee first = new Attendee("Alex Smith", "Alex@Example.COM");
        Attendee second = new Attendee("Different Name", "alex@example.com");

        assertEquals(first, second);
    }

    @Test
    void attendeesWithDifferentEmailsAreNotEqual() {
        Attendee first = new Attendee("Alex Smith", "alex@example.com");
        Attendee second = new Attendee("Alex Smith", "other@example.com");

        assertNotEquals(first, second);
    }

    @Test
    void equalAttendeesHaveSameHashCode() {
        Attendee first = new Attendee("Alex Smith", "Alex@Example.COM");
        Attendee second = new Attendee("Alex Smith", "alex@example.com");

        assertEquals(first.hashCode(), second.hashCode());
    }
}
