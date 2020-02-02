package com.BookingTech.spring.controllers;

import com.BookingTech.spring.models.Option;
import com.BookingTech.spring.models.Ride;
import com.BookingTech.spring.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OptionsController {

    @Autowired
    RideService rideService;

    @GetMapping("/options/{provider}")
    public ResponseEntity<List<Ride>> option(@PathVariable String provider,
                                            @RequestParam(value = "pickup", required = true) String pickup,
                                            @RequestParam(value = "dropoff", required = true) String dropoff,
                                            @RequestParam(value = "numberOfPassengers", required = false) String numberOfPassengers) {


        int passengers;
        Option option = rideService.createPOJOFromJSON(provider, pickup, dropoff);

        if(numberOfPassengers == null) passengers = 0;
        else option.updateListByNoOfPassengers(Integer.parseInt(numberOfPassengers));

        return ResponseEntity.ok(option.getOptions());
    }

    @GetMapping("/options")
    public ResponseEntity<List<Ride>> option(@RequestParam(value = "pickup", required = true) String pickup,
                             @RequestParam(value = "dropoff", required = true) String dropoff,
                             @RequestParam(value = "numberOfPassengers", required = false) String numberOfPassengers) {


        int passengers;
        List<Option> options = rideService.createPOJOsFromJSON(pickup, dropoff);
        List<Ride> filteredRides;

        if(numberOfPassengers == null) passengers = 0;
        else passengers = Integer.parseInt(numberOfPassengers);

        filteredRides = rideService.ridesWithMinimumPriceFromAllProviders(options,passengers);

        return ResponseEntity.ok(filteredRides);
    }
}
