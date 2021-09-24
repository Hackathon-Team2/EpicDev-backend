package org.hackathon.apis.controller;

import org.hackathon.apis.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/initializeDate")
    public String initializeDate() {
        LocalDateTime actualLifeDate = timeService.initializeLifeDate();
        return "OK DATE INIT : " + timeService.getLocalDate(actualLifeDate);
    }

    @GetMapping("/newDay")
    public String newDay(LocalDateTime actualLifeDate) {
        timeService.newDay(actualLifeDate);
        return "OK Nouvelle belle journée : " + timeService.getLocalDate(actualLifeDate);
    }

    @GetMapping("/addTime")
    public String addTime(LocalDateTime previousLifeDate,int hour, int minutes){
        LocalDateTime actualLifeDate = timeService.addTimeToDate(previousLifeDate, hour,minutes);
        return timeService.getTimeOfDay(actualLifeDate);
    }

    @GetMapping("/getLocalDate")
    public String getLocalDate(LocalDateTime actualLifeDate) {
        return timeService.getLocalDate(actualLifeDate);
    }

    @GetMapping("/getTimeOfDay")
    public String getTimeOfDay(LocalDateTime actualLifeDate){
        return timeService.getTimeOfDay(actualLifeDate);
    }
}
