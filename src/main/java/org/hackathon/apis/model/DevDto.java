package org.hackathon.apis.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DevDto {

    public int points;
    public LocalDateTime actualLifeDateTime;
    public String phraseAccompagnatrice;

}
