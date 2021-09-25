package org.hackathon.apis.controller;

import org.hackathon.apis.model.Level;
import org.hackathon.apis.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/level")
public class LevelController {

    @Autowired
    private LevelService levelService;

    /*
    * Initialisation des levels et return level 0
    * */
    @GetMapping("/Init")
    public Level Init(){
        levelService.InitLevels();
        return levelService.listLevels.get(0);
    }

    @GetMapping("/getLevelById/{levelNumber}")
    public Level getLevelById(@PathVariable("levelNumber") int levelNumber){
        Level level = levelService.getLevel(levelNumber);
        return level;
    }

    /*
    * Vérifier la validité d'un niveau par le nombre de points collectés
    * */
    @GetMapping("/isValidLevel")
    public boolean isValidLevel(int levelNumber){
        boolean validation = levelService.isValidLevel(levelNumber);
        return validation;
    }

}
