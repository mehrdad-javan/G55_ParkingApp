package se.lexicon.dao.impl;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private ArrayList<ParkingSpot> inMemoryStorage = new ArrayList<>();

    @Override
    public ParkingSpot save(ParkingSpot parkingSpot) {
        // todo needs completion
        return null;
    }

    @Override
    public ArrayList<ParkingSpot> findAll() {
        // todo needs completion
        return null;
    }

    @Override
    public ArrayList<ParkingSpot> findAllAvailableSpots() {
        // todo needs completion
        return null;
    }

    @Override
    public Optional<ParkingSpot> findById(Integer id) {
        // todo needs completion
        return Optional.empty();
    }

    @Override
    public void update(ParkingSpot parkingSpot) {
        // todo needs completion

    }

    @Override
    public void delete(Integer id) {
        // todo needs completion

    }
}
