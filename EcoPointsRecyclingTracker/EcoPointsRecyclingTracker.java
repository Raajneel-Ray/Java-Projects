import java.util.*;

// main application to run the eco points recycling tracker

public class EcoPointsRecyclingTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Household> households = new HashMap<>(); // need this for mapping the household object
    // with their respective ids

        public static void main(String[] args) {
        boolean running = true;
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

}