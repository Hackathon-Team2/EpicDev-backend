package org.hackathon.apis.service;

import org.hackathon.apis.dto.ChoixEnigmeDto;
import org.hackathon.apis.dto.EnigmeDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnigmeService {
    public static List<EnigmeDto> listEnigmes = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme0 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme1 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme2 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme3 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme4 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme5 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme6 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme7 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme8 = new ArrayList<>();
    public static List<ChoixEnigmeDto> listChoixEnigme9 = new ArrayList<>();


    public static void InitEnigmes(){
        listEnigmes.add(new EnigmeDto(0, "ceci est un test", listChoixEnigme0, 100));
    }

    public static void InitChoixEnigmes(){
        listChoixEnigme0.add(new ChoixEnigmeDto(0, 0, "1 er choix"));
        listChoixEnigme0.add(new ChoixEnigmeDto(1, 1, "2 er choix"));
        listChoixEnigme0.add(new ChoixEnigmeDto(2, 0, "3 er choix"));
    }

}
