package org.pltw.examples.backendlesstest;

/**
 * Created by 20sjobai on 2/2/2018.
 */

public class Person {
    private String lastName;
    private String firstName;
    private double gpa;
    private double height;
    private String objectId;

    public Person(String lastName, String firstName, double gpa, double height) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gpa = gpa;
        this.height = height;
    }

    public Person (){}

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }


}
