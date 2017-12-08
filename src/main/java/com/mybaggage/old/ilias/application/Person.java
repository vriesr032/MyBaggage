/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.old.ilias.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ilias
 */
public class Person {
    //This belongs to CSV Controller
    //===============================
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", null);
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", null);
    private final StringProperty phoneNumber = new SimpleStringProperty(this, "phoneNumber", null);
    private final StringProperty emailAddress = new SimpleStringProperty(this, "emailAddress", null);

    public Person() {
        this(null, null, null,null);
    }

    public Person(String firstName, String lastName, String phoneNumber,String emailAddress) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
        this.emailAddress.set(emailAddress);
        }

    /* firstName Property */
    public final String getFirstName() {//1
    return firstName.get();
    }
    public final void setFirstName(String firstName) {//1
    firstNameProperty().set(firstName);
    }

    public final StringProperty firstNameProperty() {//1
    return firstName;
    }

    public final String getLasttName() {//2
    return lastName.get();
    }
    public final void setLastName(String lastName) {//2
    lastNameProperty().set(lastName);
    }

    public final StringProperty lastNameProperty() {//2
    return lastName;
    }

    public final String getPhoneNumber() {//3
    return phoneNumber.get();
    }
    public final void setPhoneNumber(String phoneNumber) {//3
    phoneNumberProperty().set(phoneNumber);
    }

    public final StringProperty phoneNumberProperty() {//3
    return phoneNumber;
    }

    public final String getEmailAddress() {
    return emailAddress.get();
    }
    public final void setEmailAddress(String emailAddress) {
    emailAddressProperty().set(emailAddress);
    }

    public final StringProperty emailAddressProperty() {
    return emailAddress;
    }
}

