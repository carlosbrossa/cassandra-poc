package com.carlitos.cassandrapoc.controller;

import com.carlitos.cassandrapoc.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
