package org.hackathon.apis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionsDoneLevel5Dto extends ActionsDoneDto {

    private boolean daily = false;

    private boolean leadDevRencontre = false;

    private boolean lireDoc = false;

    private boolean environnementDeveloppementInstalle;

    private boolean accesDemandes;
}
