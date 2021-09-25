package org.hackathon.apis.controller;

import org.hackathon.apis.dto.ActionsDoneLevel1Dto;
import org.hackathon.apis.dto.ActionsDoneLevel4Dto;
import org.hackathon.apis.model.DevDto;
import org.hackathon.apis.service.LevelService;
import org.hackathon.apis.service.LocationService;
import org.hackathon.apis.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Controller pour les actions du level 0
 */
@RestController
@RequestMapping("/action/level/4")
public class ActionLevel4Controller {

    @Autowired
    public TimeService timeService;

    @Autowired
    public LocationService locationService;

    @Autowired
    private LevelService levelService;

    @PostMapping("/startLevel")
    public DevDto start(DevDto devDto) {
        devDto.setTotalPoints(devDto.getTotalPoints() + devDto.getPoints());
        devDto.setPoints(0);
        devDto.setPhraseAccompagnatrice("Niveau 1 - Culture G sur un projet informatique");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(4));
        return devDto;
    }
    @PostMapping("/startAgain")
    public DevDto startAgain(DevDto devDto) {
        devDto.setPhraseAccompagnatrice("Une nouvelle journée commence !");
        devDto.setActionsPossibles(levelService.getAvailableActionsByLevel(4));
        return devDto;
    }

    @PostMapping("/doAction")
    public DevDto doAction(DevDto devDto, String action) {
        ActionsDoneLevel4Dto actionsFaites = (ActionsDoneLevel4Dto) devDto.getActionsDoneDto();
        switch (action) {

            case "Aller au daily":
                if (!actionsFaites.isDaily()) {
                    devDto.setPhraseAccompagnatrice("Tes collègues et toi se mettent en rond devant une télévision. Amusé par cet attroupement, tu commences à fredonner...Sur le pont d'Avignon, on y danse tous en rond..../n Néanmoins ton manager te remarque et te prie de te concentrer.");
                    devDto.setPoints(devDto.getPoints() + 50);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                    devDto.getActionsPossibles().remove("Aller au daily");
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déja rencontré le lead dev, n'aurais-tu pas une certaine attirance pour ce jeune homme ? ;)");

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

            case "Jouer au démineur":
                devDto.setPhraseAccompagnatrice("Et non, nous ne sommes pas à La Poste ici, il faut travailler !");
                devDto.setPoints(devDto.getPoints() - 50);
                devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 0, 30));
                break;

            case "Installer l'environnement de développement":
                if (!actionsFaites.isEnvironnementDeveloppementInstalle()) {
                    devDto.setPhraseAccompagnatrice("Ne pouvant accéder à aucune ressource réseau, tes collègues viennent te porter secours Pour que tu es accès à tout");
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

            case "Discuter avec le lead dev du projet":
                if (!actionsFaites.isLeadDevRencontre()) {
                    devDto.setPhraseAccompagnatrice("Le lead Dev te présente la stack technique et l'architecture du projet pour que tu comprennes bien comment tout fonctionne.");
                    devDto.setPoints(devDto.getPoints() + 50);
                    devDto.setActualLifeDateTime(timeService.addTimeToDate(devDto.getActualLifeDateTime(), 2, 0));
                    devDto.getActionsPossibles().remove("Discuter avec le lead dev du projet");
                } else {
                    devDto.setPhraseAccompagnatrice("Tu as déja rencontré le lead dev, n'aurais-tu pas une certaine attirance pour ce jeune homme ? ;)");

                }
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
            devDto.setPhraseAccompagnatrice("La journée s'achève!");
            if(levelService.isValidLevel(devDto,4)){
                devDto.setNiveauSuivant(true);
            }

        }
        return devDto;
    }

}
