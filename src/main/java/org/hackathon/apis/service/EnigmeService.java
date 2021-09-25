package org.hackathon.apis.service;

import org.hackathon.apis.model.ChoixEnigme;
import org.hackathon.apis.model.Enigme;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnigmeService {
    public static List<Enigme> listEnigmes = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme0 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme1 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme2 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme3 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme4 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme5 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme6 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme7 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme8 = new ArrayList<>();
    public static List<ChoixEnigme> listChoixEnigme9 = new ArrayList<>();


    public static void InitEnigmes(){
        listEnigmes.add(new Enigme(0, "ceci est un test", listChoixEnigme0, 100));
    }

    public static void InitChoixEnigmes(){
        listChoixEnigme0.add(new ChoixEnigme(0, 0, "1 er choix"));
        listChoixEnigme0.add(new ChoixEnigme(1, 1, "2 er choix"));
        listChoixEnigme0.add(new ChoixEnigme(2, 0, "3 er choix"));
    }

}
