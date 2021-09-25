package org.hackathon.apis.dto;

import lombok.Data;

@Data
public class ActionsDoneDto {

    private boolean managerRencontre = false;

    private boolean tourOpenSpaceFait = false;

    private boolean environnementDeveloppementInstalle;

    private boolean accesDemandes;
}
