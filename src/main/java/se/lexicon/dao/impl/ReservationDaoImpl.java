package se.lexicon.dao.impl;

import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private ArrayList<Reservation> inMemoryStorage = new ArrayList<>();
    @Override
    public void save(Reservation reservation) {
        // todo needs completion
    }

    @Override
    public Optional<Reservation> findById(String id) {
        // todo needs completion
        return Optional.empty();
    }

    @Override
    public void update(Reservation reservation) {
        // todo needs completion
    }
}
