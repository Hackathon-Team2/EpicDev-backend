package org.hackathon.apis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionsDoneLevel0Dto extends ActionsDoneDto {

    private boolean managerRencontre = false;

    private boolean tourOpenSpaceFait = false;

    private boolean environnementDeveloppementInstalle;

    private boolean accesDemandes;
}
