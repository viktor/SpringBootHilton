package com.hilton.service;

import com.hilton.controller.GeoLocationController;
import com.hilton.dto.GeoLocationDTO;
import com.hilton.entity.GeoLocation;
import com.hilton.repository.GeoLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class GeoLocationService {
    private final String _URL = "http://ip-api.com/json/";
    private Logger log = LoggerFactory.getLogger(GeoLocationController.class);

    private @Autowired RestTemplate restTemplate;
    private @Autowired GeoLocationRepository repository;

    public GeoLocationDTO get(String ipAddress) {
        log.info("In get() {} ", ipAddress);
        return fromCachedDbOrApi(ipAddress);
    }

    public GeoLocationDTO fromApi(String ipAddress){
        log.info("fromApi() {}", ipAddress);
        return restTemplate.exchange(_URL.concat(ipAddress),
                HttpMethod.GET, null, GeoLocationDTO.class).getBody();
    }

    public GeoLocationDTO fromCachedDbOrApi(String ipAddress){
        log.info("In fromDbOrApi() {} ", ipAddress);
        GeoLocationDTO dto = new GeoLocationDTO();
        GeoLocation geolocation = repository.findByIpAddress(ipAddress);

        if(geolocation == null || (geolocation != null && isRecordOlderThan5min(geolocation))){
            dto = fromApi(ipAddress);
            geolocation = new GeoLocation();
            BeanUtils.copyProperties(dto, geolocation);
            geolocation.setIpAddress(ipAddress);
            geolocation.setId(null);
            geolocation.setSavedTimeStamp(LocalDateTime.now());

            log.info("Saving new object in db {} ", geolocation);
            repository.save(geolocation);
        }else {
            log.info("parsing found record in db/cache {} ", geolocation);
            BeanUtils.copyProperties(geolocation, dto);
        }

        return dto;
    }

    public boolean isRecordOlderThan5min(GeoLocation geoLocation){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime savedTime = geoLocation.getSavedTimeStamp();

        long seconds = ChronoUnit.SECONDS.between(savedTime, now);
        log.info("The seconds between saved geolocation object and now is : {}", seconds);
        return seconds > 300;
    }


}
