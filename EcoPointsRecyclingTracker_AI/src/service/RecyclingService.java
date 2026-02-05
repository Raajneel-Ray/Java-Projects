package service;

import model.Household;
import model.RecyclingEvent;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class RecyclingService {
    private Map<String, Household> households;
    private final String DATA_FILE = "households_v2.ser";

    public RecyclingService() {
        this.households = new HashMap<>();
        loadData();
    }

    public boolean registerHousehold(Household household) {
        if (households.containsKey(household.getId())) {
            return false;
        }
        households.put(household.getId(), household);
        return true;
    }

    public Household getHousehold(String id) {
        return households.get(id);
    }

    public void logEvent(String householdId, RecyclingEvent event) {
        Household household = households.get(householdId);
        if (household != null) {
            household.addEvent(event);
        }
    }

    public List<Household> getAllHouseholds() {
        return new ArrayList<>(households.values());
    }

    public Household getTopHousehold() {
        return households.values().stream()
                .max(Comparator.comparingDouble(Household::getTotalPoints))
                .orElse(null);
    }

    public double getTotalCommunityWeight() {
        return households.values().stream()
                .mapToDouble(Household::getTotalWeight)
                .sum();
    }

    public void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(households);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            households = (Map<String, Household>) in.readObject();
        } catch (FileNotFoundException e) {
            // New file, start empty
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}
