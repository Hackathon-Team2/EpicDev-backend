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
        listEnigmes.add(new EnigmeDto(0, "ceci est un test", listIndicesEnigme0, "test", 100));
    }

    public static void InitChoixEnigmes(){
        listIndicesEnigme0.add(new IndicationEnigmeDto(0, "1 er indice"));
        listIndicesEnigme0.add(new IndicationEnigmeDto(1, "2 eme indice"));
        listIndicesEnigme0.add(new IndicationEnigmeDto(2, "3 eme indice"));
    }

}
