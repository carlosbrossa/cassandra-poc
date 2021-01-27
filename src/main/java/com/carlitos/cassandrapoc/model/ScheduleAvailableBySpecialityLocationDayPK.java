package com.carlitos.cassandrapoc.model;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDate;
import java.time.LocalDateTime;

@PrimaryKeyClass
public class ScheduleAvailableBySpecialityLocationDayPK {

    public ScheduleAvailableBySpecialityLocationDayPK() {
    }

    public ScheduleAvailableBySpecialityLocationDayPK(String specialty, String location, LocalDate day, String brand) {
        this.specialty = specialty;
        this.location = location;
        this.day = day;
        this.brand = brand;
    }

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String specialty;

    @PrimaryKeyColumn
    private String location;

    @PrimaryKeyColumn
    private LocalDate day;

    @PrimaryKeyColumn
    private String brand;

}
