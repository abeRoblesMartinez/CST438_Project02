package com.example.cst438_project02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SpringBootApplication
public class Cst438Project02Application {

    @GetMapping("/home")
    public String home( String id) {

        return "home";
    }
    @GetMapping("/additem")
    public String additem(String id){return "additem";
    }

    @GetMapping("/landingpage")
    public String landingpage(String id){
        return "landingpage";
    }


    @RequestMapping(value = "/name")
    @ResponseBody
    String name(){
        return "user";
    }


    public static void main(String[] args) {
        SpringApplication.run(Cst438Project02Application.class, args);
    }


}
