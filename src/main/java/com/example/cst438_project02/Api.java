package com.example.cst438_project02;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/allUsers")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

}
