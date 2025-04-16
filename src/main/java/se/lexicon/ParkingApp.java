package se.lexicon;

import se.lexicon.dao.ReservationDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ParkingApp {

    ParkingSpotDaoImpl parkingSpotDao;
    CustomerDaoImpl customerDao;
    ReservationDao reservationDao;

    public ParkingApp() {
        parkingSpotDao = new ParkingSpotDaoImpl();
        customerDao = new CustomerDaoImpl();
        reservationDao = new ReservationDaoImpl();
    }

    public static void main(String[] args) {
        ParkingApp parkingApp = new ParkingApp();
        parkingApp.runApp();
    }

    public void runApp() {

        // create 10 spots and save them to inMemoryStorage
        for (int i = 0; i < 10; i++) {
            ParkingSpot spot = new ParkingSpot(i, 101);
            parkingSpotDao.save(spot);
        }

        boolean running = true;
        while (running) {
            System.out.println("Welcome to my App");
            System.out.println("1. Register Customer");
            System.out.println("2. Display Parking Spots");
            System.out.println("3. Reserved a Parking Spot");
            System.out.println("4. Vacate Parking Spot");
            System.out.println("5. Exit");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    displayParkingSpots();
                    break;
                case "3":
                    reserveParkingSpot();
                    break;
                case "4":
                    vacateParkingSpot();
                    break;
                case "5":
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid option. Try again." + ConsoleColors.RESET);

            }
        }

    }

    private void registerCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer name:");
        String name = scanner.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter vehicle plat number:");
        String platNumber = scanner.nextLine();

        Customer customerData = new Customer(name, phoneNumber, platNumber);

        Customer createdCustomer = customerDao.save(customerData);
        System.out.println(ConsoleColors.GREEN + "Customer registered with ID: " + createdCustomer.getId() + ConsoleColors.RESET);
    }

    private void displayParkingSpots() {
        ArrayList<ParkingSpot> parkingSpots = parkingSpotDao.findAll();
        System.out.println("===== Parking Spots =====");
        for (ParkingSpot parkingSpot : parkingSpots) {
            /*String status = "";
            if(parkingSpot.isOccupied()){
                status = "X";
            } else {
                status = "✓";
            }*/
            String status = parkingSpot.isOccupied() ? "X" : "✓";
            System.out.println("Spot Id: " + parkingSpot.getSpotNumber() + " | Area: " + parkingSpot.getAreaCode() + " | Status: " + status);
        }
        System.out.println("=========================");

    }

    private void reserveParkingSpot() {
        Scanner scanner = new Scanner(System.in);
        // display all available spots
        ArrayList<ParkingSpot> parkingSpots = parkingSpotDao.findAllAvailableSpots();
        if (parkingSpots.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No Available parking spots." + ConsoleColors.RESET);
            return;
        }

        displayParkingSpots();

        // enter the spot id
        System.out.println("Enter Spot ID: ");
        String spotNumber = scanner.nextLine();
        // verify the spot id if it is valid continue
        Optional<ParkingSpot> optionalParkingSpot = parkingSpotDao.findById(Integer.parseInt(spotNumber));
        if (optionalParkingSpot.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Invalid Spot ID." + ConsoleColors.RESET);
            return;
        }
        if (optionalParkingSpot.get().isOccupied()) {
            System.out.println(ConsoleColors.RED + "Spot ID is Occupied." + ConsoleColors.RESET);
            return;
        }

        // enter the customer id
        System.out.println("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        // verify the customer id if it is valid continue
        Optional<Customer> optionalCustomer = customerDao.findById(Integer.parseInt(customerId));
        if (optionalCustomer.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Invalid Customer ID." + ConsoleColors.RESET);
            return;
        }

        // enter hour number
        System.out.println("Enter reservation hours: ");
        String hours = scanner.nextLine();

        // Create Reservation object
        Reservation reservation = new Reservation(optionalCustomer.get(), optionalParkingSpot.get(), Integer.parseInt(hours));
        // save to storage
        reservationDao.save(reservation);
        optionalParkingSpot.get().occupy(); // to change the isOccupied to true
        parkingSpotDao.update(optionalParkingSpot.get());

        // display reservation id
        System.out.println(ConsoleColors.GREEN + "Reservation Created: " + reservation + ConsoleColors.RESET);

    }

    private void vacateParkingSpot() {
        // todo: needs completion
    }

}
