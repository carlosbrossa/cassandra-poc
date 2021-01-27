package com.carlitos.cassandrapoc.service;

import com.carlitos.cassandrapoc.model.LocationAvailable;
import com.carlitos.cassandrapoc.model.LocationAvailablePK;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LocationService{

    public LocationService(CassandraTemplate cassandraTemplate, CqlTemplate cqlTemplate) {
        this.cassandraTemplate = cassandraTemplate;
        this.cqlTemplate = cqlTemplate;
    }

    private final CassandraTemplate cassandraTemplate;
    private final CqlTemplate cqlTemplate;

    public void create() {

        String[] locations = ScheduleUtil.getLocations();
        System.out.println("locations total = " + locations.length);

        String[] specialties = ScheduleUtil.getSpecialties();
        System.out.println("specialties total = " + specialties.length);

        for (String specialty : specialties){
            LocationAvailablePK pk = new LocationAvailablePK(specialty, "DELBONI");
            HashMap<String, Boolean> locationsAvaibility = new HashMap<>();
            for (String location : locations){
                locationsAvaibility.put(location, true);
            }
            LocationAvailable locationAvailable = new LocationAvailable(pk, locationsAvaibility);
            cassandraTemplate.insert(locationAvailable);
        }
/*
        List<LocationAvailable> avaibilities = cassandraTemplate.select(QueryBuilder.selectFrom(KEYSPACE, COLUMN_FAMILY).all().build(),
                LocationAvailable.class);
        System.out.println(avaibilities);*/
    }

    public LocationAvailable getBySpeciality(String speciality) {

        LocationAvailable locationAvailable = cassandraTemplate.select(
                "select * from schedule.location_by_specialty_brand where brand = 'DELBONI' and specialty = '" + speciality + "' ",
                LocationAvailable.class).get(0);

        return locationAvailable;
    }

    public void updateLocation(String speciality, String unit, Boolean avaibility) {
        cqlTemplate.execute("UPDATE schedule.location_by_specialty_brand SET locations[?] = ? where brand = 'DELBONI' and specialty = ?",
                unit, avaibility, speciality);
    }
}
