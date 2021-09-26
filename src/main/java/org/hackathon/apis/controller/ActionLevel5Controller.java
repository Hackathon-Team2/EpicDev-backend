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
@RequestMapping("/action/level/5")
public class ActionLevel5Controller {

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
        devDto.setPhraseAccompagnatrice("Niveau 5 - Déploiement et recette");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(5));
        devDto.setNiveauActuel(5);
        devDto.setNiveauSuivant(false);
        devDto.setActionsDoneDto(new ActionsDoneDto());
        return devDto;
    }
    @PostMapping("/startAgain")
    public DevDto startAgain(DevDto devDto) {
        devDto.setPhraseAccompagnatrice("Une nouvelle journée commence !");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(5));
        return devDto;
    }

    @PostMapping("/doAction")
    public DevDto doAction(@RequestBody ActionRequestBody actionRequestBody) {
        DevDto devDto = actionRequestBody.getDevDto();
        ActionsDoneDto actionsFaites = devDto.getActionsDoneDto();
        switch (actionRequestBody.getAction()) {

            case "Aller au daily":
                if (!actionsFaites.isDaily()) {
                    devDto.setPhraseAccompagnatrice("Tes collègues et toi se mettent en rond devant une télévision. Amusé par cet attroupement, tu commences à fredonner...Sur le pont d'Avignon, on y danse tous en rond...\n Néanmoins ton manager te remarque et te prie de te concentrer.");
                    devDto.setPoints(devDto.getPoints() + 50);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                    devDto.getActionsPossibles().remove("Aller au daily");
                } else {
                    devDto.setPhraseAccompagnatrice("");

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
                        devDto.setPhraseAccompagnatrice("Tu vas manger avec tes collègues dans un restaurant javanais situé près des bureaux. Finalement tu n'as pas vraiment aimé !");
                        actionsFaites.setAllerManger(true);
                        devDto.setActualLifeDateTime(devDto.getActualLifeDateTime().withHour(14).withMinute(0));
                    }
                }else{
                    devDto.setPhraseAccompagnatrice("Ce n'est pas un peu tard pour aller manger ?");
                    devDto.setPoints(devDto.getPoints() - 50);
                }
                break;

            case "Vérifier la qualité du code":
                devDto.setPhraseAccompagnatrice("Très bien ! Tu utilises l'outiul Sonar pour vérifier la qualité du code que tu as écrit.");
                devDto.setPoints(devDto.getPoints() + 150);
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 30));
                break;

            case "Faire une communication de mise en recette":
                if (!actionsFaites.isCommunicationMiseEnRecette()) {
                    devDto.setPhraseAccompagnatrice("Très bien, la communication c'est la base ! Comme ça, tout le monde est au courant.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 1, 00));
                    devDto.getActionsDoneDto().setCommunicationMiseEnRecette(true);
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà installé ton environnement de développement.");
                }
                break;

            case "Déploiement en recette":
                if (!actionsFaites.isAccesDemandes()) {
                    devDto.setPhraseAccompagnatrice("La tension est à son comble, tu t'apprêtes à déployer ton code... Et le déploiement est un succès !");
                    devDto.setPoints(devDto.getPoints() + 250);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 30));
                }
                break;

            case "Faire une pause café":
                devDto.setPhraseAccompagnatrice("Tu recharges tes accus avec un bon café avec tes collègues");
                devDto.setPoints(devDto.getPoints() + 50);
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 0));
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
            if(levelService.isValidLevel(devDto,5)){
                devDto.setNiveauSuivant(true);
            }

        }
        return devDto;
    }

}
