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
}
