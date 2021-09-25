package org.hackathon.apis.service;

import org.hackathon.apis.model.DevDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<String> getAvailableActionsByLevel(int level) {
        List<String> availableActions = new ArrayList<>();
        switch (level) {
            case 0:
                availableActions = Arrays.asList("Aller voir mon manager",
                        "Faire le tour de l'open space avec mon manager",
                        "Découvrir mon poste de travail",
                        "Aller manger");
                break;
        }
        return availableActions;
    }

    public boolean isValidLevel(int levelNumber){
        boolean verifPoints = false;

        switch(levelNumber){
            case 0:
                verifPoints = true;
                break;
            case 1 :
                if (devDto.getPoints() >= 500)
                    verifPoints = true;
                break;
            case 2:
                if (devDto.getPoints() >= 600)
                    verifPoints = true;
                break;
            case 3:
                if (devDto.getPoints() >= 700)
                    verifPoints = true;
                break;
            case 4:
                if (devDto.getPoints() >= 800)
                    verifPoints = true;
                break;
            case 5:
                if (devDto.getPoints() >= 900)
                    verifPoints = true;
                break;
            case 6:
                if (devDto.getPoints() >= 1000)
                    verifPoints = true;
                break;
            case 7:
                if (devDto.getPoints() >= 1100)
                    verifPoints = true;
                break;
            case 8:
                if (devDto.getPoints() >= 1200)
                    verifPoints = true;
                break;
            case 9:
                if (devDto.getPoints() >= 1300)
                    verifPoints = true;
                break;
            case 10:
                if (devDto.getPoints() >= 1500)
                    verifPoints = true;
                break;
        }

        if(verifPoints && levelNumber != 0)
            devDto.setTotalPoints(devDto.getTotalPoints() + devDto.getPoints());
        devDto.setPoints(0);
        return verifPoints;
    }

}
