package com.carlitos.cassandrapoc.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Map;

@Table("location_by_specialty_brand")
public class LocationAvailable {

    public LocationAvailable(LocationAvailablePK pk, Map<String, Boolean> locations) {
        this.locationAvailablePK = pk;
        this.locations = locations;
    }

    public LocationAvailable() {
    }

    @PrimaryKey
    private LocationAvailablePK locationAvailablePK;

    @Column
    private Map<String, Boolean> locations;

    public Map<String, Boolean> getLocations() {
        return locations;
    }
}
