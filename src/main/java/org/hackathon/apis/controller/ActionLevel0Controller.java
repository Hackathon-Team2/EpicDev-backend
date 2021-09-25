package org.hackathon.apis.controller;

import org.hackathon.apis.dto.ActionsDoneLevel0Dto;
import org.hackathon.apis.dto.LocationDto;
import org.hackathon.apis.enums.TasksEnum;
import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.service.LevelService;
import org.hackathon.apis.service.LocationService;
import org.hackathon.apis.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller pour les actions du level 0
 */
@CrossOrigin
@RestController
@RequestMapping("/action/level/0")
public class ActionLevel0Controller {

    @Autowired
    public TimeService timeService;

    @Autowired
    public LocationService locationService;

    @Autowired
    private LevelService levelService;

    @PostMapping("/start")
    public DevDto start(DevDto devDto) {
        LocalDateTime dateDebut = LocalDateTime.now().withHour(9).withMinute(0);
        DevDto devDtoInit = new DevDto();
        devDtoInit.setLieuActuel(new LocationDto("Open space", ""));
        devDtoInit.setActualLifeDateTime(dateDebut);
        devDto.setPoints(0);
        devDto.setTotalPoints(0);
        devDto.setPhraseAccompagnatrice("Bienvenue dans l'open space ! C'est ici que tu passeras la plupart de ton temps pour travailler.");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(0));
        return devDto;
    }

    @PostMapping("/doAction")
    public DevDto doAction(DevDto devDto, String action) {
        ActionsDoneLevel0Dto actionsFaites = (ActionsDoneLevel0Dto) devDto.getActionsDoneDto();
        switch (action) {
            case "Aller voir mon manager":
                if (!actionsFaites.isManagerRencontre()) {
                    devDto.setPhraseAccompagnatrice("Ton nouveau manager est ravi de t'accueillir. Il te présente rapidement le projet et l'équipe, mais tu auras l'occasion d'en savoir plus à ces sujets plus tard.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.getActionsPossibles().remove("Aller voir mon manager");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                }
                break;

            case "Faire le tour de l'open space avec mon manager":
                if (actionsFaites.isManagerRencontre()) {
                    devDto.setPhraseAccompagnatrice("Ton manager te fait découvrir tes collègues, les différentes équipes de travail et l'agencement de l'open space. Tu te présentes brièvement devant ta future équipe, ils sont ravis de t'accueillir.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.getActionsPossibles().remove("Faire le tour de l'open space avec mon manager");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                }
                break;

            case "Aller manger":
                LocalDateTime actualLifeDateTime = devDto.getActualLifeDateTime();
                if (actualLifeDateTime.getHour() < 12) {
                    devDto.setPhraseAccompagnatrice("Patience, tu pourras aller manger à midi !");
                }
                if (actualLifeDateTime.getHour() > 14) {
                    devDto.setPhraseAccompagnatrice("Ce n'est pas un peu tard pour aller manger ?");
                }
                devDto.setActualLifeDateTime(devDto.getActualLifeDateTime().withHour(14).withMinute(0));
                break;

            case "Découvrir mon poste de travail":
                if (!actionsFaites.isManagerRencontre() || !actionsFaites.isTourOpenSpaceFait()) {
                    devDto.setPhraseAccompagnatrice("Tu devrais d'abord rencontrer ton manager et faire connaissance avec les gens autour de toi.");
                } else {
                    devDto.getActionsPossibles().remove("Découvrir mon poste de travail");
                    devDto.getActionsPossibles().add("Jouer au démineur");
                    devDto.getActionsPossibles().add("Installer l'environnement de développement");
                    devDto.getActionsPossibles().add("Demander les accès aux applications utilisées par l'équipe");
                    devDto.getActionsPossibles().add("Lire la documentation du projet");
                    devDto.setPhraseAccompagnatrice("C'est bien, tu vas pouvoir te familiariser avec tes outils de travail.");
                }
                break;

            case "Jouer au démineur":
                devDto.setPhraseAccompagnatrice("Et non, nous ne sommes pas à La Poste ici, il faut travailler !");
                devDto.setPoints(devDto.getPoints() - 50);
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                break;

            case "Installer l'environnement de développement":
                if (!actionsFaites.isEnvironnementDeveloppementInstalle()) {
                    devDto.setPhraseAccompagnatrice("Super, tu installes IntelliJ, Git et bien sûr l'indispensable Notepad ++ !\nAvec tout ça, tu devrais pouvoir faire tes développements sans problème.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà installé ton environnement de développement.");
                }
                break;

            case "Demander les accès aux outils du projet":
                if (!actionsFaites.isAccesDemandes()) {
                    devDto.setPhraseAccompagnatrice("Bonne initiative ! Sans tes accès Jira, impossible d'accéder au tableau agile de l'équipe. Il va aussi te falloir les accès Github pour récupérer le code et y contribuer.");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà demandé tes accès, patience ! Depuis quand est-ce que l'on obtient ses accès en moins de 3 semaines ?");
                    devDto.setPoints(devDto.getPoints() + 100);
                }
                break;

            case "Lire la documentation du projet":
                devDto.setPhraseAccompagnatrice("C'est bien, tu pourras comprendre l'architecture du projet et son fonctionnement afin d'attaquer plus sereinement les développements.");
                devDto.setPoints(devDto.getPoints() + 100);
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 0));
                break;

            case "Rencontrer le lead dev du projet":
                devDto.setPhraseAccompagnatrice("Tu sympathises avec le lead dev du projet, qui te présente très rapidement ce sur quoi tu vas travailler.");
                devDto.setPoints(devDto.getPoints() + 50);
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 0));
                devDto.getActionsPossibles().remove("Rencontrer le lead dev du projet");
                break;
        }

        return devDto;
    }

    @PostMapping("/finishDay")
    public DevDto finishDay(DevDto devDto){
        LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();

        // S'il n'est pas encore 17h30, on empêche de finir
        if (actualLifeTime.isBefore(actualLifeTime.withHour(17).withMinute(30))){
            devDto.setPhraseAccompagnatrice("Tu prends ton après-midi ?");
        } else {
            // Sinon on passe à la journée suivante
            LocalDateTime debutJourneeSuivante = devDto.getActualLifeDateTime();
            debutJourneeSuivante = debutJourneeSuivante.withHour(9).withMinute(0);
            devDto.setActualLifeDateTime(debutJourneeSuivante);
            devDto.setPhraseAccompagnatrice("Cette première journée t'a permis d'avoir un premier contact avec tes collègues et ton futur projet. Dès demain, les choses sérieuses vont commencer !");

        }
        return devDto;
    }

}
