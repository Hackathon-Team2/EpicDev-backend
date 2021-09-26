package org.hackathon.apis.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class ApiController {

    @GetMapping("/TestApi")
    public String Test(){
        return "TEST API OK";
    }

    @GetMapping("/Accueil")
    public String Accueil() {
        return "           _______   ________  ___  ________          ________  _______   ___      ___ _______   ___       ________  ________  _______   ________     \n" +
                "          |\\  ___ \\ |\\   __  \\|\\  \\|\\   ____\\        |\\   ___ \\|\\  ___ \\ |\\  \\    /  /|\\  ___ \\ |\\  \\     |\\   __  \\|\\   __  \\|\\  ___ \\ |\\   __  \\    \n" +
                "          \\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\___|        \\ \\  \\_|\\ \\ \\   __/|\\ \\  \\  /  / | \\   __/|\\ \\  \\    \\ \\  \\|\\  \\ \\  \\|\\  \\ \\   __/|\\ \\  \\|\\  \\   \n" +
                "           \\ \\  \\_|/_\\ \\   ____\\ \\  \\ \\  \\            \\ \\  \\ \\\\ \\ \\  \\_|/_\\ \\  \\/  / / \\ \\  \\_|/_\\ \\  \\    \\ \\  \\\\\\  \\ \\   ____\\ \\  \\_|/_\\ \\   _  _\\  \n" +
                "            \\ \\  \\_|\\ \\ \\  \\___|\\ \\  \\ \\  \\____        \\ \\  \\_\\\\ \\ \\  \\_|\\ \\ \\    / /   \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\___|\\ \\  \\_|\\ \\ \\  \\\\  \\| \n" +
                "             \\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\       \\ \\_______\\ \\_______\\ \\__/ /     \\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\_______\\ \\__\\\\ _\\ \n" +
                "              \\|_______|\\|__|     \\|__|\\|_______|        \\|_______|\\|_______|\\|__|/       \\|_______|\\|_______|\\|_______|\\|__|     \\|_______|\\|__|\\|__|\n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      \n" +
                "                                      _________  ___  ___  _______           ________  ________  _____ ______   _______                               \n" +
                "                                     |\\___   ___\\\\  \\|\\  \\|\\  ___ \\         |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\                              \n" +
                "                                     \\|___ \\  \\_\\ \\  \\\\\\  \\ \\   __/|        \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|                             \n" +
                "                                          \\ \\  \\ \\ \\   __  \\ \\  \\_|/__       \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/__                           \n" +
                "                                           \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\                          \n" +
                "                                            \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\                         \n" +
                "                                             \\|__|  \\|__|\\|__|\\|_______|        \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|                         \n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      ";
    }
}
