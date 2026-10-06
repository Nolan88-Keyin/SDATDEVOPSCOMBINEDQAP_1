package com.keyin;

import java.util.LinkedHashSet;
import java.util.Set;

public class event {
    private final String title;
    private final int capacity;
    private final Set<attendee> attendees = new LinkedHashSet<>();

    public event(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<attendee> getAttendees() {
        return attendees;
    }

    public boolean isFull() {
        return attendees.size() >= capacity;
    }

    public boolean isRegistered(attendee attendee) {
        return attendees.contains(attendee);
    }

    public int getSpotsLeft() {
        return capacity - attendees.size();
    }

    public void registerAttendee(attendee attendee) {
        if (!isFull() && !isRegistered(attendee)) {
            attendees.add(attendee);
        }
    }

    public void cancel(attendee attendee) {
        attendees.remove(attendee);
    }
}
