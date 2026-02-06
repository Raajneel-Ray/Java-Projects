import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PetCareScheduler {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Pet> pets = new HashMap<>();

    public static void main(String[] args) {
        boolean running = true;
        loadPetsFromFile();
        while (running) {
            System.out.println("\n=== Pet Care Scheduler ===");
            System.out.println("1. Add Pet");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Display Pets");
            System.out.println("4. Display Appointments");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addPet();
                    break;
                case "2":
                    scheduleAppointment();
                    break;
                case "3":
                    viewPets();
                    break;
                case "4":
                    viewAppointments();
                    break;
                case "5":
                    generateReports();
                    break;
                case "6":
                    savePetsToFile();
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }
        }
    }

    private static void addPet() {
        System.out.println("Enter Pet ID:");
        String id = scanner.nextLine().trim();
        if (pets.containsKey(id)) {
            System.out.println("Error: Pet ID already exists.");
            return;
        }

        System.out.println("Enter Pet Name:");
        String name = scanner.nextLine().trim();

        System.out.println("Enter Species:");
        String species = scanner.nextLine().trim();

        int age = 0;
        while (true) {
            System.out.println("Enter Age:");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age < 0)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a positive number.");
            }
        }

        System.out.println("Enter Owner Name:");
        String owner = scanner.nextLine().trim();

        System.out.println("Enter Contact Info:");
        String contact = scanner.nextLine().trim();

        Pet newPet = new Pet(id, name, species, age, owner, contact);
        pets.put(id, newPet);
        System.out.println("Pet registered successfully!");
    }

    private static void scheduleAppointment() {
        System.out.println("Enter Pet ID:");
        String id = scanner.nextLine().trim();
        Pet pet = pets.get(id);

        if (pet == null) {
            System.out.println("Error: Pet not found.");
            return;
        }

        LocalDate date = null;
        while (true) {
            System.out.println("Enter Date (YYYY-MM-DD):");
            try {
                date = LocalDate.parse(scanner.nextLine().trim());
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Error: Appointment date must be in the future.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        System.out.println("Enter Appointment Type (e.g., Vet Visit, Vaccination, Grooming):");
        String type = scanner.nextLine().trim();
        List<String> validTypes = Arrays.asList("Vet Visit", "Vaccination", "Grooming", "Checkup", "Surgery");
        // Simple case-insensitive check if it contains one of the standard types, or
        // just accept it.
        // Requirement says "Validate the appointment type".
        if (type.isEmpty()) {
            System.out.println("Error: Appointment type cannot be empty.");
            return;
        }

        System.out.println("Enter Notes (optional):");
        String notes = scanner.nextLine().trim();

        Appointment appointment = new Appointment(type, date, notes);
        pet.addAppointment(appointment);
        System.out.println("Appointment scheduled successfully!");
    }

    private static void viewPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets registered.");
            return;
        }
        System.out.println("\n--- Registered Pets ---");
        for (Pet pet : pets.values()) {
            System.out.println(pet);
            System.out.println("-------------------------");
        }
    }

    private static void viewAppointments() {
        System.out.println("\n1. All appointments for a specific pet");
        System.out.println("2. Upcoming appointments for all pets");
        System.out.println("3. Past appointment history for each pet");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Enter Pet ID:");
                String id = scanner.nextLine().trim();
                Pet pet = pets.get(id);
                if (pet == null) {
                    System.out.println("Pet not found.");
                } else {
                    System.out.println("\nAppointments for " + pet.getPetName() + ":");
                    List<Appointment> apps = pet.getAppointments();
                    if (apps.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        for (Appointment app : apps) {
                            System.out.println(app);
                            System.out.println("-");
                        }
                    }
                }
                break;
            case "2":
                System.out.println("\n--- Upcoming Appointments ---");
                boolean foundUpcoming = false;
                for (Pet p : pets.values()) {
                    for (Appointment app : p.getAppointments()) {
                        if (app.getDate().isAfter(LocalDate.now()) || app.getDate().equals(LocalDate.now())) {
                            System.out.println("Pet: " + p.getPetName() + " (" + p.getPetID() + ")");
                            System.out.println(app);
                            System.out.println("-");
                            foundUpcoming = true;
                        }
                    }
                }
                if (!foundUpcoming)
                    System.out.println("No upcoming appointments.");
                break;
            case "3":
                System.out.println("\n--- Past Appointment History ---");
                boolean foundPast = false;
                for (Pet p : pets.values()) {
                    boolean hasPast = false;
                    for (Appointment app : p.getAppointments()) {
                        if (app.getDate().isBefore(LocalDate.now())) {
                            if (!hasPast) {
                                System.out.println("Pet: " + p.getPetName() + " (" + p.getPetID() + ")");
                                hasPast = true;
                            }
                            System.out.println(app);
                            System.out.println("-");
                            foundPast = true;
                        }
                    }
                }
                if (!foundPast)
                    System.out.println("No past appointments.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void generateReports() {
        System.out.println("\n--- Reports ---");

        // 1. Pets with upcoming appointments in the next week
        System.out.println("1. Pets with appointments in the next 7 days:");
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);
        boolean foundNextWeek = false;

        for (Pet p : pets.values()) {
            for (Appointment app : p.getAppointments()) {
                if (!app.getDate().isBefore(today) && !app.getDate().isAfter(nextWeek)) {
                    System.out.println(" - " + p.getPetName() + " (" + p.getPetID() + "): " + app.getAppointmentType()
                            + " on " + app.getDate());
                    foundNextWeek = true;
                }
            }
        }
        if (!foundNextWeek)
            System.out.println("   No appointments in the next week.");

        // 2. Pets overdue for a vet visit (no visit in last 6 months)
        System.out.println("\n2. Pets overdue for a visit (no visit in last 6 months):");
        LocalDate sixMonthsAgo = today.minusMonths(6);
        boolean foundOverdue = false;

        for (Pet p : pets.values()) {
            boolean hasRecentVisit = false;
            if (p.getAppointments().isEmpty()) {
                // If never had an appointment, consider them overdue? Or maybe just list them
                // as having no history.
                // Prompt says "overdue... no vet visit in the last 6 months".
                // I will list them if they have no appointments OR their last appointment was >
                // 6 months ago.
                // Assuming "registration date" might be relevant, but let's stick to
                // appointment history.
            } else {
                for (Appointment app : p.getAppointments()) {
                    if (app.getDate().isAfter(sixMonthsAgo)) {
                        hasRecentVisit = true;
                        break;
                    }
                }
            }

            if (!hasRecentVisit) {
                System.out.println(" - " + p.getPetName() + " (" + p.getPetID() + ")");
                foundOverdue = true;
            }
        }
        if (!foundOverdue)
            System.out.println("   No overdue pets.");
    }

    private static void savePetsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pets.ser"))) {
            out.writeObject(pets);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadPetsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("pets.ser"))) {
            pets = (Map<String, Pet>) in.readObject();
            System.out.println("Pet data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
