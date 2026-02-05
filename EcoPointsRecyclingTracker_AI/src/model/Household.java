package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Household implements Serializable {
    private final String id;
    private final String name;
    private final String address;
    private final LocalDate joinDate;
    private final List<RecyclingEvent> events;
    private double totalPoints;

    public Household(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.events = new ArrayList<>();
        this.totalPoints = 0.0;
    }

    public void addEvent(RecyclingEvent event) {
        events.add(event);
        totalPoints += event.ecoPoints();
    }

    public double getTotalWeight() {
        return events.stream().mapToDouble(RecyclingEvent::weight).sum();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public List<RecyclingEvent> getEvents() {
        return new ArrayList<>(events);
    } // Return copy for safety

    public double getTotalPoints() {
        return totalPoints;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Points: " + totalPoints;
    }
}
