package com.keyin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttendeeTest {

    @Test
    void storesNameAndEmail() {
        attendee person = new attendee("Alex Smith", "alex@example.com");

        assertEquals("Alex Smith", person.getName());
        assertEquals("alex@example.com", person.getEmail());
    }

    @Test
    void normalizesEmailToLowercase() {
        attendee person = new attendee("Alex Smith", "Alex@Example.COM");

        assertEquals("alex@example.com", person.getEmail());
    }

    @Test
    void rejectsNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new attendee(null, "alex@example.com"));
    }

    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new attendee("   ", "alex@example.com"));
    }

    @Test
    void rejectsNullEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new attendee("Alex Smith", null));
    }

    @Test
    void rejectsEmailWithoutAtSign() {
        assertThrows(IllegalArgumentException.class,
                () -> new attendee("Alex Smith", "alex.example.com"));
    }

    @Test
    void attendeesWithSameEmailAreEqualRegardlessOfCase() {
        attendee first = new attendee("Alex Smith", "Alex@Example.COM");
        attendee second = new attendee("Different Name", "alex@example.com");

        assertEquals(first, second);
    }

    @Test
    void attendeesWithDifferentEmailsAreNotEqual() {
        attendee first = new attendee("Alex Smith", "alex@example.com");
        attendee second = new attendee("Alex Smith", "other@example.com");

        assertNotEquals(first, second);
    }

    @Test
    void equalAttendeesHaveSameHashCode() {
        attendee first = new attendee("Alex Smith", "Alex@Example.COM");
        attendee second = new attendee("Alex Smith", "alex@example.com");

        assertEquals(first.hashCode(), second.hashCode());
    }
}
