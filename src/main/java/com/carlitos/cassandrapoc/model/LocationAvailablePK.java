package com.carlitos.cassandrapoc.model;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class LocationAvailablePK {

    public LocationAvailablePK() {
    }

    public LocationAvailablePK(String specialty, String brand) {
        this.specialty = specialty;
        this.brand = brand;
    }

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String specialty;

    @PrimaryKeyColumn(ordinal = 0, ordering = Ordering.DESCENDING)
    private String brand;

}
