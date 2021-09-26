package org.hackathon.apis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionsDoneLevel2Dto extends ActionsDoneDto {

    private boolean daily = false;

    private boolean reunionMetier = false;

    private boolean finaliserConfigEnvironnement = false;

    private boolean reflechirSolution = false;

    private boolean developperPoc = false;

    private boolean allerManger = false;
}
