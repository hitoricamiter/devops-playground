package ru.zaikin.microone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
@CrossOrigin(origins = "*")
public class HelloController {
    
    @GetMapping
    public String hello() {
        return "HELLO FROM WELCOME";
    }
    
    
}
