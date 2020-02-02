package com.BookingTech.spring.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ride implements Comparable<Ride>{

    @JsonProperty("car_type")
    private String carType;
    @JsonProperty("price")
    private int price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String provider = null;



    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int maxNumberOfSeats(String carType){
        int maxNumber = -1;
        switch(carType){
            case "STANDARD":
                maxNumber = 4;
                break;
            case "EXECUTIVE":
                maxNumber = 4;
                break;
            case "LUXURY":
                maxNumber = 4;
                break;
            case "PEOPLE_CARRIER":
                maxNumber = 6;
                break;
            case "LUXURY_PEOPLE_CARRIER":
                maxNumber = 6;
                break;
            case "MINIBUS":
                maxNumber = 12;
                break;
        }
        return maxNumber;
    }


    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }


    @Override
    public int compareTo(Ride o) {
        return o.price - this.price;
    }
}
