package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private List<String> addresses = new ArrayList<>();

    public Contact() {

    }

    public Contact(String name) {
        this.name = name;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean AddressTaken(String address){
        for (String addressname : addresses) {
            if (addressname.equals(address)){
                return true;
            }
        }
        return false;
    }

    public void AddAddress(String address){
        addresses.add(address);
    }

    public void RemoveAddress(String address){
        int size = addresses.size();
        for (int i = 0; i < size; i++) {
            if(addresses.get(i).equals(address)){
                addresses.remove(i);
                return;
            }
        }
    }

}
