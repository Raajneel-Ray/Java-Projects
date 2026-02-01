import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Represent the household that will be participate in Eco pts program

public class Household implements Serializable {
    // Household with the following attributes
    private String id;
    private String name;
    private String address;
    private LocalDate joinDate;
    private List<RecyclingEvent> events; // list of multiple events participated by house
    private double totalPoints;

    public Household(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.events = new ArrayList<>();
        this.totalPoints = 0.0;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public LocalDate getJoinDate() { return joinDate; }
    public List<RecyclingEvent> getEvents() { return events; }
    public double getTotalPoints() { return totalPoints; }

    public void addEvents(RecyclingEvent event) {
        this.events.add(event);
        this.totalPoints += event.getEcoPoints();
    }

    public double getTotalWeight() {
        double total = 0.0;
        for(RecyclingEvent event : events) {
            total += event.getWeight();
        }
        return total;
    }
}