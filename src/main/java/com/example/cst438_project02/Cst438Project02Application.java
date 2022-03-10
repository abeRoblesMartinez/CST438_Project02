package com.example.cst438_project02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Cst438Project02Application {
//(@RequestParam(defaultValue = "test")

//    @ResponseBody
    @GetMapping("/home")
  public String home( String id) {

        return "home";
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
