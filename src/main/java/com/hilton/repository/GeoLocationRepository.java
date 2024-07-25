package com.hilton.repository;

import com.hilton.entity.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoLocationRepository extends JpaRepository<GeoLocation, Integer> {

    public GeoLocation findByIpAddress(String ipAddress);

}
