package com.packt.domain;

public class Customer {

    String customerId;
    String name;
    String address;
    boolean noOfOrderMade;

    public Customer(String customerId, String name, String address, boolean noOfOrderMade){
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.noOfOrderMade = noOfOrderMade;
    }

    //Getters and Setters

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isNoOfOrderMade() {
        return noOfOrderMade;
    }

    public void setNoOfOrderMade(boolean noOfOrderMade) {
        this.noOfOrderMade = noOfOrderMade;
    }
}
