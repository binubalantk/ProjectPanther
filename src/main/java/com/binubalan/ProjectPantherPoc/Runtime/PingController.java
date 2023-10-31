package com.binubalan.ProjectPantherPoc.Runtime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/v1/runtime")
@EnableWebMvc
public class PingController {

    @GetMapping("/ping")
    public String ping(){
        return "OK";
    }

    @GetMapping(value = "/ping-json")
    public PingModel pingJson(){
        return new PingModel("OK");
    }

}
