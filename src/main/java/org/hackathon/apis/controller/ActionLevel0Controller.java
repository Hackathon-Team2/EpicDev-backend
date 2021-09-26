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

    private boolean isFinJournee;

    @PostMapping("/start")
    public DevDto start(DevDto devDto) {
        LocalDateTime dateDebut = LocalDateTime.now().withHour(9).withMinute(0);
        devDto.setActualLifeDateTime(dateDebut);
        isFinJournee = false;
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
                if (!actionsFaites.isManagerRencontre() && !isFinJournee) {
                    devDto.setPhraseAccompagnatrice("Ton nouveau manager est ravi de t'accueillir. Il te présente rapidement le projet et l'équipe, mais tu auras l'occasion d'en savoir plus à ces sujets plus tard.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                    actionsFaites.setManagerRencontre(true);
                    LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
                    if (actualLifeTime.isAfter(actualLifeTime.withHour(17).withMinute(30)))
                        this.isFinJournee = true;
                } else {
                    if (isFinJournee)
                        devDto.setPhraseAccompagnatrice("STOP -_- la journée s'achève, il est temps de rentrer !");
                    else
                        devDto.setPhraseAccompagnatrice("Quelque chose qui n'est pas clair pour toi ?");
                }
                break;

            case "Faire le tour de l'open space":
                if (actionsFaites.isManagerRencontre() && !isFinJournee) {
                    devDto.setPhraseAccompagnatrice("Ton manager te fait découvrir tes collègues, les différentes équipes de travail et l'agencement de l'open space. Tu te présentes brièvement devant ta future équipe, ils sont ravis de t'accueillir.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                    actionsFaites.setTourOpenSpaceFait(true);
                    LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
                    if (actualLifeTime.isAfter(actualLifeTime.withHour(17).withMinute(30)))
                        this.isFinJournee = true;
                } else {
                    if (isFinJournee)
                        devDto.setPhraseAccompagnatrice("STOP -_- la journée s'achève, il est temps de rentrer !");
                    else
                        devDto.setPhraseAccompagnatrice("Tu devrais d'abord saluer ton manager, c'est ton premier jour quand même !");
                    devDto.setPoints(devDto.getPoints() - 50);
                }
                break;

            case "Aller manger":
                if (!actionsFaites.isAllerManger()){
                    LocalDateTime actualLifeDateTime = devDto.getActualLifeDateTime();
                    if (actualLifeDateTime.getHour() < 12) {
                        devDto.setPhraseAccompagnatrice("Patience, tu pourras aller manger à midi !");
                    } else if (actualLifeDateTime.getHour() > 14) {
                        devDto.setPhraseAccompagnatrice("Ce n'est pas un peu tard pour aller manger ?");
                    } else {
                        devDto.setPhraseAccompagnatrice("Tu vas manger avec tes collègues dans un bon restaurant japonais à volonté situé près des bureaux. Ca promet une après-midi productive ...");
                        actionsFaites.setAllerManger(true);
                        devDto.setActualLifeDateTime(devDto.getActualLifeDateTime().withHour(14).withMinute(0));
                    }
                }else{
                    devDto.setPhraseAccompagnatrice("Ce n'est pas un peu tard pour aller manger ?");
                    devDto.setPoints(devDto.getPoints() - 50);
                }
                break;

            case "Découvrir mon poste de travail":
                if (!actionsFaites.isDecouvrirPosteTravail() && !isFinJournee) {
                    if(!actionsFaites.isManagerRencontre()){
                        devDto.setPhraseAccompagnatrice("Tu devrais d'abord rencontrer ton manager et faire connaissance avec les gens autour de toi.");
                        devDto.setPoints(devDto.getPoints() - 50);
                    } else{
                        devDto.setPhraseAccompagnatrice("C'est bien, tu vas pouvoir te familiariser avec tes outils de travail.");
                        devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                        actionsFaites.setDecouvrirPosteTravail(true);
                        LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
                        if (actualLifeTime.isAfter(actualLifeTime.withHour(17).withMinute(30)))
                            this.isFinJournee = true;
                    }
                } else {
                    if (isFinJournee){
                        devDto.setPhraseAccompagnatrice("STOP -_- la journée s'achève, il est temps de rentrer !");
                        devDto.setPoints(devDto.getPoints() - 50);
                    }
                    else{
                        devDto.setPhraseAccompagnatrice("Ne t'inquiète pas, ça va venir avec le temps !");
                        devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                    }
                }
                break;

            case "Jouer au démineur":
                devDto.setPhraseAccompagnatrice("Et non, nous ne sommes pas à La Poste ici, il faut travailler !");
                devDto.setPoints(devDto.getPoints() - 50);
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
            devDto.setNiveauSuivant(true);
        }
        return devDto;
    }

}
