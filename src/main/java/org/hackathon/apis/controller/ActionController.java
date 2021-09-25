package org.hackathon.apis.controller;

import org.hackathon.apis.enums.TasksEnum;
import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    public TimeService timeService;

    @Autowired
    public TasksEnum tasksEnum;

    @PostMapping("/mandatoryEvent")
    public String initializeDate(DevDto devDto, String event) {

        if (event.equals("Daily")){

        }
        return "prout";
    }

    @PostMapping("/whatDo")
    public List<String> whatToDo(DevDto devDto, List<String> levelEvents) {

        List<String> toDoList = new ArrayList<>();
        LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();

        if (actualLifeTime.isBefore(timeService.getDailyTime(actualLifeTime))) {
            toDoList.add(tasksEnum.getDescriptionFromEnum("DAILY"));
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
            newPoints = ((hour*60)+min)*10;
            actualLifeTime.plusHours(hour).plusMinutes(min);
        } else {
            newPoints = tasksEnum.getPointsFromStringCode(event);
        }

        devDto.setPoints(devDto.getPoints()+newPoints);

        return devDto;
    }
}
