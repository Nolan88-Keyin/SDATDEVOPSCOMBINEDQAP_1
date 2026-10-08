package com.keyin;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {
    private final Map<String, Event> events = new LinkedHashMap<>();

    public Event createEvent(String title, int capacity) {
        if (events.containsKey(title)) {
            throw new IllegalArgumentException("Event already exists: " + title);
        }
        Event newEvent = new Event(title, capacity);
        events.put(title, newEvent);
        return newEvent;
    }

    public Event getEvent(String title) {
        Event foundEvent = events.get(title);
        if (foundEvent == null) {
            throw new IllegalArgumentException("Event does not exist: " + title);
        }
        return foundEvent;
    }

    public int getEventCount() {
        return events.size();
    }

    public void cancelEvent(String title) {
        if (events.remove(title) == null) {
            throw new IllegalArgumentException("Event does not exist: " + title);
        }
    }

    public Map<String, Event> getEvents() {
        return Collections.unmodifiableMap(events);
    }

    public boolean registerAttendee(String eventTitle, Attendee attendee) {
        return getEvent(eventTitle).registerAttendee(attendee);
    }

    public void cancelAttendee(String eventTitle, Attendee attendee) {
        getEvent(eventTitle).cancelAttendee(attendee);
    }
}
