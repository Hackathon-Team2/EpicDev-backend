package org.hackathon.apis.service;

import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.dto.LevelDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LevelService {

    DevDto devDto;
    public List<LevelDto> listLevels = new ArrayList<>();

    public void InitLevels(){
        EnigmeService.InitChoixEnigmes();
        EnigmeService.InitEnigmes();
        listLevels.add(new LevelDto(0,"découverte de l’environnement d'un dev",null));
        listLevels.add(new LevelDto(1,"Culture G sur un projet informatique", EnigmeService.listEnigmes.get(0)));
        listLevels.add(new LevelDto(2,"Analyse des besoins et dev d’un POC",null));
        listLevels.add(new LevelDto(3,"définition des sprints d’un projet",null));
        listLevels.add(new LevelDto(4,"Développement du sprint 1",null));
        listLevels.add(new LevelDto(5,"Préparation et mise en recette",null));
        listLevels.add(new LevelDto(6,"Retours recette",null));
        listLevels.add(new LevelDto(7,"Correction d’anomalies",null));
        listLevels.add(new LevelDto(8,"Préparation et mise en production",null));
        listLevels.add(new LevelDto(9,"MCO",null));
        listLevels.add(new LevelDto(10,"bonus",null));
    }


    public LevelDto getLevel(int num){
        switch(num){
            case 0: return listLevels.get(0);
            case 1: return listLevels.get(1);
            case 2: return listLevels.get(2);
            case 3: return listLevels.get(3);
            case 4: return listLevels.get(4);
            case 5: return listLevels.get(5);
            case 6: return listLevels.get(6);
            case 7: return listLevels.get(7);
            case 8: return listLevels.get(8);
            case 9: return listLevels.get(9);
            case 10: return listLevels.get(10);
        }
        return null;
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
