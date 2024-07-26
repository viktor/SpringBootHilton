package com.hilton.repository;

import com.hilton.entity.GeoLocation;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoLocationRepository extends JpaRepository<GeoLocation, Integer> {

    @Cacheable(value = "geoLocationsCache", key = "#ipAddress")
    public GeoLocation findByIpAddress(String ipAddress);

    @CachePut(value = "geoLocationsCache", key = "#geoLocation.ipAddress")
    public GeoLocation save(GeoLocation geoLocation);

}
