package com.keyin;

import java.util.LinkedHashMap;
import java.util.Map;

public class eventManager {
    private final Map<String, event> events = new LinkedHashMap<>();

    public event createEvent(String title, int capacity) {
        if (events.containsKey(title)) {
            throw new IllegalArgumentException("Event already exists: " + title);
        }
        event event = new event(title, capacity);
        events.put(title, event);
        return event;
    }

    public event getEvent(String title) {
        event event = events.get(title);
        if (event == null) {
            throw new IllegalArgumentException("Event does not exist: " + title);
        }
        return event;
    }

    public int getEventCount() {
        return events.size();
    }

    public void cancelEvent(String title) {
        if (!events.containsKey(title)) {
            throw new IllegalArgumentException("Event does not exist: " + title);
        }
        events.remove(title);
    }

    public Map<String, event> getEvents() {
        return events;
    }

    public void registerAttendee(String eventTitle, attendee attendee) {
        event event = getEvent(eventTitle);
        event.registerAttendee(attendee);
    }

    public void cancelAttendee(String eventTitle, attendee attendee) {
        event event = getEvent(eventTitle);
        event.cancel(attendee);
    }
 }
