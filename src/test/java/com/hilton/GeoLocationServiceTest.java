package com.hilton;

import com.hilton.dto.GeoLocationDTO;
import com.hilton.entity.GeoLocation;
import com.hilton.repository.GeoLocationRepository;
import com.hilton.service.GeoLocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class GeoLocationServiceTest extends Mockito {
    private Logger log = LoggerFactory.getLogger(GeoLocationServiceTest.class);
    @Mock
    RestTemplate restTemplate;
    @Mock
    GeoLocationRepository repository;
    @InjectMocks
    GeoLocationService service = new GeoLocationService();

    void setup(){
        MockitoAnnotations.openMocks(this);
        GeoLocation entity = new GeoLocation();
        entity.setIpAddress("1.1.12.123");
        entity.setQuery("1.1.12.123");
        entity.setStatus("success");
        entity.setCountryCode("CN");
        entity.setCountry("China");
        entity.setRegion("GD");
        entity.setRegionName("Guangdong");
        entity.setCity("Guangzhou");
        entity.setZip("510000");
        entity.setTimeZone(null);
        entity.setLon(113.266F);
        entity.setLat(23.1317F);
        entity.setIsp("CHINANET-GD");
        entity.setOrg("Chinanet G");

        GeoLocationDTO dto = new GeoLocationDTO();
        dto.setQuery("1.1.12.123");
        dto.setStatus("success");
        dto.setCountryCode("CN");
        dto.setCountry("China");
        dto.setRegion("GD");
        dto.setRegionName("Guangdong");
        dto.setCity("Guangzhou");
        dto.setZip("510000");
        dto.setTimeZone(null);
        dto.setLon(113.266F);
        dto.setLat(23.1317F);
        dto.setIsp("CHINANET-GD");
        dto.setOrg("Chinanet G");

        ResponseEntity response = mock(ResponseEntity.class);

        when(repository.save(any(GeoLocation.class))).thenReturn(entity);
        when(repository.findByIpAddress(anyString())).thenReturn(entity);
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), nullable(HttpEntity.class), ArgumentMatchers.<Class<GeoLocationDTO>>any())).thenReturn(response);
        when(response.getBody()).thenReturn(dto);
    }

    @Test
    public void fromApi(){
        setup();
        GeoLocationDTO dto = service.fromApi("1.1.12.123");
        Assertions.assertEquals("China", dto.getCountry());
    }

    @Test
    public void fromCachedDbOrApi(){
        setup();
        GeoLocationDTO dto = service.fromCachedDbOrApi("1.1.12.123");
        Assertions.assertEquals("China", dto.getCountry());
    }

}
