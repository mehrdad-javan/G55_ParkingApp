package se.lexicon.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    // Fields
    private Integer id; // 1
    private String name; // CustomerName1
    private String phoneNumber; // 0123456789
    private String vehiclePlateNumber; // ABC-123

    // Constructors
    public Customer(Integer id, String name, String phoneNumber, String vehiclePlateNumber) {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setVehiclePlateNumber(vehiclePlateNumber);
    }

    // Methods (getters, setters, toString and...)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id should no be null.");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name should no be null or empty.");
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty())
            throw new IllegalArgumentException("Phone Number should not be null or empty.");


        final String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) throw new IllegalArgumentException("Invalid Phone Number");
        this.phoneNumber = phoneNumber;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        if (vehiclePlateNumber == null || vehiclePlateNumber.trim().isEmpty())
            throw new IllegalArgumentException("Vehicle Plate Number should no be null or empty.");
        // todo: you can add regex for validating the plate number
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehiclePlateNumber='" + vehiclePlateNumber + '\'' +
                '}';
    }
}
