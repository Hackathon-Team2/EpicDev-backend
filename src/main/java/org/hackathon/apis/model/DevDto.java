package org.hackathon.apis.model;


import lombok.Getter;
import lombok.Setter;
import org.hackathon.apis.dto.LocationDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class DevDto {

    private int totalPoints;
    private int points;
    private LocalDateTime actualLifeDateTime;
    private String phraseAccompagnatrice;
    private LocationDto lieuActuel;

}
