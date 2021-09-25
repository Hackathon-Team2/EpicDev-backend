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
    private String libelle;
    private List<IndicationEnigmeDto> listIndications;
    private String reponseEnigme;
    private int points;
}
