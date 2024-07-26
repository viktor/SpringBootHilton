package com.hilton;

import com.hilton.controller.GeoLocationController;
import com.hilton.dto.GeoLocationDTO;
import com.hilton.service.GeoLocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeoLocationControllerTest extends Mockito {
    private Logger log = LoggerFactory.getLogger(GeoLocationControllerTest.class);
    @Mock
    GeoLocationService service;
    @InjectMocks
    GeoLocationController controller = new GeoLocationController();

    void setup(){
        MockitoAnnotations.openMocks(this);
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

        when(service.fromApi(anyString())).thenReturn(dto);
        when(service.fromCachedDbOrApi(anyString())).thenReturn(dto);
        when(service.get(anyString())).thenReturn(dto);
    }

    @Test
    public void fromApi(){
        setup();
        GeoLocationDTO dto = controller.fromApi("1.1.12.123");
        Assertions.assertEquals("China", dto.getCountry());
    }

}
