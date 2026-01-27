import java.io.Serializable; //  This lets the class be serializable, meaning you can save this object to a file and load it back later. No recycling event gets left behind.
import java.time.LocalDate;

// Represents a single recycling event for a household

public class RecyclingEvent implements Serializable {
    /**RecyclingEvent with the following attributes:
        Material type (such as plastic, glass, metal, and paper)
        Weight in kilograms
        Date of recycling
        Eco points earned */
    private String materialType;
    private double weight;
    private LocalDate date;
    private double ecoPoints;

    public RecyclingEvent(String materialType, Double weight) {
        // constructor to make a new recycling event with given input parameter material type and weight
        this.materialType = materialType;
        this.weight = weight;
        this.date = LocalDate.now();
        this.ecoPoints = weight * 10; // 10 points per kg
    }

    public String getMaterialType() {
        return this.materialType;
    }

    public Double getWeight() {
        return this.weight;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public double getEcoPoints() {
        return this.ecoPoints;
    }

    @Override
    // Overrides the toString method in Object class to provide the stringified version of the Recycling event class.
    public String toString() {
        return "Date : " + this.date +
                "\nMaterial Type : " + this.materialType +
                "\nWeight : " + this.weight +
                "\nEco Points : " + this.ecoPoints;
    }
}