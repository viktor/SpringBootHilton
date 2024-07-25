package com.hilton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ipAddress;

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
