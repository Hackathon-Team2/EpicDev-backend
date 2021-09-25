package org.hackathon.apis.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService {

    public LocalDateTime initializeLifeDate() {

        return LocalDateTime.of(2020,1,1,8,30);
    }

    public LocalDateTime newDay(LocalDateTime actualLifeDate) {
        return actualLifeDate.plusDays(1).withHour(8).withMinute(30);
    }

    public LocalDateTime addTimeToDate(LocalDateTime actualLifeDate, int hours, int min) {
        return actualLifeDate.plusHours(hours).plusMinutes(min);
    }

    public String getTimeOfDay(LocalDateTime actualLifeDate) {
        String hour = String.valueOf(actualLifeDate.getHour());
        String min = String.valueOf(actualLifeDate.getMinute());
        return hour+":"+min;
    }

    public String getLocalDate(LocalDateTime actualLifeDate) {
        return actualLifeDate.toString();
    }

    public LocalDateTime getDailyTime(LocalDateTime actualLifeDate) {
        return actualLifeDate.withHour(9).withMinute(30);
    }

    public LocalDateTime getMiamTime(LocalDateTime actualLifeDate) {
        return actualLifeDate.withHour(12).withMinute(00);
    }


}
