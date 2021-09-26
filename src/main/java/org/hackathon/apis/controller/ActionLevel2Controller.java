package org.hackathon.apis.controller;

import org.hackathon.apis.dto.ActionRequestBody;
import org.hackathon.apis.dto.ActionsDoneDto;
import org.hackathon.apis.dto.DevDtoBodyParam;
import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.service.LevelService;
import org.hackathon.apis.service.LocationService;
import org.hackathon.apis.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller pour les actions du level 2
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/action/level/2")
public class ActionLevel2Controller {

    @Autowired
    public TimeService timeService;

    @Autowired
    public LocationService locationService;

    @Autowired
    private LevelService levelService;

    @PostMapping("/startLevel")
    public DevDto start(DevDto devDto) {
        LocalDateTime dateDebut = LocalDateTime.now().withHour(9).withMinute(0);
        devDto.setActualLifeDateTime(dateDebut);
        devDto.setTotalPoints(devDto.getTotalPoints() + devDto.getPoints());
        devDto.setPoints(0);
        devDto.setPhraseAccompagnatrice("Niveau 2 - Analyse des besoins et developpement d’un POC");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(2));
        devDto.setNiveauActuel(2);
        devDto.setNiveauSuivant(false);
        devDto.setActionsDoneDto(new ActionsDoneDto());
        return devDto;
    }
    @PostMapping("/startAgain")
    public DevDto startAgain(DevDto devDto) {
        devDto.setPhraseAccompagnatrice("Une nouvelle journée commence !");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(2));
        return devDto;
    }

    @PostMapping("/doAction")
    public DevDto doAction(@RequestBody ActionRequestBody actionRequestBody) {
        DevDto devDto = actionRequestBody.getDevDto();
        ActionsDoneDto actionsFaites = devDto.getActionsDoneDto();
        switch (actionRequestBody.getAction()) {

            case "Aller au daily":
                if (!actionsFaites.isDaily()) {
                    devDto.setPhraseAccompagnatrice("Et hop, petit point journalier du matin pour discuter des sujets en cours.");
                    devDto.setPoints(devDto.getPoints() + 50);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà assisté au daily. \nOui, ça va très vite une journée !");
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

            case "Jouer au démineur":
                devDto.setPhraseAccompagnatrice("Et non, nous ne sommes pas à La Poste ici, il faut travailler !");
                devDto.setPoints(devDto.getPoints() - 50);
                break;

            case "Faire une réunion avec le métier":
                if (!actionsFaites.isReunionMetier()) {
                    devDto.setPhraseAccompagnatrice("Nouvellement arrivé, on t'invite à une réunion avec le métier, d'une part pour faire leurs connaissances, et d'autre part, comprendre et discuter autour du besoin.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà fait la réunion avec le métier.");
                }
                break;

            case "Finaliser les configs de mon environnement de dev":
                if (!actionsFaites.isFinaliserConfigEnvironnement()) {
                    devDto.setPhraseAccompagnatrice("C'est vrai que c'est pénible les configs d'environnements :(, mais ça te fera une bonne chose de faite :)");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 30));
                    devDto.setPoints(devDto.getPoints() + 100);
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà finalisé les configs de ton environnement, il est temps de s'y mettre au dev !");
                }
                break;

            case "Réfléchir à une solution pour mon sujet":
                if(!actionsFaites.isReflechirSolution()){
                    devDto.setPhraseAccompagnatrice("“Le sage réfléchit avant d'agir.”");
                    devDto.setPoints(devDto.getPoints() + 150);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 0));
                } else {
                    devDto.setPhraseAccompagnatrice("C'est bien de réfléchir mais le temps presse ! \nLes bonnes idées viennent en développant ;)");
                }
                break;

            case "Développer un POC":
                if (!actionsFaites.isDevelopperPoc()) {
                    devDto.setPhraseAccompagnatrice("Enfin du dev ! tu vas pouvoir t'amuser un peu ;)");
                    devDto.setPoints(devDto.getPoints() + 200);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 3, 0));
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà développé le POC, il faut passer aux choses sérieuses maintenant !");
                }
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
            // Sinon on passe à la journée suivante
            LocalDateTime debutJourneeSuivante = devDto.getActualLifeDateTime();
            debutJourneeSuivante = debutJourneeSuivante.withHour(9).withMinute(0);
            devDto.setActualLifeDateTime(debutJourneeSuivante);
            devDto.setPhraseAccompagnatrice("La journée s'achève!");
            devDto.setNiveauSuivant(true);
            if(levelService.isValidLevel(devDto,2)){
                devDto.setNiveauSuivant(true);
            }

        }
        return devDto;
    }

}
