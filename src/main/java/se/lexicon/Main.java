package se.lexicon;

import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "CustomerName1","0123456789", "ABC-123");
        ParkingSpot spot1 = new ParkingSpot(1, 101);
        ParkingSpot spot2 = new ParkingSpot(3, 101);
        System.out.println("spot1 = " + spot1);
        System.out.println("spot2 = " + spot2);
        System.out.println("------------------");
        Reservation reservation1 = new Reservation(customer1, spot1, 2);
        spot1.occupy();

        System.out.println("reservation1 = " + reservation1);
        System.out.println("---------------------");
        System.out.println("spot1 = " + spot1);
        System.out.println("spot2 = " + spot2);


    }
}