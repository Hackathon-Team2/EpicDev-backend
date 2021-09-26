package org.hackathon.apis.service;

import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.dto.LevelDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class LevelService {

    DevDto devDto;
    public List<LevelDto> listLevels = new ArrayList<>();

    public void InitLevels(){
        EnigmeService.InitChoixEnigmes();
        EnigmeService.InitEnigmes();
        listLevels.add(new LevelDto(0,"découverte de l’environnement d'un dev",null));
        listLevels.add(new LevelDto(1,"Culture G sur un projet informatique", EnigmeService.listEnigmes.get(0)));
        listLevels.add(new LevelDto(2,"Analyse des besoins et dev d’un POC",EnigmeService.listEnigmes.get(5)));
        listLevels.add(new LevelDto(3,"définition des sprints d’un projet",null));
        listLevels.add(new LevelDto(4,"Développement d'un sprint",EnigmeService.listEnigmes.get(2)));
        listLevels.add(new LevelDto(5,"Préparation et mise en recette", EnigmeService.listEnigmes.get(1)));
        listLevels.add(new LevelDto(6,"Retours recette",null));
        listLevels.add(new LevelDto(7,"Correction d’anomalies",EnigmeService.listEnigmes.get(4)));
        listLevels.add(new LevelDto(8,"Préparation et mise en production",null));
        listLevels.add(new LevelDto(9,"MCO",EnigmeService.listEnigmes.get(3)));
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
        List<String> temp;
        Random rand = new Random();
        switch (level) {
            case 0:
                availableActions = Arrays.asList("Aller voir mon manager",
                        "Faire le tour de l'open space",
                        "Aller manger",
                        "Jouer au démineur",
                        "Découvrir mon poste de travail");
                break;
            case 1:
                availableActions = Arrays.asList("Aller au daily",
                        "Aller voir mon manager",
                        "Rencontrer le lead dev du projet",
                        "Aller manger",
                        "Installer l'environnement de développement",
                        "Lire la documentation du projet",
                        "Demander les accès aux outils du projet");
                break;
            case 2:
                availableActions = Arrays.asList("Aller au daily",
                        "Faire une réunion avec le métier",
                        "Finaliser les configs de mon environnement de dev",
                        "Aller manger",
                        "Réfléchir à une solution pour mon sujet",
                        "Développer un POC"
                        );
                break;
            case 3:
                availableActions = Arrays.asList("Aller au daily",
                        "Définir les sprints avec l'équipe",
                        "Commencer le développement du sprint 1",
                        "Aller manger",
                        "Faire une pause café");
                break;
            case 4:
                availableActions = Arrays.asList("Aller au daily",
                        "Faire des tests unitaires",
                        "Finaliser le développement du sprint",
                        "Aller manger",
                        "Faire une pause café");
                break;
            case 5:
                availableActions = Arrays.asList("Aller au daily",
                        "Vérifier la qualité du code",
                        "Faire une communication de mise en recette",
                        "Déploiement en recette",
                        "Aller manger",
                        "Faire une pause café");
                break;
            case 6:
                availableActions = Arrays.asList("Aller au daily",
                        "Commencer le développement du sprint 2",
                        "Analyser les remontées de recette",
                        "Aller manger",
                        "Faire une pause café");
                break;
            case 7:
                availableActions = Arrays.asList("Aller au daily",
                        "Corriger les anomalies du sprint 1",
                        "Aller manger",
                        "Faire une pause café");
                break;
            case 8:
                availableActions = Arrays.asList("Aller au daily",
                        "Préparer la mise en prod",
                        "Déploiement en production",
                        "Faire une communication de mise en prod",
                        "Aller manger",
                        "Faire une pause café");
                break;
            case 9:
                availableActions = Arrays.asList("Aller au daily",
                        "Support et maintien en condition opérationnelle de l'application",
                        "Aller manger",
                        "Faire une pause café");
                break;
            case 10:
                availableActions = Arrays.asList("Aller au daily",
                        "Support et MCO",
                        "Jouer à la pétanque :)",
                        "Aller manger",
                        "Faire une pause café",
                        "Faire une astreinte");

                break;
        }

        // Un petit random par ici !
        temp = new ArrayList<>(availableActions);
        for(int i=0; i < availableActions.size(); i++){
            int newPos = rand.nextInt(availableActions.size());
            while(newPos == i || temp.get(newPos) == null){
                newPos = rand.nextInt(availableActions.size());
            }
            availableActions.set(i, temp.get(newPos));
            temp.set(newPos,null);
        }

        return availableActions;
    }

    public boolean isValidLevel(DevDto devDto, int levelNumber){
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
