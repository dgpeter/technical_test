package com.BookingTech.spring;

import com.BookingTech.spring.models.Option;
import com.BookingTech.spring.models.Ride;
import com.BookingTech.spring.service.HTTPRequestClient;
import com.BookingTech.spring.service.RideService;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class TestSuiteForService {

    private RideService rideService = new RideService();
    private HTTPRequestClient client = new HTTPRequestClient();

    private final static String PICK_UP_COORDINATES = "3.410632,-2.157533";
    private final static String DROP_OFF_COORDINATES = "3.410632,-2.157533";

    @Test
    public void testInternalServerError(){

    }

    @Test
    public void testBadRequestError(){

    }

    @Test
    public void testNoPassengersSingleProviderSorted(){
        Option option = new Option();

        constructOptionResultForSingleProvider(option);

        option.updateDescendingOrder();

        List<Ride> rides = option.getOptions();


        //check if sorted
        assertTrue(!rides.isEmpty());
        assertEquals(2, rides.size());
        assertEquals("MINIBUS", rides.get(0).getCarType());
        assertEquals("LUXURY", rides.get(1).getCarType());
    }



    @Test
    public void testSetPassengersSingleProviderSorted(){

        Option option = new Option();

        constructOptionResultForSingleProvider(option);

        System.out.println(option.getOptions().get(0).getCarType());

        int numbersOfPassengers = 5;

        option.updateListByNoOfPassengers(numbersOfPassengers);

        List<Ride> rides = option.getOptions();


        //check if sorted
        assertTrue(!rides.isEmpty());
        assertEquals(1, rides.size());
        assertEquals("MINIBUS", rides.get(0).getCarType());
    }

    @Test
    public void testNoPassengersAllProviders(){
        List<Option> options = new ArrayList<>();
        constructOptionResultForMultipleProviders(options);

        List<Ride> rides = new ArrayList<>();
        rides = rideService.ridesWithMinimumPriceFromAllProviders(options,0);

//        rides.forEach(ride ->
//                System.out.println(ride.getCarType() + ": " + ride.getProvider() + " - "
//                        + ride.getPrice()));

        assertTrue(!rides.isEmpty());
        assertEquals(4, rides.size());
        assertEquals("LUXURY_PEOPLE_CARRIER", rides.get(0).getCarType());
        assertEquals(317918, rides.get(0).getPrice());
        assertEquals("LUXURY", rides.get(1).getCarType());
        assertEquals(160015, rides.get(1).getPrice());
        assertEquals("STANDARD", rides.get(2).getCarType());
        assertEquals(160015, rides.get(2).getPrice());
        assertEquals("MINIBUS", rides.get(3).getCarType());
        assertEquals(2, rides.get(3).getPrice());
    }

    @Test
    public void testSetPassengersAllProvidersWithMinimumPrice(){
        List<Option> options = new ArrayList<>();
        constructOptionResultForMultipleProviders(options);

        List<Ride> rides = new ArrayList<>();
        int noOfPassengers = 5;

        rides = rideService.ridesWithMinimumPriceFromAllProviders(options,5);

//        rides.forEach(ride ->
//                System.out.println(ride.getCarType() + ": " + ride.getProvider() + " - "
//                        + ride.getPrice()));

        assertTrue(!rides.isEmpty());
        assertEquals(4, rides.size());
        assertEquals("LUXURY_PEOPLE_CARRIER", rides.get(0).getCarType());
        assertEquals(317918, rides.get(0).getPrice());
        assertEquals("MINIBUS", rides.get(1).getCarType());
        assertEquals(2, rides.get(1).getPrice());
    }

    private void constructOptionResultForSingleProvider(Option option){
        option.setSupplierID("DAVE");
        option.setPickUp("3.410632,-2.157533");
        option.setDropOff("3.410632,-2.157533");
        List<Ride> rides = new ArrayList<Ride>();

        Ride ride1 = new Ride();
        ride1.setCarType("LUXURY");
        ride1.setPrice(160015);

        Ride ride2 = new Ride();
        ride2.setCarType("MINIBUS");
        ride2.setPrice(317918);


        rides.add(ride1);
        rides.add(ride2);

        option.setOptions(rides);

    }

    private void constructOptionResultForMultipleProviders(List<Option> options){
        Option daveOption = new Option();
        Option ericOption = new Option();
        Option jeffOption = new Option();

        daveOption.setSupplierID("DAVE");
        daveOption.setPickUp("3.410632,-2.157533");
        daveOption.setDropOff("3.410632,-2.157533");
        List<Ride> ridesDave = new ArrayList<Ride>();

        Ride rideDave1 = new Ride();
        rideDave1.setCarType("LUXURY");
        rideDave1.setPrice(160015);

        Ride rideDave2 = new Ride();
        rideDave2.setCarType("MINIBUS");
        rideDave2.setPrice(317918);


        ridesDave.add(rideDave1);
        ridesDave.add(rideDave2);

        daveOption.setOptions(ridesDave);

        options.add(daveOption);

        jeffOption.setSupplierID("JEFF");
        jeffOption.setPickUp("3.410632,-2.157533");
        jeffOption.setDropOff("3.410632,-2.157533");
        List<Ride> ridesJeff = new ArrayList<Ride>();

        Ride rideJeff1 = new Ride();
        rideJeff1.setCarType("STANDARD");
        rideJeff1.setPrice(160015);

        Ride rideJeff2 = new Ride();
        rideJeff2.setCarType("LUXURY_PEOPLE_CARRIER");
        rideJeff2.setPrice(317918);


        Ride rideJeff3 = new Ride();
        rideJeff3.setCarType("MINIBUS");
        rideJeff3.setPrice(2);


        ridesJeff.add(rideJeff1);
        ridesJeff.add(rideJeff2);
        ridesJeff.add(rideJeff3);

        jeffOption.setOptions(ridesJeff);

        options.add(jeffOption);

        ericOption.setSupplierID("ERIC");
        ericOption.setPickUp("3.410632,-2.157533");
        ericOption.setDropOff("3.410632,-2.157533");
        List<Ride> ridesEric = new ArrayList<Ride>();

        Ride rideEric1 = new Ride();
        rideEric1.setCarType("STANDARD");
        rideEric1.setPrice(200000);

        ridesEric.add(rideEric1);

        ericOption.setOptions(ridesEric);

        options.add(ericOption);

    }
}
