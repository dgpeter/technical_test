package com.BookingTech.spring;

import com.BookingTech.spring.controllers.OptionsController;
import com.BookingTech.spring.models.Option;
import com.BookingTech.spring.models.Ride;
import com.BookingTech.spring.service.HTTPRequestClient;
import com.BookingTech.spring.service.RideService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestSuiteForRestApiController {


    private static final String RESOURCE_URL = "https://techtest.rideways.com/";
    private static final String[] API_PROVIDERS = {"dave","eric","jeff"};
    private static final String PICKUP = "3.410632,-2.157533";
    private static final String DROPOFF = "3.410632,-2.157533";


    private OptionsController controller = new OptionsController();


//    @Test
//    public void testSingleProviderRESTAPI() {
//        ResponseEntity result = controller.option(API_PROVIDERS[0],PICKUP, DROPOFF,null);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }


//    @Test
//    @Ignore
//    public void should_return_EmptyRideObject() throws Exception {
//        List<Ride> result = Collections.emptyList();
//        when(rideService.createPOJOFromJSON(API_PROVIDERS[0],PICKUP,DROPOFF)).thenReturn(new Option());
//        mockMvc.perform(get("/options/" + API_PROVIDERS[0] + "?pickup=" + PICKUP + "&dropoff=" + DROPOFF)
//                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(content().json("[]"));
//        fail("TO DO");
//    }

//    @Test
//    @Ignore
//    public void should_return_OrderedObjects() throws Exception {
//
//        Ride ride1 = new Ride();
//        ride1.setProvider("DAVE");
//        ride1.setPrice(1);
//        ride1.setCarType("STANDARD");
//        Ride ride2 = new Ride();
//        ride2.setProvider("ERIC");
//        ride2.setPrice(3);
//        ride2.setCarType("LUXURY");
//
//        Option filter = new Option();
//        filter.getOptions().add(ride1);
//        filter.getOptions().add(ride2);
//
//        List<Option> filteredList = new ArrayList<>();
//        filteredList.add(filter);
//
//        when(rideService.ridesWithMinimumPriceFromAllProviders(filteredList, 5))
//                .thenReturn(filteredList);
//        List<Ride> result = Collections.emptyList();
//        when(rideService.createPOJOFromJSON(API_PROVIDERS[0],PICKUP,DROPOFF).getOptions()).thenReturn(result);
//        mockMvc.perform(get("/options/" + API_PROVIDERS[0] + "?pickup=" + PICKUP + "&dropoff=" + DROPOFF)
//                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(content().json("[]"));
//        fail("TO DO");
//    }
}
