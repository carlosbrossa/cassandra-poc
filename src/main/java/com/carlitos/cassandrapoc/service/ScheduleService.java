package com.carlitos.cassandrapoc.service;

import com.carlitos.cassandrapoc.model.ScheduleAvailableBySpecialityLocationDay;
import com.carlitos.cassandrapoc.model.ScheduleAvailableBySpecialityLocationDayPK;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScheduleService {

    private final CassandraTemplate cassandraTemplate;
    private final CqlTemplate cqlTemplate;

    public ScheduleService(CassandraTemplate cassandraTemplate, CqlTemplate cqlTemplate) {
        this.cassandraTemplate = cassandraTemplate;
        this.cqlTemplate = cqlTemplate;
    }

    public void create() {
        for(String location : ScheduleUtil.getLocations()) {
            for(String speciality : ScheduleUtil.getSpecialties()) {
                ScheduleAvailableBySpecialityLocationDayPK pk
                        = new ScheduleAvailableBySpecialityLocationDayPK(speciality, location, LocalDate.now(), "DELBONI");

                int totalSlotsByDay = 60; //10 hours with 6 slots per hour (6 * 10)
                LocalDateTime start = LocalDate.now().atTime(8, 0);

                Map<Integer, String> slots = new HashMap<>();

                while (totalSlotsByDay > 0) {
                    start = start.plusMinutes(15);
                    slots.put(totalSlotsByDay, start.getHour() + ":" + start.getMinute());

                    totalSlotsByDay--;
                }

                cassandraTemplate.insert(new ScheduleAvailableBySpecialityLocationDay(pk, slots));
            }
        }

    }

    public ScheduleAvailableBySpecialityLocationDay findBySpecLocationDay(String speciality, String location, LocalDate day) {
        return cassandraTemplate.select(
                "select * from schedule.schedule_by_specialty_location_day " +
                        "where brand = 'DELBONI' and specialty = '"+speciality+"' " +
                        " and location = '"+location+"' and day = '"+day+"'",
                ScheduleAvailableBySpecialityLocationDay.class).get(0);
    }

    public void remove(String specialty, String location, LocalDate day, int slot) {
        cqlTemplate.execute("DELETE slots[?] from schedule.schedule_by_specialty_location_day " +
                        "where brand = 'DELBONI' and specialty = ? and location = ? and day = ?",
                slot, specialty, location, day);
    }
}
