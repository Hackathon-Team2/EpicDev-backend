package org.hackathon.apis.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/TestApi")
    public String Test(){
        return "TEST API OK";
    }
}
