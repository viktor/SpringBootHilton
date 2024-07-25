package com.hilton.controller;

import com.hilton.dto.GeoLocationDTO;
import com.hilton.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class GeoLocationController {
    private @Autowired GeoLocationService service;

    @GetMapping("/geolocation/fromapi/{ipAddress}")
    public GeoLocationDTO fromApi(@PathVariable String ipAddress){
        return service.fromApi(ipAddress);
    }

    @GetMapping("/geolocation/fromdb/{ipAddress}")
    public GeoLocationDTO fromDb(@PathVariable String ipAddress){
        return service.fromDb(ipAddress);
    }

}
