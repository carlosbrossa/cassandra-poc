package com.carlitos.cassandrapoc.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Map;

@Table("schedule_by_specialty_location_day")
public class ScheduleAvailableBySpecialityLocationDay {

    public ScheduleAvailableBySpecialityLocationDay(ScheduleAvailableBySpecialityLocationDayPK pk, Map<Integer, String> slots) {
        this.pk = pk;
        this.slots = slots;
    }

    public ScheduleAvailableBySpecialityLocationDay() {
    }

    @PrimaryKey
    private ScheduleAvailableBySpecialityLocationDayPK pk;

    @Column
    private Map<Integer, String> slots;

    public Map<Integer, String> getSlots() {
        return slots;
    }

    public ScheduleAvailableBySpecialityLocationDayPK getPk() {
        return pk;
    }
}
