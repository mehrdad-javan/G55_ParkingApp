package se.lexicon.model;

public class ParkingSpot {
    // todo: needs completion

    // Fields
    private Integer spotNumber;
    private Integer areaCode;
    private boolean occupied;

    // Constructors

    public ParkingSpot(Integer spotNumber, Integer areaCode) {
        this.spotNumber = spotNumber;
        this.areaCode = areaCode;
    }

    // Methods (getters, setters, toString and...)

    public Integer getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(Integer spotNumber) {
        // todo: add  validation
        this.spotNumber = spotNumber;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        // todo: add  validation
        this.areaCode = areaCode;
    }

    public boolean isOccupied() {
        return occupied;
    }


    public void occupy() {
        this.occupied = true;

    }

    public void vacate() {
        this.occupied = false;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", areaCode=" + areaCode +
                ", occupied=" + occupied +
                '}';
    }
}
