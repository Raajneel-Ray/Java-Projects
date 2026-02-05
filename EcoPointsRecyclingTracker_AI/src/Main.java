
import view.ConsoleView;
import service.RecyclingService;
import model.Household;
import model.RecyclingEvent;

public class Main {
    private static ConsoleView view;
    private static RecyclingService service;

    public static void main(String[] args) {
        view = new ConsoleView();
        service = new RecyclingService();
        boolean running = true;

        while (running) {
            view.displayMenu();
            String choice = view.getUserInput();

            switch (choice) {
                case "1" -> registerHousehold();
                case "2" -> logRecyclingEvent();
                case "3" -> view.displayHouseholds(service.getAllHouseholds());
                case "4" -> displayHouseholdEvents();
                case "5" -> generateReports();
                case "6" -> {
                    service.saveData();
                    view.displayMessage("Data saved. Goodbye!");
                    running = false;
                }
                default -> view.displayMessage("Invalid choice. Please select 1-6.");
            }
        }
    }

    private static void registerHousehold() {
        Household household = view.getHouseholdInput();
        if (household != null) {
            if (service.registerHousehold(household)) {
                view.displayMessage("Household registered successfully.");
            } else {
                view.displayMessage("Error: Household ID already exists.");
            }
        }
    }

    private static void logRecyclingEvent() {
        String id = view.getHouseholdId();
        Household household = service.getHousehold(id);

        if (household == null) {
            view.displayMessage("Error: Household ID not found.");
            return;
        }

        RecyclingEvent event = view.getEventInput();
        if (event != null) {
            service.logEvent(id, event);
            view.displayMessage("Recycling event logged! Points earned: " + event.ecoPoints());
        }
    }

    private static void displayHouseholdEvents() {
        String id = view.getHouseholdId();
        Household household = service.getHousehold(id);

        if (household == null) {
            view.displayMessage("Error: Household ID not found.");
            return;
        }

        view.displayEvents(household);
    }

    private static void generateReports() {
        Household top = service.getTopHousehold();
        if (top != null) {
            view.displayMessage("\nHousehold with Highest Points:");
            view.displayMessage(
                    "ID: " + top.getId() + ", Name: " + top.getName() + ", Points: " + top.getTotalPoints());
        } else {
            view.displayMessage("No households registered.");
        }

        double totalWeight = service.getTotalCommunityWeight();
        view.displayMessage("Total Community Recycling Weight: " + totalWeight + " kg");
    }
}
