package com.tamscrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApplication {
 	
  

    @GetMapping("/")
    String home() {
        return "index";
    }

   	

 
}
