package com.carlitos.cassandrapoc;

import com.carlitos.cassandrapoc.model.LocationAvailable;
import com.carlitos.cassandrapoc.model.LocationAvailablePK;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LocationService {

    public LocationService(CassandraTemplate cassandraTemplate, CqlTemplate cqlTemplate) {
        this.cassandraTemplate = cassandraTemplate;
        this.cqlTemplate = cqlTemplate;
    }

    private final CassandraTemplate cassandraTemplate;
    private final CqlTemplate cqlTemplate;

    private static final String KEYSPACE = "airfare";
    private static final String COLUMN_FAMILY = "location_by_specialty_brand";

    public void create() {

        String[] locations = getLocations();
        System.out.println("locations total = " + locations.length);

        String[] specialties = getSpecialties();
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

    private static String[] getLocations() {
        return new String[] {"DSE", "DVC", "DPP", "DSB", "DSU", "DGR", "DGV", "DPI", "DMO", "DS1", "DSN",
                "DAU", "DBR", "DMA", "DUP", "DLB", "DIB", "DLP", "DS2", "DOS", "DSS", "DAP", "DTT", "DRJ", "DJS"};
    }

    private static String[] getSpecialties() {
        return new String[] {
                "AN-Testes Funcionais",
                "ES-Densitometria / Mamogr",
                "ES-Medicina Fetal",
                "AN-COLETA",
                "ES-Mapa",
                "ES-Manometria",
                "AN-Genetica",
                "ES-Tomografia",
                "ES-Endoscopia",
                "ES-Ultra-Son. Doppler",
                "ES-Densitometria / Mamografia",
                "AN-Patologia",
                "AN-Consultas",
                "ES-Colonoscopia",
                "ES-Teste Ergometrico",
                "ES-Colposcopia",
                "ES-Peniscopia",
                "ES-Medicina Nuclear",
                "ES-Raio X",
                "ES-Ultra-Sonografia",
                "ES-Mamografia",
                "ES-Ressonancia",
                "AN",
                "AN-Mielograma",
                "ES-Eletrocardiografia",
                "ES-Densitometria Ossea",
                "ES-Cardiologia - (Ecos)",
                "ES-Holter 24h",
                "ES-Vacinas"
        };
    }

    public LocationAvailable getBySpeciality(String speciality) {

        LocationAvailable locationAvailable = cassandraTemplate.select(
                "select * from airfare.location_by_specialty_brand where brand = 'DELBONI' and specialty = '" + speciality + "' ",
                LocationAvailable.class).get(0);

        return locationAvailable;
    }

    public void updateLocation(String speciality, String unit, Boolean avaibility) {
        cqlTemplate.execute("UPDATE airfare.location_by_specialty_brand SET locations[?] = ? where brand = 'DELBONI' and specialty = ?",
                unit, avaibility, speciality);
    }
}
