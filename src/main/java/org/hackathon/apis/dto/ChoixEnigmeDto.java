package org.hackathon.apis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChoixEnigmeDto {
    public int idChoix;
    public int isOK;
    public String title;
}
