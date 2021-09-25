package org.hackathon.apis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EnigmeDto {
    public int id;
    public String title;
    public List<ChoixEnigmeDto> listChoix;
    public int points;
}
