package com.hilton.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeoLocationDTO {
    private String query;
    private String status;
    private String countryCode;
    private String country;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private String timeZone;
    private float lon;
    private float lat;
    private String isp;
    private String org;
}
