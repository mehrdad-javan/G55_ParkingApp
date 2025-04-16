package se.lexicon.dao.impl;

import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private final List<Reservation> inMemoryStorage = new ArrayList<>(); // In-memory storage

    @Override
    public void save(Reservation reservation) {
        inMemoryStorage.add(reservation);
    }

    @Override
    public Optional<Reservation> findById(String reservationId) {
        for (Reservation reservation : inMemoryStorage) {
            if (reservation.getReservationId().equals(reservationId)) {
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Reservation reservation) {
        if (reservation == null || reservation.getReservationId() == null) {
            throw new IllegalArgumentException("Reservation ID should not be null.");
        }

        for (int i = 0; i < inMemoryStorage.size(); i++) {
            if (inMemoryStorage.get(i).getReservationId().equals(reservation.getReservationId())) {
                inMemoryStorage.set(i, reservation); // Update the reservation in place
                return;
            }
        }

        throw new IllegalArgumentException("Reservation not found: " + reservation.getReservationId());
    }

}
