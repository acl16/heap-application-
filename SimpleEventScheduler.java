import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

class Event implements Comparable<Event> {
    String name;
    LocalDateTime dateTime;

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    @Override
    public int compareTo(Event other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return name + " at " + dateTime.format(formatter);
    }
}

public class SimpleEventScheduler {
    public static void main(String[] args) {
        PriorityQueue<Event> events = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (true) {
            System.out.println("\n--- Simple Event Scheduler ---");
            System.out.println("1. Add Event");
            System.out.println("2. View Next Event");
            System.out.println("3. Remove Next Event");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter event name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter event date and time (yyyy-MM-dd HH:mm): ");
                    String dateTimeInput = scanner.nextLine();
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput, formatter);
                        events.offer(new Event(name, dateTime));
                        System.out.println("Event added!");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Try again.");
                    }
                }
                case 2 -> {
                    if (events.isEmpty()) {
                        System.out.println("No events scheduled.");
                    } else {
                        System.out.println("Next Event: " + events.peek());
                    }
                }
                case 3 -> {
                    if (events.isEmpty()) {
                        System.out.println("No events to remove.");
                    } else {
                        System.out.println("Removed: " + events.poll());
                    }
                }
                case 4 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
