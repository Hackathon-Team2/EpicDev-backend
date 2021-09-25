package org.hackathon.apis.service;

import org.hackathon.apis.dto.IndicationEnigmeDto;
import org.hackathon.apis.dto.EnigmeDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnigmeService {
    public static List<EnigmeDto> listEnigmes = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme0 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme1 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme2 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme3 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme4 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme5 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme6 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme7 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme8 = new ArrayList<>();
    public static List<IndicationEnigmeDto> listIndicesEnigme9 = new ArrayList<>();


    public static void InitEnigmes(){
        listEnigmes.add(new EnigmeDto(0, "Qu'est ce qu'il y a derrière un projet ?", listIndicesEnigme0, "chef", 100));
        listEnigmes.add(new EnigmeDto(1, "Qu'est ce qui est en commun entre une cuisine et un projet informatique ?", listIndicesEnigme1, "recette", 100));
        listEnigmes.add(new EnigmeDto(2, "Quel est le code de l’erreur « URL/file not found » ?", listIndicesEnigme2, "404", 100));
        listEnigmes.add(new EnigmeDto(3, "La fonction principale de la MCO c’est la gestion des … ?", listIndicesEnigme3, "incidents", 100));
        listEnigmes.add(new EnigmeDto(4, "Qu’est ce qu’il y a en commun entre une femme et un programme java ?", listIndicesEnigme4, "string", 100));
        listEnigmes.add(new EnigmeDto(5, "Un gâteau qu’on voit souvent sur les navigateurs ?", listIndicesEnigme5, "cookie", 100));
    }

    public static void InitChoixEnigmes(){
        listIndicesEnigme0.add(new IndicationEnigmeDto(0, "Derrière au sens propre"));
        listIndicesEnigme0.add(new IndicationEnigmeDto(1, "S'écrit sur 4 caractères"));
        listIndicesEnigme0.add(new IndicationEnigmeDto(2, "Piloter une équipe"));
        listIndicesEnigme1.add(new IndicationEnigmeDto(0, "Non, ça ne se mange pas :)"));
        listIndicesEnigme1.add(new IndicationEnigmeDto(1, "S'écrit sur 7 caractères"));
        listIndicesEnigme2.add(new IndicationEnigmeDto(0, "Modèle peugeot des années 60"));
        listIndicesEnigme3.add(new IndicationEnigmeDto(0, "Ce n’est pas des problèmes mais ça pose problème"));
        listIndicesEnigme4.add(new IndicationEnigmeDto(0, "Un type"));
        listIndicesEnigme4.add(new IndicationEnigmeDto(0, "S’écrit sur 6 caractères"));
        listIndicesEnigme5.add(new IndicationEnigmeDto(0, "ça se passe entre la boulangerie et la CNIL !"));
    }

}
