package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.logging.ApplicationLogger;
import com.example.demo.model.GoogleApiProperties;

@RestController("/gapi")
public class GoogleApiController {

	@Autowired
	private GoogleApiProperties googleApiProperties;
	@Autowired
	private ApplicationLogger appLogger;

	@GetMapping
	public ResponseEntity<String> getLatLong(@RequestParam("address") final String address) {
		appLogger.logInfo(this.getClass() + "called for /gapi api endpoint");
		appLogger.logDebug("URL for google api " + getResourceUrl(googleApiProperties, address));
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(getResourceUrl(googleApiProperties, address),
				String.class);
		return response;
	}

	/*
	 * https://maps.googleapis.com/maps/api/geocode/json?
	 * address=1600+Amphitheatre+Parkway,+Mountain+View,+CA &
	 * key=AIzaSyCF8-Kzw6JZS_Wppm9g-Fxy64PUMa6hGjg
	 * 
	 */
	public static String getResourceUrl(GoogleApiProperties apiProperties, String address) {
		String googleApiResourceUrl = apiProperties.getGeolocationApiUrl()
				+ apiProperties.getGeolocationAddressParamKey() + "=" + address + "&"
				+ apiProperties.getGeolocationKeyParamKey() + "=" + apiProperties.getGeolocationKeyParamValue();
		return googleApiResourceUrl;
	}
}
