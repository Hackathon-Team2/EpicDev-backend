package org.hackathon.apis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LevelDto {
    private int levelNumber;
    private String title;
    private EnigmeDto enigmeDto;
}
