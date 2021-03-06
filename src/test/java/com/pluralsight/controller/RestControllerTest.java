package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {
    @Test(timeout=3000)
    public void testCreateRide() {
        Ride ride = new Ride();

        ride.setName("Round Valley Ride");
        ride.setDuration(35);

        RestTemplate restTemplate = new RestTemplate();
        ride = restTemplate.postForObject("http://localhost:8080/rides", ride, Ride.class);

        System.out.println("Ride : " + ride);

    }

	@Test(timeout=10000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
}
