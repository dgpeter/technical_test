package com.BookingTech.spring.service;

import com.BookingTech.spring.models.Option;
import com.BookingTech.spring.models.Ride;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RideService {

    private static final String RESOURCE_URL = "https://techtest.rideways.com/";
    private static String[] apiProviders = {"dave","eric","jeff"};

    @Autowired
    private HTTPRequestClient client;

    private ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<String> getResponseFromRequest(String apiProvider, String pickupCoordinates,
                                                                            String dropffCoordinates){

        ResponseEntity<String> responseEntity = client.get(RESOURCE_URL, apiProvider,
                                                    pickupCoordinates, dropffCoordinates);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity;
            }
        else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            System.out.println("Supplied parameters are wrong for " + apiProvider);
        }
        else if (responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
            System.out.println(apiProvider + " API server sent an Internal Server Error - may be down");
        }
        else
            System.out.println("Request generates internal error");
        return null;
    }
    public Option createPOJOFromJSON(String apiProvider, String pickupCoordinates, String dropffCoordinates){
            ResponseEntity<String> responseEntity = getResponseFromRequest(apiProvider, pickupCoordinates,
                                                                                        dropffCoordinates);
            try{
                if(responseEntity != null) {
                    Option option = mapper.readValue(Objects.requireNonNull(responseEntity.getBody()), Option.class);
                    option.updateDescendingOrder();
                    return option;
                }
                else
                    return  new Option();
            } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    System.out.println("Error was "+ e);
                    return new Option();
                }
        return new Option();
    }

    public List<Option> createPOJOsFromJSON(String pickupCoordinates, String dropoffCoordinates){
        List<Option> options = new ArrayList<>();
        for(String apiProvider : apiProviders)
            options.add(createPOJOFromJSON(apiProvider,pickupCoordinates,dropoffCoordinates));

        return options;
    }


    public List<Ride> ridesWithMinimumPriceFromAllProviders(List<Option> options, int maxNoOfPassengers){
        Map<String, Ride> filteredOptions = new LinkedHashMap<>();
        for(Option option : options) {
            if(option.checkIfEmpty()) {
                option.updateListByNoOfPassengers(maxNoOfPassengers);
                for (Ride ride : option.getOptions()) {
                    ride.setProvider(option.getSupplierID());
                    if (filteredOptions.containsKey(ride.getCarType())) {
                        if (filteredOptions.get(ride.getCarType()).compareTo(ride) < 0) {
                            filteredOptions.put(ride.getCarType(), ride);
                        }
                    } else {
                        filteredOptions.put(ride.getCarType(), ride);
                    }

                }
            }
        }

        List<Ride> filtredRides = new ArrayList<>(filteredOptions.values());
        filtredRides.sort(Comparator.comparing(Ride::getPrice).reversed());

        return filtredRides;

        }


}
