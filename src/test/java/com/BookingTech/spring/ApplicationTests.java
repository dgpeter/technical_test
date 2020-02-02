package com.BookingTech.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

	private static final String RESOURCE_URL = "https://techtest.rideways.com/";
	private static String[] apiProviders = {"dave","eric","jeff"};

	@Test
	void testGetContextCorrectly() {
		RestTemplate restTemplate = new RestTemplate();

		final String url = RESOURCE_URL + apiProviders[0] + "?pickup=3.410632,-2.157533&dropoff=3.410632,-2.157533";

		ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

		//Verify request succeed
		assertEquals(200, result.getStatusCodeValue());

	}

}
