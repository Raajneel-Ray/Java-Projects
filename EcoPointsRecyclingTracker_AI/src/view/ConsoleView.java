package view;

import model.Household;
import model.MaterialType;
import model.RecyclingEvent;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n=== Eco-Points Recycling Tracker ===");
        System.out.println("1. Register Household");
        System.out.println("2. Log Recycling Event");
        System.out.println("3. Display Households");
        System.out.println("4. Display Household Events");
        System.out.println("5. Generate Reports");
        System.out.println("6. Save and Exit");
        System.out.print("Choose an option: ");
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    public Household getHouseholdInput() {
        System.out.print("Enter household ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter household name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter household address: ");
        String address = scanner.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("Error: ID and Name cannot be empty.");
            return null;
        }
        return new Household(id, name, address);
    }

    public String getHouseholdId() {
        System.out.print("Enter household ID: ");
        return scanner.nextLine().trim();
    }

    public RecyclingEvent getEventInput() {
        System.out.println("Select Material Type:");
        for (MaterialType type : MaterialType.values()) {
            System.out.println("- " + type.name());
        }
        System.out.print("Enter material type: ");
        String typeStr = scanner.nextLine().trim().toUpperCase();

        try {
            MaterialType type = MaterialType.valueOf(typeStr);
            System.out.print("Enter weight (kg): ");
            double weight = Double.parseDouble(scanner.nextLine().trim());

            if (weight <= 0) {
                System.out.println("Error: Weight must be positive.");
                return null;
            }
            return new RecyclingEvent(type, weight);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid material type or weight.");
            return null;
        }
    }

    public void displayHouseholds(List<Household> households) {
        if (households.isEmpty()) {
            System.out.println("No households registered.");
        } else {
            System.out.println("\nRegistered Households:");
            households.forEach(System.out::println);
        }
    }

    public void displayEvents(Household household) {
        System.out.println("\nEvents for " + household.getName() + ":");
        List<RecyclingEvent> events = household.getEvents();
        if (events.isEmpty()) {
            System.out.println("No events logged.");
        } else {
            events.forEach(System.out::println);
            System.out.println("Total Weight: " + household.getTotalWeight() + " kg");
            System.out.println("Total Points: " + household.getTotalPoints());
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
