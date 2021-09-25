package org.hackathon.apis.dto;

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
