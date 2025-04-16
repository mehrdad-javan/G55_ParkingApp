package se.lexicon.dao;

import se.lexicon.model.Customer;

import java.util.Optional;

public interface CustomerDao {
    Customer save(Customer customer);

    Optional<Customer> findById(Integer id);
}
