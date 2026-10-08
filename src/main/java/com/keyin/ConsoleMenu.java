package com.keyin;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleMenu {
    private static final String MENU = """

            Event Booking System
            1. Create event
            2. Register attendee
            3. Cancel registration
            4. View event
            5. Cancel event
            0. Exit
            Choose an option:\s""";

    private final EventManager eventManager = new EventManager();
    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleMenu(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    public void run() {
        boolean running = true;
        while (running) {
            out.print(MENU);
            String choice = scanner.nextLine().trim();
            running = !choice.equals("0");
            if (running) {
                handleChoice(choice);
            }
        }
        out.println("Goodbye!");
    }

    private void handleChoice(String choice) {
        try {
            switch (choice) {
                case "1" -> createEvent();
                case "2" -> registerAttendee();
                case "3" -> cancelRegistration();
                case "4" -> viewEvent();
                case "5" -> cancelEvent();
                default -> out.println("Invalid option, please try again.");
            }
        } catch (IllegalArgumentException e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private void createEvent() {
        String title = prompt("Event title: ");
        int capacity = parseCapacity(prompt("Capacity: "));
        eventManager.createEvent(title, capacity);
        out.println("Created event: " + title);
    }

    private void registerAttendee() {
        String title = prompt("Event title: ");
        Attendee attendee = promptAttendee();
        if (eventManager.registerAttendee(title, attendee)) {
            out.println(attendee.getName() + " is registered for " + title);
        } else {
            out.println("Could not register " + attendee.getName() + " (event full or already registered)");
        }
    }

    private void cancelRegistration() {
        String title = prompt("Event title: ");
        Attendee attendee = promptAttendee();
        eventManager.cancelAttendee(title, attendee);
        out.println("Registration cancelled for " + attendee.getName());
    }

    private void viewEvent() {
        Event event = eventManager.getEvent(prompt("Event title: "));
        out.printf("%s: %d/%d registered, %d spots left%n",
                event.getTitle(), event.getAttendees().size(),
                event.getCapacity(), event.getSpotsLeft());
        event.getAttendees().forEach(attendee ->
                out.println(" - " + attendee.getName() + " <" + attendee.getEmail() + ">"));
    }

    private void cancelEvent() {
        String title = prompt("Event title: ");
        eventManager.cancelEvent(title);
        out.println("Cancelled event: " + title);
    }

    private Attendee promptAttendee() {
        return new Attendee(prompt("Attendee name: "), prompt("Attendee email: "));
    }

    private int parseCapacity(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Capacity must be a whole number");
        }
    }

    private String prompt(String message) {
        out.print(message);
        return scanner.nextLine();
    }
}
