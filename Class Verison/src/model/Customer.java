package model;

import java.io.Serializable;

public class Customer implements Serializable, Comparable<Customer> {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String secondName;
    private int noOfBurgerRequired;

    // Constructor to initialize a Customer object
    public Customer(String firstName, String secondName, int noOfBurgerRequired) {
        this.firstName = firstName;
        this.secondName = secondName;
        // Limit the number of burgers required to a maximum of 5
        this.noOfBurgerRequired = Math.min(noOfBurgerRequired, 5);

    }
    // Default constructor
    public Customer() {

    }
    // Getter for the first name of the customer
    public String getFirstName() {
        return firstName;
    }

    // Getter for the last name of the customer
    public String getSecondName() {
        return secondName;
    }

    // Getter for the number of burgers required by the customer
    public int getNoOfBurgerRequired() {
        return noOfBurgerRequired;
    }

    @Override
    public int compareTo(Customer other) {
        // Compare first names first
        int firstNameComparison = this.firstName.compareTo(other.firstName);
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        // If first names are equal, compare last names
        return this.secondName.compareTo(other.secondName);
    }
}
