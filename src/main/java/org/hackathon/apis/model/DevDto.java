package org.hackathon.apis.model;


import lombok.Getter;
import lombok.Setter;
import org.hackathon.apis.dto.ActionsDoneDto;
import org.hackathon.apis.dto.LocationDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DevDto {

    private int totalPoints;
    private int points;
    private LocalDateTime actualLifeDateTime;
    private String phraseAccompagnatrice;
    private LocationDto lieuActuel;
    private List<String> actionsPossibles;

    /**
     * Actions faites pour le niveau en cours
     */
    private ActionsDoneDto actionsDoneDto;

}
