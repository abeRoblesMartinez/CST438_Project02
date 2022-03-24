package com.example.cst438_project02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;




import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SpringBootApplication
public class Cst438Project02Application {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String home( String id) {

        return "home";
    }
    @GetMapping("/additem")
    public String additem(String id){return "additem";
    }

    @GetMapping("/")
    public String landingpage(String id){
        return "landingpage";
    }

    @GetMapping("/userdata")
    public String userdata(Model model){
        model.addAttribute("user", new User());
        return "userdata";
    }
    @PostMapping("/userdata")
    public String userdatas(Model model, User user, @RequestParam String username,@RequestParam String email, @RequestParam String password ){
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
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
