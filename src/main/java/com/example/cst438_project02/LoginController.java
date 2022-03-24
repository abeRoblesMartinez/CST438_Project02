package com.example.cst438_project02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getLoginForm(){
        return "index";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute (name="user") User user, Model model){
        String username = user.getUsername();
        String password = user.getPassword();

        if(userRepository.existsByUsernameLikeIgnoreCase(username)){
            User log = userRepository.findByUsernameLikeIgnoreCase(username);
            if (log.getPassword().equals(password)){
                return "wishlists";
            } else {
                return "index";
            }
        } else{
            return "index";
        }
    }

    @GetMapping(value="/login")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "wishlists";
    }
}
