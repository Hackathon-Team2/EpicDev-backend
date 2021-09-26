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
 * Controller pour les actions du level 1
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/action/level/1")
public class ActionLevel1Controller {

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
        devDto.setPhraseAccompagnatrice("Niveau 1 - Culture G sur un projet informatique");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(1));
        devDto.setNiveauActuel(1);
        devDto.setNiveauSuivant(false);
        devDto.setActionsDoneDto(new ActionsDoneDto());
        return devDto;
    }
    @PostMapping("/startAgain")
    public DevDto startAgain(DevDto devDto) {
        devDto.setPhraseAccompagnatrice("Une nouvelle journée commence !");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(1));
        return devDto;
    }

    @PostMapping("/doAction")
    public DevDto doAction(@RequestBody ActionRequestBody actionRequestBody) {
        DevDto devDto = actionRequestBody.getDevDto();
        ActionsDoneDto actionsFaites = devDto.getActionsDoneDto();
        switch (actionRequestBody.getAction()) {

            case "Aller au daily":
                if (!actionsFaites.isDaily()) {
                    devDto.setPhraseAccompagnatrice("Tes collègues et toi se mettent en rond devant une télévision. Amusé par cet attroupement, tu commences à fredonner...Sur le pont d'Avignon, on y danse tous en rond....\n Néanmoins ton manager te remarque et te prie de te concentrer.");
                    devDto.setPoints(devDto.getPoints() + 50);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                    devDto.getActionsPossibles().remove("Aller au daily");
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
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                break;

            case "Installer l'environnement de développement":
                if (!actionsFaites.isEnvironnementDeveloppementInstalle()) {
                    devDto.setPhraseAccompagnatrice("Super, tu installes IntelliJ, Git et bien sûr l'indispensable Notepad ++ !\nAvec tout ça, tu devrais pouvoir faire tes développements sans problème.");
                    devDto.setPoints(devDto.getPoints() + 100);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 0));
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà installé ton environnement de développement.");
                }
                break;

            case "Demander les accès aux outils du projet":
                if (!actionsFaites.isAccesDemandes()) {
                    devDto.setPhraseAccompagnatrice("Bonne initiative ! Sans tes accès Jira, impossible d'accéder au tableau agile de l'équipe. Il va aussi te falloir les accès Github pour récupérer le code et y contribuer.");
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                    devDto.setPoints(devDto.getPoints() + 100);
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déjà demandé tes accès, patience ! Depuis quand est-ce que l'on obtient ses accès en moins de 3 semaines ?");
                }
                break;

            case "Lire la documentation du projet":
                devDto.setPhraseAccompagnatrice("C'est bien, tu pourras comprendre l'architecture du projet et son fonctionnement afin d'attaquer plus sereinement les développements.");
                devDto.setPoints(devDto.getPoints() + 100);
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 0));
                break;

            case "Rencontrer le lead dev du projet":
                if (!actionsFaites.isLeadDevRencontre()) {
                    devDto.setPhraseAccompagnatrice("Le lead Dev te présente la stack technique et l'architecture du projet pour que tu comprennes bien comment tout fonctionne.");
                    devDto.setPoints(devDto.getPoints() + 50);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 0));
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déja rencontré le lead dev, n'aurais-tu pas une certaine attirance pour ce jeune homme ? ;)");

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
            devDto.setNiveauSuivant(true);
//            if(levelService.isValidLevel(devDto,1)){
//                devDto.setNiveauSuivant(true);
//            }
        }
        return devDto;
    }

}
