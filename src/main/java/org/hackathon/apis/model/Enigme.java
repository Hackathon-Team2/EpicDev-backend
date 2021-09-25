package org.hackathon.apis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Enigme {
    public int id;
    public String title;
    public List<ChoixEnigme> listChoix;
    public int points;

    public Enigme(int id, String title, List<ChoixEnigme> listChoix, int points) {
        this.id = id;
        this.title = title;
        this.listChoix = listChoix;
        this.points = points;
    }
}
