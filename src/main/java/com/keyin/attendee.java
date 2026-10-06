package com.keyin;

import java.util.Objects;

public class attendee {
    private final String name;
    private final String email;

    public attendee(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Attendee name is required");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("A valid email is required");
        }
        this.name = name;
        this.email = email.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof attendee attendee && email.equals(attendee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
