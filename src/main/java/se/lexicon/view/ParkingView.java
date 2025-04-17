// ParkingView.java
package se.lexicon.view;

import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Scanner;

public class ParkingView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n===== Parking App Menu =====");
        System.out.println("1. Register Customer");
        System.out.println("2. Display Parking Spots");
        System.out.println("3. Reserve a Parking Spot");
        System.out.println("4. Vacate Parking Spot");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println("\033[0;31m" + error + "\033[0m");
    }

    public void showParkingSpots(List<ParkingSpot> spots) {
        System.out.println("===== Parking Spots =====");
        for (ParkingSpot spot : spots) {
            String status = spot.isOccupied() ? "X" : "\u2713";
            System.out.println("Spot Id: " + spot.getSpotNumber() + " | Area: " + spot.getAreaCode() + " | Status: " + status);
        }
        System.out.println("=========================");
    }

    public String prompt(String promptText) {
        System.out.print(promptText);
        return scanner.nextLine();
    }
}
