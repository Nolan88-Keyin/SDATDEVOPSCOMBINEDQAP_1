package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttendeeTest {

    @Test
    public void testNormalizesEmailToLowercase() {
        attendee person = new attendee("Alex Smith", "Alex@Example.COM");

        Assertions.assertEquals("alex@example.com", person.getEmail());
    }

    @Test
    public void testRejectsNullName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new attendee(null, "alex@example.com"));
    }

    @Test
    public void testRejectsBlankName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new attendee("   ", "alex@example.com"));
    }

    @Test
    public void testRejectsNullEmail() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new attendee("Alex Smith", null));
    }

    @Test
    public void testRejectsEmailWithoutAtSign() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new attendee("Alex Smith", "alex.example.com"));
    }

    @Test
    public void testAttendeesWithSameEmailAreEqualRegardlessOfCase() {
        attendee first = new attendee("Alex Smith", "Alex@Example.COM");
        attendee second = new attendee("Different Name", "alex@example.com");

        Assertions.assertEquals(first, second);
    }

    @Test
    public void testAttendeesWithDifferentEmailsAreNotEqual() {
        attendee first = new attendee("Alex Smith", "alex@example.com");
        attendee second = new attendee("Alex Smith", "other@example.com");

        Assertions.assertNotEquals(first, second);
    }

    @Test
    public void testEqualAttendeesHaveSameHashCode() {
        attendee first = new attendee("Alex Smith", "Alex@Example.COM");
        attendee second = new attendee("Alex Smith", "alex@example.com");

        Assertions.assertEquals(first.hashCode(), second.hashCode());
    }
}
