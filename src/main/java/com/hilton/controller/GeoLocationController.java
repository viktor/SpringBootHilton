package com.hilton.controller;

import com.hilton.dto.GeoLocationDTO;
import com.hilton.service.GeoLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;


@RestController
public class GeoLocationController {
    private Logger log = LoggerFactory.getLogger(GeoLocationController.class);
    private @Autowired GeoLocationService service;
    private String ipRegex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$";

    @GetMapping("/geolocation/{ipAddress}")
    public GeoLocationDTO retrieve(@PathVariable String ipAddress){
        log.info("In /geolocation/{} ", ipAddress);

        if(!ipAddress.matches(ipRegex))
            throw new RuntimeException("Invalid ipAddress");

        return service.get(ipAddress);
    }

    @GetMapping("/geolocation/fromapi/{ipAddress}")
    public GeoLocationDTO fromApi(@PathVariable String ipAddress){
        return service.fromApi(ipAddress);
    }

    @GetMapping("/geolocation/fromdb/{ipAddress}")
    public GeoLocationDTO fromDb(@PathVariable String ipAddress){
        return service.fromCachedDbOrApi(ipAddress);
    }

}
