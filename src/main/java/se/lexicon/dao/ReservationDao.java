package se.lexicon.dao;

import se.lexicon.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationDao {

    void save(Reservation reservation);

    Optional<Reservation> findById(String id);

    void update(Reservation reservation);
}
