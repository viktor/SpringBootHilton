package com.hilton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ipAddress;
    private LocalDateTime savedTimeStamp;

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
