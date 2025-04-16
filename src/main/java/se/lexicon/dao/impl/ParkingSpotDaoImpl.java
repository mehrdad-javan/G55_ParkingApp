package se.lexicon.dao.impl;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private final List<ParkingSpot> inMemoryStorage = new ArrayList<>(); // In-memory storage for parking spots

    @Override
    public ParkingSpot save(ParkingSpot parkingSpot) {
        inMemoryStorage.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public ArrayList<ParkingSpot> findAll() {
        return new ArrayList<>(inMemoryStorage); // Return a copy to avoid modification of the original list
    }

    @Override
    public ArrayList<ParkingSpot> findAllAvailableSpots() {
        // Filter for vacant spots
        ArrayList<ParkingSpot> list = new ArrayList<>();
        for (ParkingSpot spot : inMemoryStorage) {
            if (!spot.isOccupied()) {
                list.add(spot);
            }
        }
        return list;
    }


    @Override
    public Optional<ParkingSpot> findById(Integer spotNumber) {
        for (ParkingSpot spot : inMemoryStorage) {
            if (spot.getSpotNumber().equals(spotNumber)) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }


    @Override
    public void update(ParkingSpot parkingSpot) {
        // Validate input
        if (parkingSpot == null || parkingSpot.getSpotNumber() == null) {
            throw new IllegalArgumentException("Invalid parking spot: spotNumber cannot be null.");
        }

        // Find the existing parking spot
        Optional<ParkingSpot> existingSpot = findById(parkingSpot.getSpotNumber());
        if (existingSpot.isPresent()) {
            // Update the existing parking spot
            int index = inMemoryStorage.indexOf(existingSpot.get());
            inMemoryStorage.set(index, parkingSpot);
        } else {
            throw new IllegalArgumentException("Parking spot not found: " + parkingSpot.getSpotNumber());
        }
    }

    @Override
    public void delete(Integer spotNumber) {
        if (spotNumber == null) {
            throw new IllegalArgumentException("Spot number cannot be null.");
        }

        boolean isFind = false;
        for (ParkingSpot spot : inMemoryStorage) {
            if (spot.getSpotNumber().equals(spotNumber)) {
                inMemoryStorage.remove(spot);
                isFind = true;
                break; // Successfully deleted
            }
        }
        if (!isFind) {
            throw new IllegalArgumentException("In valid Spot Number");
        }

    }

}
