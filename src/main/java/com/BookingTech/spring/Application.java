package com.BookingTech.spring;

import com.BookingTech.spring.models.Option;
import com.BookingTech.spring.models.Ride;
import com.BookingTech.spring.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;


@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private RideService rideService;

    private static final String[] API_PROVIDERS = {"dave","eric","jeff"};

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        if (args.length == 0)
            System.out.println("Application running - can make calls to RESTapi");
    }

    @Override
    public void run(String... args) {
        if(args.length == 0)
            return;
        if(args.length == 1)
            System.out.println("Supply the dropoff and pickup coordinates and/or number of passengers");
        else {
            try {

                //I will assume that the coordinates are correctly supplied because they will be probably given
                //by the device

                String pickupCoordinates = args[0];
                String dropoffCoordinates = args[1];

                int numberOfPassengers = 0;

                if(args[2].toLowerCase().equals("provider")){
                    String singleProvider = args[3].toLowerCase();
                    List<String> validAPIs = Arrays.asList(API_PROVIDERS);
                    if(!validAPIs.contains(singleProvider)){
                        System.out.println("Provide an existing provider");
                    }
                    else{
                        if(numberOfSeatsIsValid(args[4]))
                            numberOfPassengers = Integer.parseInt(args[4]);

                        Option option = rideService.createPOJOFromJSON(singleProvider,pickupCoordinates,
                                dropoffCoordinates);

                        option.updateListByNoOfPassengers(numberOfPassengers);

                        option.getOptions().forEach(ride ->
                                System.out.println(ride.getCarType() + " - " + ride.getPrice()));

                    }
                }
                else{
                    if(Integer.parseInt(args[2]) != 0)
                        numberOfPassengers = Integer.parseInt(args[2]);
                    List<Option> options;
                    List<Ride> filteredRides;
                    options = rideService.createPOJOsFromJSON(pickupCoordinates,dropoffCoordinates);
                    filteredRides = rideService.ridesWithMinimumPriceFromAllProviders(options, numberOfPassengers);

                    filteredRides.forEach(ride ->
                            System.out.println(ride.getCarType() + ": " + ride.getProvider() + " - "
                                    + ride.getPrice()));
                }

            }catch(Exception e){
                System.err.println("Exception was: " + e);
            }
        }
    }

    private static boolean numberOfSeatsIsValid(String numberOfSeats){
        return numberOfSeats != null && Integer.parseInt(numberOfSeats) > 0;
    }
}
