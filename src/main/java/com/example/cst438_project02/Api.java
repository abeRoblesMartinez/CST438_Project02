package com.example.cst438_project02;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/allUsers")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(path = "/addUser")
    public @ResponseBody String addUser (@RequestParam String name, @RequestParam String password, @RequestParam String email){
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "saved";
    }

}
