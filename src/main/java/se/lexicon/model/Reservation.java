package se.lexicon.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    // todo: needs completion
    // Fields
    private String reservationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Customer customer;
    private ParkingSpot parkingSpot;
    private Status status;

    // Constructors

    public Reservation(Customer customer, ParkingSpot parkingSpot, int hours) {
        this.reservationId = UUID.randomUUID().toString();
        this.startTime = LocalDateTime.now();
        this.endTime = startTime.plusHours(hours);
        this.customer = customer;
        this.parkingSpot = parkingSpot;
        this.status = Status.ACTIVE;
    }

    // Methods (getters, setters, toString and...)

    public String getReservationId() {
        return reservationId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Status getStatus() {
        return status;
    }

    public void setEndTime(int hours) {
        if (hours <= 0) throw new IllegalArgumentException("Hours must be greater than 0");
        this.endTime = this.endTime.plusHours(hours);
    }

    public void complete() {
        this.status = Status.COMPLETED;
    }

    @Override
    public String toString() {
        return """
                Reservation Details:
                Reservation Id : %s
                Start Time : %s
                End Time : %s
                Parking Spot : %s
                Customer : %s
                """.formatted(
                reservationId,
                startTime,
                endTime,
                parkingSpot,
                customer);
    }
}
