package org.hackathon.apis.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DevDto {

    private int points;
    private LocalDateTime actualLifeDateTime;
    private String phraseAccompagnatrice;

}
