package com.carlitos.cassandrapoc.controller;

import com.carlitos.cassandrapoc.service.LocationService;
import com.carlitos.cassandrapoc.model.LocationAvailable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    private final LocationService locationService;

    @GetMapping("location/generate")
    public void generate(){
        locationService.create();
    }

    @GetMapping("location/speciality/{speciality}")
    public LocationAvailable getBySpeciality(@PathVariable String speciality){
        return locationService.getBySpeciality(speciality);
    }

    @PutMapping("location/{speciality}/{unit}/{avaibility}")
    public void update(@PathVariable String speciality,
                       @PathVariable String unit,
                       @PathVariable Boolean avaibility){
        locationService.updateLocation(speciality, unit, avaibility);

    }

}
