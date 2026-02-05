package model;

public enum MaterialType {
    PLASTIC(10.0),
    GLASS(5.0),
    METAL(15.0),
    PAPER(2.0);

    private final double pointsPerKg;

    MaterialType(double pointsPerKg) {
        this.pointsPerKg = pointsPerKg;
    }

    public double getPointsPerKg() {
        return pointsPerKg;
    }
}
