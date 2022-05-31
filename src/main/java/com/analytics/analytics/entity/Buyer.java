package com.analytics.analytics.entity;

public class Buyer extends User {


    public Buyer(String firstName, String lastName, String dateOfBirth) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDateOfBirth(dateOfBirth);
    }
}
