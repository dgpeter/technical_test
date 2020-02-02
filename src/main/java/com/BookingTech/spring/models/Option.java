package com.BookingTech.spring.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;

public class Option {
    @JsonProperty("supplier_id")
    private String supplierID;
    @JsonProperty("pickup")
    private String pickUp;
    @JsonProperty("dropoff")
    private String dropOff;
    @JsonProperty("options")
    private List<Ride> options;

    public String getSupplierID() {
        return supplierID;
    }

    public String getPickUp() {
        return pickUp;
    }

    public String getDropOff() {
        return dropOff;
    }

    public List<Ride> getOptions() {
        return options;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setPickUp(String pickUp) {
        this.pickUp = pickUp;
    }

    public void setDropOff(String dropOff) {
        this.dropOff = dropOff;
    }

    public void setOptions(List<Ride> rides) {
        this.options = rides;
    }

    public boolean checkIfEmpty(){
        return supplierID != null && pickUp != null && dropOff != null;
    }
    public void updateDescendingOrder(){
        this.getOptions().sort(Comparator.comparing(Ride::getPrice).reversed());
    }
    public void updateListByNoOfPassengers(int noOfPassengers) {
            for(int i = 0; i < this.getOptions().size(); i++) {
               // System.out.println(this.getOptions().get(i).getCarType());
                if (this.getOptions().get(i).maxNumberOfSeats(this.getOptions().get(i).getCarType()) < noOfPassengers)
                {
                    this.getOptions().remove(i);
                    i--;
                }
            }
    }
}
