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
 * Controller pour les actions du level 0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/action/level/4")
public class ActionLevel4Controller {

    @Autowired
    public TimeService timeService;

    @Autowired
    public LocationService locationService;

    @Autowired
    private LevelService levelService;

    private boolean isFinJournee;

    @PostMapping("/startLevel")
    public DevDto start(DevDto devDto) {
        LocalDateTime dateDebut = LocalDateTime.now().withHour(9).withMinute(0);
        devDto.setActualLifeDateTime(dateDebut);
        devDto.setTotalPoints(devDto.getTotalPoints() + devDto.getPoints());
        isFinJournee = false;
        devDto.setPoints(0);
        devDto.setPhraseAccompagnatrice("Niveau 4 - Ecriture de tests unitaires");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(4));
        devDto.setNiveauActuel(4);
        devDto.setNiveauSuivant(false);
        devDto.setActionsDoneDto(new ActionsDoneDto());
        return devDto;
    }
    @PostMapping("/startAgain")
    public DevDto startAgain(DevDto devDto) {
        devDto.setPhraseAccompagnatrice("Une nouvelle journée commence !");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(4));
        return devDto;
    }

    @PostMapping("/doAction")
    public DevDto doAction(@RequestBody ActionRequestBody actionRequestBody) {
        DevDto devDto = actionRequestBody.getDevDto();
        ActionsDoneDto actionsFaites = devDto.getActionsDoneDto();
        switch (actionRequestBody.getAction()) {

            case "Aller au daily":
                if (!actionsFaites.isDaily() && !isFinJournee) {
                    devDto.setPhraseAccompagnatrice("Et hop, petit point journalier du matin pour discuter des sujets en cours.");
                    devDto.setPoints(devDto.getPoints() + 50);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                    actionsFaites.setDaily(true);
                    LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
                    if (actualLifeTime.isAfter(actualLifeTime.withHour(17).withMinute(30)))
                        this.isFinJournee = true;
                } else {
                    if (isFinJournee)
                        devDto.setPhraseAccompagnatrice("STOP -_- la journée s'achève, il est temps de rentrer !");
                    else
                        devDto.setPhraseAccompagnatrice("Tu as déjà assisté au daily. \nOui, ça va très vite une journée !");
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
                        devDto.setPhraseAccompagnatrice("Tu vas manger avec tes collègues un poke bowl, parce qu'il faut bien prendre soin de sa ligne ! Je te rappelle que tu as été au Jap' hier.");
                        actionsFaites.setAllerManger(true);
                        devDto.setActualLifeDateTime(devDto.getActualLifeDateTime().withHour(14).withMinute(0));
                    }
                }else{
                    devDto.setPhraseAccompagnatrice("Ce n'est pas un peu tard pour aller manger ?");
                    devDto.setPoints(devDto.getPoints() - 50);
                }
                break;

            case "Faire des tests unitaires":
                if(!actionsFaites.isFaireTU() && !isFinJournee){
                    devDto.setPhraseAccompagnatrice("Parfait ! Tester n'est pas douter, c'est éviter des régressions futures et vérifier que tout fonctionne comme il faut.");
                    devDto.setPoints(devDto.getPoints() + 150);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(),3, 00));
                    actionsFaites.setFaireTU(true);
                    LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
                    if (actualLifeTime.isAfter(actualLifeTime.withHour(17).withMinute(30)))
                        this.isFinJournee = true;
                } else {
                    if (isFinJournee)
                        devDto.setPhraseAccompagnatrice("STOP -_- la journée s'achève, il est temps de rentrer !");
                    else
                        devDto.setPhraseAccompagnatrice("C'est déjà fait !");
                    devDto.setPoints(devDto.getPoints() - 50);
                }
                break;

            case "Finaliser le développement du sprint":
                if(!actionsFaites.isFinaliserDevSprint() && !isFinJournee){
                    devDto.setPhraseAccompagnatrice("Ton collègue t'a fait remarquer une petite erreur dans ton code. Pas grave, vaillant comme tu es, tu la corriges en un rien de temps. Et paf, ça fait du code qui marche !");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 30));
                    actionsFaites.setFinaliserDevSprint(true);
                    LocalDateTime actualLifeTime = devDto.getActualLifeDateTime();
                    if (actualLifeTime.isAfter(actualLifeTime.withHour(17).withMinute(30)))
                        this.isFinJournee = true;
                } else {
                    if (isFinJournee)
                        devDto.setPhraseAccompagnatrice("STOP -_- la journée s'achève, il est temps de rentrer !");
                    else
                        devDto.setPhraseAccompagnatrice("Tu as déjà assisté au daily. \nOui, ça va très vite une journée !");
                    devDto.setPoints(devDto.getPoints() - 50);
                }

                break;

            case "Faire une pause café":
                devDto.setPhraseAccompagnatrice("Tu te fais un bon café pour tenir jusqu'à la fin de la journée ! Cela te permet de discuter un peu avec tes collègues et être au courant de l'avancée des autres projets de l'entreprise.");
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
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
            devDto.setNiveauSuivant(true);
//            if(levelService.isValidLevel(devDto,4)){
//                devDto.setNiveauSuivant(true);
//            }

        }
        return devDto;
    }

}
