package com.SmartBooking.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class ClasseController {

    @GetMapping
    public  String Olamundo(){
      return "hello word";
    }

}
