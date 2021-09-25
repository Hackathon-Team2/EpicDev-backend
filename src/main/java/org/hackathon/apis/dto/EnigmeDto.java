package org.hackathon.apis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EnigmeDto {
    private int id;
    private String title;
    private List<ChoixEnigmeDto> listChoix;
    private int points;
}
