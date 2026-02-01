import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

// main application to run the eco points recycling tracker

public class EcoPointsRecyclingTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Household> households = new HashMap<>(); // need this for mapping the household object
    // with their respective ids

        public static void main(String[] args) {
        boolean running = true;
        loadHouseholdsFromFile();
        while (running) {
            System.out.println("\n=== Eco-Points Recycling Tracker ===");
            System.out.println("1. Register Household");
            System.out.println("2. Log Recycling Event");
            System.out.println("3. Display Households");
            System.out.println("4. Display Household Recycling Events");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            // handle the choice entered here using switch(case)
            
            switch (choice) {
                case "1":
                    registerHousehold();
                    break;
                case "2":
                    logRecyclingEvent();
                    break;
                case "3":
                    displayHouseholds();
                    break;
                case "4":
                    displayHouseholdEvents();
                    break;
                case "5":
                    generateReports();
                    break;
                case "6":
                    saveHouseholdsToFile();
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }
        }
    }

    // Add the methods to handle each choice here

    // This method collects input from the user, validates the ID, creates a Household object, and adds it to the household's map.
    private static void registerHousehold() {
        // user to input the household id 
        System.out.println("Enter household ID - ");
        String id = scanner.nextLine().trim(); // read and trim the user input
        if(households.containsKey(id)) { // check if household id exists in the map
            System.out.println("Error: Household ID already exists.");
            return;
        }
        // enter the rest of the detail to call a parameterized constructor
        System.out.println("Enter the household name - ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter the household address - ");
        String address = scanner.nextLine().trim();

        // Create a new Household object using the provided details
        Household household = new Household(id, name, address);

        // put it in the map households using id as key
        households.put(id, household);

        // confirm whether the household is registered successfully
        System.out.println("Household with id - " + id + " registered successfully on " + household.getJoinDate());
    }

    // This method asks for the household ID and validates the household ID. Then the method collects recycling details, validates the recycling details, creates a RecyclingEvent, and updates the household record.
    private static void logRecyclingEvent() {
        // ask the user for household id 
        System.out.println("Enter household ID - ");
        String id = scanner.nextLine().trim(); // read and trim the user input

        // search for it in the households map
        Household household = households.get(id);

        // if null show error and exit
        if(household == null) {
            System.out.println("Error: Household ID not found.");
            return;
        }
        // ask the user for material type they recycled
        System.out.println("Enter material type (plastic/glass/metal/paper) - ");
        String materialType = scanner.nextLine().trim();

        double weight = 0.0;

        // loop until valid weight is entered
        while(true) {
            try {
                System.out.println("Enter the weight in Kilograms - ");
                weight = Double.parseDouble(scanner.nextLine());

                if(weight<=0) throw new IllegalArgumentException();

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight. Must be a positive number.");
            }  catch (IllegalArgumentException e) {
                System.out.println("Invalid weight. Must be a positive number.");
            }
        }
        // add the new event to household and add points
        RecyclingEvent event = new RecyclingEvent(materialType, weight);

        //Add the new event to the household and update points
        household.addEvents(event);

        // show success message and points earned
        System.out.println("Recycling event logged! Points earned - " + event.getEcoPoints());
    }
    // this displays all the registered households
    private static void displayHouseholds() {
        // Check if the households map is empty
        if (households.isEmpty()) {
            System.out.println("No households registered.");
            return; // Exit early if there's nothing to show
        }
        // If there are households, print a header first
        System.out.println("\nRegistered Households:");
        // Loop through each household in the map and print its details
        for (Household h : households.values()) {
            System.out.println("ID: " + h.getId() +
                               ", Name: " + h.getName() +
                               ", Address: " + h.getAddress() +
                               ", Joined: " + h.getJoinDate());
        }
    }
    // This method displays all recycling events for a specific household. This method displays the total weight and total points earned as well.
    private static void displayHouseholdEvents() {
        System.out.println("Enter the household ID - ");
        String id = scanner.nextLine();

        Household household = households.get(id);

        if(household == null) {
            System.out.println("Household not found.");
            return;
        }
        // Print a header with the household's name
        System.out.println("\nRecycling Event for " + household.getName() + ":");

        if(household.getEvents().isEmpty()) {
            System.out.println("No event logged.");
        }
        else {
            for(RecyclingEvent e : household.getEvents()) {
                //Print the stringified version of the event
                System.out.println(e);
            }
            System.out.println("Total Weight: " + household.getTotalWeight() + " kg");
            System.out.println("Total Points: " + household.getTotalPoints() + " pts");
        }
    }

    //This method generates simple reports for the Eco-Points Recycling Tracker.
    //  The household with the highest total eco points.
    //  The total community recycling weight.
    private static void generateReports() {
        if(households.isEmpty()) {
            System.out.println("No household registered.");
            return;
        }
        // Find the household with the highest points
        Household top = null;
        for(Household h : households.values()) {
            // if top is null or the current household has more points update top with current household
            if(top == null || h.getTotalPoints() > top.getTotalPoints()) {
                top = h;
            }
        }
        // Print details of the top household
        System.out.println("\nHousehold with Highest Points:");
        System.out.println("ID: " + top.getId() +
                           ", Name: " + top.getName() +
                           ", Points: " + top.getTotalPoints());
        
        // Calculate total community recycling weight
        double totalCommunityWeight = 0.0;
        for(Household h : households.values()) {
            totalCommunityWeight += h.getTotalWeight();
        }
        // Print total community weight
        System.out.println("Total Community Recycling Weight: " + totalCommunityWeight + " kg");
    }

    // This method saves all household data to a file using serialization. This method ensures that households and their recycling events persist even after the program closes.
    private static void saveHouseholdsToFile() {
        /**
         * Serialization converts your HashMap of Household objects into a byte stream that can be saved on disk.
            ObjectOutputStream is a special stream to write whole objects, not just text.
            try-catch makes sure that file problems don't crash the app.
         */
        try {
            // Create a FileOutputStream to write to the file named "households.ser"
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("households.ser"));
            // Write the entire households map to the file
            out.writeObject(households);
        } catch (IOException e) {
            // If something goes wrong while saving, print an error message
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked") // suppresses unchecked cast warning when reading the object
    private static void loadHouseholdsFromFile() {
        // use a try with resources block to automatically close the input stream when the block is executed
        try (
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("households.ser"));
        ) {
            // Read the object from the file and cast it back to the correct type
            households = (Map<String, Household>) in.readObject();
            // Confirmation message to let the user know data was loaded
            System.out.println("Household data loaded.");
        } catch (FileNotFoundException e) {
            // If the file doesn't exist yet, that's okay — start with empty data
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            // Handle other errors, like if the file is corrupted or unreadable
            System.out.println("Error loading data: " + e.getMessage());
        }

    }
}