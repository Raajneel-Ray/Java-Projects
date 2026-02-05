package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a single recycling event.
 * Using a Java Record since it's an immutable data carrier.
 */
public record RecyclingEvent(MaterialType type, double weight, LocalDate date, double ecoPoints)
        implements Serializable {
    public RecyclingEvent(MaterialType type, double weight) {
        this(type, weight, LocalDate.now(), weight * type.getPointsPerKg());
    }

    @Override
    public String toString() {
        return "Date: " + date +
                ", Material: " + type +
                ", Weight: " + weight + "kg" +
                ", Points: " + ecoPoints;
    }
}
