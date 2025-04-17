// ParkingController.java
package se.lexicon.controller;

import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.view.ParkingView;

import java.util.ArrayList;
import java.util.Optional;

public class ParkingController {

    private final ParkingView view = new ParkingView();
    private final ParkingSpotDaoImpl parkingSpotDao = new ParkingSpotDaoImpl();
    private final CustomerDaoImpl customerDao = new CustomerDaoImpl();
    private final ReservationDaoImpl reservationDao = new ReservationDaoImpl();

    public void start() {
        for (int i = 0; i < 10; i++) {
            ParkingSpot spot = new ParkingSpot(i, 101);
            parkingSpotDao.save(spot);
        }

        boolean running = true;
        while (running) {
            view.showMenu();
            String option = view.prompt("");

            switch (option) {
                case "1" -> registerCustomer();
                case "2" -> view.showParkingSpots(parkingSpotDao.findAll());
                case "3" -> reserveParkingSpot();
                case "4" -> vacateParkingSpot();
                case "5" -> {
                    view.showMessage("Exiting... Goodbye!");
                    running = false;
                }
                default -> view.showError("Invalid option. Try again.");
            }
        }
    }

    private void registerCustomer() {
        String name = view.prompt("Enter customer name: ");
        String phoneNumber = view.prompt("Enter phone number: ");
        String plateNumber = view.prompt("Enter vehicle plate number: ");

        try {
            Customer customer = new Customer(name, phoneNumber, plateNumber);
            Customer savedCustomer = customerDao.save(customer);
            view.showMessage("Customer registered with ID: " + savedCustomer.getId());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void reserveParkingSpot() {
        ArrayList<ParkingSpot> availableSpots = parkingSpotDao.findAllAvailableSpots();
        if (availableSpots.isEmpty()) {
            view.showError("No available parking spots.");
            return;
        }

        view.showParkingSpots(availableSpots);
        try {
            int spotNumber = Integer.parseInt(view.prompt("Enter Spot ID: "));
            Optional<ParkingSpot> optionalSpot = parkingSpotDao.findById(spotNumber);

            if (optionalSpot.isEmpty() || optionalSpot.get().isOccupied()) {
                view.showError("Invalid or occupied spot.");
                return;
            }

            int customerId = Integer.parseInt(view.prompt("Enter Customer ID: "));
            Optional<Customer> optionalCustomer = customerDao.findById(customerId);

            if (optionalCustomer.isEmpty()) {
                view.showError("Invalid customer ID.");
                return;
            }

            int hours = Integer.parseInt(view.prompt("Enter reservation hours: "));
            Reservation reservation = new Reservation(optionalCustomer.get(), optionalSpot.get(), hours);
            reservationDao.save(reservation);
            optionalSpot.get().occupy();
            parkingSpotDao.update(optionalSpot.get());

            view.showMessage("Reservation Created: \n" + reservation);
        } catch (NumberFormatException e) {
            view.showError("Invalid number entered.");
        }
    }

    private void vacateParkingSpot() {
        try {
            int spotNumber = Integer.parseInt(view.prompt("Enter Spot ID to vacate: "));
            Optional<ParkingSpot> optionalSpot = parkingSpotDao.findById(spotNumber);

            if (optionalSpot.isPresent()) {
                ParkingSpot spot = optionalSpot.get();
                if (!spot.isOccupied()) {
                    view.showMessage("Spot is already vacant.");
                    return;
                }
                spot.vacate();
                parkingSpotDao.update(spot);
                view.showMessage("Spot " + spotNumber + " is now vacated.");
            } else {
                view.showError("Invalid Spot ID.");
            }
        } catch (NumberFormatException e) {
            view.showError("Invalid number entered.");
        }
    }
}
