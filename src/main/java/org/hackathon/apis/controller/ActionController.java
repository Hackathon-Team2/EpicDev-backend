package org.hackathon.apis.controller;

import org.hackathon.apis.enums.TasksEnum;
import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.service.LocationService;
import org.hackathon.apis.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    public TimeService timeService;

    @Autowired
    public TasksEnum tasksEnum;

    @Autowired
    public LocationService locationService;

    @PostMapping("/whatDo")
    public List<String> whatToDo(DevDto devDto, List<String> levelEvents) {

        List<String> toDoList = new ArrayList<>();
        LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();

        //Cas Daily
        if (actualLifeTime.isBefore(timeService.getDailyTime(actualLifeTime))) {
            toDoList.add(tasksEnum.getDescriptionFromEnum("DAILY"));
            //S'il reste moins de 15 minutes avant le daily, on force le daily uniquement
            if ( ChronoUnit.MINUTES.between(actualLifeTime, timeService.getDailyTime(actualLifeTime)) < 15){
                return toDoList;
            }
        }

        //Cas Miam
        if(actualLifeTime.isBefore(actualLifeTime.withHour(13).withMinute(00)) && actualLifeTime.isAfter(actualLifeTime.withHour(11).withMinute(30))){
            toDoList.add(tasksEnum.getDescriptionFromEnum("MANGER"));
            if (actualLifeTime.isAfter(actualLifeTime.withHour(12).withMinute(30))) {
                return toDoList;
            }

        }

        for (String event : levelEvents){
            toDoList.add(tasksEnum.getDescriptionFromEnum(event));
        }

        toDoList.add(tasksEnum.getDescriptionFromEnum("DEV"));

        toDoList.add(tasksEnum.getDescriptionFromEnum("CAFE"));

        return toDoList;
    }

    @PostMapping("/selectedEvent")
    public DevDto selectedEvent(DevDto devDto,String event,int hour, int min){

        LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
        int newPoints = 0;
        actualLifeTime.plusHours(hour).plusMinutes(min);
        if (event.equals("DEV")){
            if (!devDto.getLieuActuel().getLabel().equals("Maison") && !devDto.getLieuActuel().getLabel().equals("Open space")){
                 locationService.goToOpenSpace(devDto);
            }
            newPoints = ((hour*60)+min)*10;
            actualLifeTime.plusHours(hour).plusMinutes(min);
        } else if (event.equals("AFFINAGE") || event.equals("DEMO")) {
            if (!devDto.getLieuActuel().getLabel().equals("Maison")){
                locationService.goToMeetingRoom(devDto);
            }
            int random = (int)Math.random()*50;

            newPoints = tasksEnum.getPointsFromStringCode(event) + ((hour*60)+min)*5 + random;

        } else {
            if (!devDto.getLieuActuel().getLabel().equals("Maison") && event.equals("CAFE")){
                locationService.goToCoffeeArea(devDto);
            }
            newPoints = tasksEnum.getPointsFromStringCode(event);
        }

        devDto.setPoints(devDto.getPoints()+newPoints);

        return devDto;
    }


    @PostMapping("/finishDay")
    public DevDto finishDay(DevDto devDto){
        LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
        //S'il est déja plus de 17h30, on force l'arrêt
        if ( actualLifeTime.isBefore(actualLifeTime.withHour(17).withMinute(30))){
            devDto.setPhraseAccompagnatrice("Tu prends ton après-midi ?");
        }
        return devDto;
    }

}
