package com.BookingTech.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class HTTPRequestClient {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> get(String resourceUrl, String apiProvider, String pickupCoordinates,
                                      String dropoffCoordinates) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + apiProvider
                                            + "?pickup=" + pickupCoordinates
                                            + "&dropoff=" + dropoffCoordinates, String.class);
            return response;

        }catch (HttpClientErrorException clientErrorException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Supplied bad parameters");
        }
        catch (HttpServerErrorException serverUnavailableException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceUrl + "/" + apiProvider +
                                                                            "is unavailable");
        }
    }
}
