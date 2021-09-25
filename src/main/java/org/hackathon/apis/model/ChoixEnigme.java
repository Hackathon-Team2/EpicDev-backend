package org.hackathon.apis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoixEnigme {
    public int idChoix;
    public int isOK;
    public String title;

    public ChoixEnigme(int idChoix, int isOK, String title) {
        this.idChoix = idChoix;
        this.isOK = isOK;
        this.title = title;
    }
}
