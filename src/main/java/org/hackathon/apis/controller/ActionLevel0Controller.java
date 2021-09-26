package org.hackathon.apis.controller;

import org.hackathon.apis.dto.ActionRequestBody;
import org.hackathon.apis.dto.ActionsDoneDto;
import org.hackathon.apis.dto.DevDtoBodyParam;
import org.hackathon.apis.dto.LocationDto;
import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.service.LevelService;
import org.hackathon.apis.service.LocationService;
import org.hackathon.apis.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller pour les actions du level 0
 */
@CrossOrigin(origins = "*")
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
        devDto.setLieuActuel(new LocationDto("Open space", ""));
        devDto.setActualLifeDateTime(dateDebut);
        devDto.setPoints(0);
        devDto.setTotalPoints(0);
        devDto.setPhraseAccompagnatrice("Bienvenue dans l'open space ! C'est ici que tu passeras la plupart de ton temps pour travailler.");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(0));
        devDto.setActionsDoneDto(new ActionsDoneDto());
        devDto.setNiveauSuivant(false);
        devDto.setNiveauActuel(0);
        return devDto;
    }

    @PostMapping("/doAction")
    public DevDto doAction(@RequestBody ActionRequestBody actionRequestBody) {
        DevDto devDto = actionRequestBody.getDevDto();
        ActionsDoneDto actionsFaites = devDto.getActionsDoneDto();
        switch (actionRequestBody.getAction()) {
            case "Aller voir mon manager":
                if (!actionsFaites.isManagerRencontre()) {
                    devDto.setPhraseAccompagnatrice("Ton nouveau manager est ravi de t'accueillir. Il te présente rapidement le projet et l'équipe, mais tu auras l'occasion d'en savoir plus à ces sujets plus tard.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.getActionsPossibles().remove("Aller voir mon manager");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                    actionsFaites.setManagerRencontre(true);
                }
                break;

            case "Faire le tour de l'open space avec mon manager":
                if (actionsFaites.isManagerRencontre()) {
                    devDto.setPhraseAccompagnatrice("Ton manager te fait découvrir tes collègues, les différentes équipes de travail et l'agencement de l'open space. Tu te présentes brièvement devant ta future équipe, ils sont ravis de t'accueillir.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.getActionsPossibles().remove("Faire le tour de l'open space avec mon manager");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                    actionsFaites.setTourOpenSpaceFait(true);
                } else {
                    devDto.setPhraseAccompagnatrice("Tu devrais d'abord saluer ton manager, c'est ton premier jour quand même !");
                }
                break;

            case "Aller manger":
                LocalDateTime actualLifeDateTime = devDto.getActualLifeDateTime();
                if (actualLifeDateTime.getHour() < 12) {
                    devDto.setPhraseAccompagnatrice("Patience, tu pourras aller manger à midi !");
                } else if (actualLifeDateTime.getHour() > 14) {
                    devDto.setPhraseAccompagnatrice("Ce n'est pas un peu tard pour aller manger ?");
                } else {
                    devDto.setPhraseAccompagnatrice("Tu vas manger avec tes collègues dans un bon restaurant japonais à volonté situé près des bureaux. Ca promet une après-midi productive ...");
                    devDto.setActualLifeDateTime(devDto.getActualLifeDateTime().withHour(14).withMinute(0));
                    devDto.getActionsPossibles().remove("Aller manger");
                }
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
                    devDto.getActionsPossibles().add("Rencontrer le lead dev du projet");
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

            case "Demander les accès aux applications utilisées par l'équipe":
                if (!actionsFaites.isAccesDemandes()) {
                    devDto.setPhraseAccompagnatrice("Bonne initiative ! Sans tes accès Jira, impossible d'accéder au tableau agile de l'équipe. Il va aussi te falloir les accès Github pour récupérer le code et y contribuer.");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                    actionsFaites.setAccesDemandes(true);
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
    public DevDto finishDay(@RequestBody DevDtoBodyParam devDtoBodyParam){
        DevDto devDto = devDtoBodyParam.getDevDto();
        LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();

        // S'il n'est pas encore 17h30, on empêche de finir
        if (actualLifeTime.isBefore(actualLifeTime.withHour(17).withMinute(30))){
            devDto.setPhraseAccompagnatrice("Tu prends ton après-midi ?");
        } else {
            // Sinon on passe au niveau suivant
            LocalDateTime debutJourneeSuivante = devDto.getActualLifeDateTime();
            debutJourneeSuivante = debutJourneeSuivante.withHour(9).withMinute(0);
            devDto.setActualLifeDateTime(debutJourneeSuivante);
            devDto.setPhraseAccompagnatrice("Cette première journée t'a permis d'avoir un premier contact avec tes collègues et ton futur projet. Dès demain, les choses sérieuses vont commencer !");
        }
        return devDto;
    }

}
