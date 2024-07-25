package com.hilton.service;

import com.hilton.dto.GeoLocationDTO;
import com.hilton.entity.GeoLocation;
import com.hilton.repository.GeoLocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoLocationService {
    private final String _URL = "http://ip-api.com/json/";

    private @Autowired RestTemplate restTemplate;
    private @Autowired GeoLocationRepository repository;

    public GeoLocationDTO fromApi(String ipAddress){
        return restTemplate.exchange(_URL.concat(ipAddress),
                HttpMethod.GET, null, GeoLocationDTO.class).getBody();
    }

    public GeoLocationDTO fromDb(String ipAddress){
        GeoLocationDTO dto = new GeoLocationDTO();
        GeoLocation geolocation = repository.findByIpAddress(ipAddress);

        if(geolocation == null || geolocation.getId() == null){
            dto = fromApi(ipAddress);
            geolocation = new GeoLocation();
            BeanUtils.copyProperties(dto, geolocation);
            geolocation.setIpAddress(ipAddress);
            geolocation.setId(null);

            repository.save(geolocation);
        }else {
            BeanUtils.copyProperties(geolocation, dto);
        }

        return dto;
    }


}
