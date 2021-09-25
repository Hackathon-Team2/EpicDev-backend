package org.hackathon.apis.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TasksEnum {
    CAFE("CAFE","Aller en pause café",10),
    DAILY("DAILY","Aller au daily",30),
    DEMO("DEMO","Aller en démo",50),
    DEV("DEV","Développer",0);

    public final String code;
    public final String description;
    public final int points;

    public String getDescriptionFromEnum(String value){
        for(TasksEnum te : values()) {
            if (te.toString().equals(value)){
                return te.getDescription();
            }
        }
        return value;
    }

    public int getPointsFromStringCode(String code){
        for(TasksEnum te : values()){
            if(te.toString().equals(code)){
                return te.getPoints();
            }
        }
        return 0;
    }



}
