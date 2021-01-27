package com.carlitos.cassandrapoc.controller;

import com.carlitos.cassandrapoc.model.ScheduleAvailableBySpecialityLocationDay;
import com.carlitos.cassandrapoc.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ScheduleController {

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    private final ScheduleService scheduleService;

    @GetMapping("schedule/generate")
    public void generate(){
        scheduleService.create();
    }

    @GetMapping("schedule/{speciality}/{location}/{day}")
    public ScheduleAvailableBySpecialityLocationDay getBySpecLocationDay(
            @PathVariable String speciality,
            @PathVariable String location,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day
            ){
        return scheduleService.findBySpecLocationDay(speciality, location, day);
    }

    @PutMapping("schedule/{speciality}/{location}/{day}/{slot}")
    public void removeSchedule(
            @PathVariable String speciality,
            @PathVariable String location,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
            @PathVariable int slot
    ){
        scheduleService.remove(speciality, location, day, slot);
    }

}
