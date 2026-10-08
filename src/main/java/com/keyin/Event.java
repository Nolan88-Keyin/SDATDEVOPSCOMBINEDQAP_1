package com.keyin;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Event {
    private final String title;
    private final int capacity;
    private final Set<Attendee> attendees = new LinkedHashSet<>();

    public Event(String title, int capacity) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Event title is required");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Event capacity must be positive");
        }
        this.title = title;
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<Attendee> getAttendees() {
        return Collections.unmodifiableSet(attendees);
    }

    public boolean isFull() {
        return attendees.size() >= capacity;
    }

    public boolean isRegistered(Attendee attendee) {
        return attendees.contains(Objects.requireNonNull(attendee, "Attendee is required"));
    }

    public int getSpotsLeft() {
        return capacity - attendees.size();
    }

    public boolean registerAttendee(Attendee attendee) {
        Objects.requireNonNull(attendee, "Attendee is required");
        if (isFull()) {
            return false;
        }
        return attendees.add(attendee);
    }

    public void cancelAttendee(Attendee attendee) {
        attendees.remove(Objects.requireNonNull(attendee, "Attendee is required"));
    }
}
