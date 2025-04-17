package se.lexicon.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDaoImplTest {


    @Test
    @DisplayName("Should save a customer successfully and assign an ID")
    void saveCustomerSuccessfully() {
        // Scenario: Create an object of the Customer class
        Customer customerData = new Customer("Test Name", "0123456789", "ABC123");

        // Expected:
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Customer createdCustomer = customerDao.save(customerData);

        // Verify: Check that ID is not null and created customer matches the original input
        assertNotNull(createdCustomer.getId());
        assertEquals(customerData, createdCustomer);
    }

    @Test
    @DisplayName("Should find a customer by ID when the customer exists")
    void findCustomerByIdReturnsWhenExists() {
        // Scenario
        Customer customerData = new Customer("Test Name", "0123456789", "ABC123");
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Customer createdCustomer = customerDao.save(customerData);

        // Expected
        Optional<Customer> optionalCustomer = customerDao.findById(createdCustomer.getId()); // 1001

        // Verify
        assertTrue(optionalCustomer.isPresent());
        assertEquals(createdCustomer, optionalCustomer.get());

    }


    @Test
    @DisplayName("Should return empty when no customer exists for the given ID")
    void findCustomerByIdReturnsEmptyWhenNotExists() {
        // Scenario
        int nonExistentId = 999;

        // Expected
        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        Optional<Customer> foundOptionalCustomer = customerDao.findById(nonExistentId); // 999

        // Verify
        assertFalse(foundOptionalCustomer.isPresent());
        // assertTrue(foundOptionalCustomer.isEmpty());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for invalid phone number")
    void saveCustomerWithInvalidPhoneNumberThrowsException() {
        // todo: needs completion :)
    }


}
