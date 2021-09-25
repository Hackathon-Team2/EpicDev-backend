package org.hackathon.apis.service;

import org.hackathon.apis.model.DevDto;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class LevelService {

    public List<Integer> listLevels = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);
    DevDto devDto;

    public String getLevelTitle(int num){
        switch(num){
            case 0: return "découverte de l’environnement d'un dev";
            case 1: return "Culture G sur un projet informatique ";
            case 2: return "Analyse des besoins et dev d’un POC";
            case 3: return "définition des sprints d’un projet";
            case 4: return "Développement du sprint 1";
            case 5: return "Préparation et mise en recette ";
            case 6: return "Retours recette";
            case 7: return "Correction d’anomalies";
            case 8: return "Préparation et mise en production";
            case 9: return "MCO";
            case 10: return "bonus";
        }
        return "bonus";
    }

    public boolean isValidLevel(int levelNumber){
        boolean verifPoints = false;

        switch(levelNumber){
            case 0: verifPoints = true;
            case 1 :
                if (devDto.getPoints() >= 500)
                    verifPoints = true;
            case 2:
                if (devDto.getPoints() >= 600)
                    verifPoints = true;
            case 3:
                if (devDto.getPoints() >= 700)
                    verifPoints = true;
            case 4:
                if (devDto.getPoints() >= 800)
                    verifPoints = true;
            case 5:
                if (devDto.getPoints() >= 900)
                    verifPoints = true;
            case 6:
                if (devDto.getPoints() >= 1000)
                    verifPoints = true;
            case 7:
                if (devDto.getPoints() >= 1100)
                    verifPoints = true;
            case 8:
                if (devDto.getPoints() >= 1200)
                    verifPoints = true;
            case 9:
                if (devDto.getPoints() >= 1300)
                    verifPoints = true;
            case 10:
                if (devDto.getPoints() >= 1500)
                    verifPoints = true;
        }

        if(verifPoints)
            devDto.setTotalPoints(devDto.getTotalPoints() + devDto.getPoints());
        devDto.setPoints(0);
        return verifPoints;
    }

}
