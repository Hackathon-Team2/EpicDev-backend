package org.hackathon.apis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level {
    public int levelNumber;
    public String title;
    public Enigme enigme;

    public Level(int levelNumber, String title, Enigme enigme) {
        this.levelNumber = levelNumber;
        this.title = title;
        this.enigme = enigme;
    }
}
