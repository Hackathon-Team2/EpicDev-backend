package org.hackathon.apis.controller;

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

    @GetMapping("/getLevelById/{levelNumber}")
    public String getLevelById(@PathVariable("levelNumber") int levelNumber){
        String levelTitle = levelService.getLevelTitle(levelNumber);
        return levelTitle;
    }

    /*
    * Vérifier la validité d'un niveau par le nombre de points collectés
    * */
    @GetMapping("/isValidLevel")
    public boolean isValidLevel(int levelNumber){
        if(!levelService.listLevels.contains(levelNumber))
            return false;
        boolean validation = levelService.isValidLevel(levelNumber);
        return validation;
    }

}
