package com.hilton.controller;

import com.hilton.dto.GeoLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class GeoLocationController {

    @Autowired
    RestTemplate restTemplate;
    private final String _URL = "http://ip-api.com/json/";

    @GetMapping("/geolocation/fromapi/{apiAddress}")
    public GeoLocationDTO fromApi(@PathVariable String apiAddress){
        return restTemplate.exchange(_URL.concat(apiAddress), HttpMethod.GET, null, GeoLocationDTO.class).getBody();
    }

    @GetMapping("/geolocation/fromdb/{apiAddress}")
    public GeoLocationDTO fromDb(@PathVariable String apiAddress){
        return null;
    }

}
