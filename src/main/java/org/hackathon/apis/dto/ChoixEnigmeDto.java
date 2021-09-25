package org.hackathon.apis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChoixEnigmeDto {
    private int idChoix;
    private int isOK;
    private String title;
}
