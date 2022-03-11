package com.example.cst438_project02;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/allLists")
    public @ResponseBody
    Iterable<WishList> getAllLists(){
        List<WishList> wishLists = (List<WishList>) wishListRepository.findAll();
        List<Items> items = null;
        WishList wishList = null;

        if(wishLists.size()==0){
            wishList = new WishList();
            wishList.setItems(new ArrayList<>());
            for(int i =0; i<5; i++){
                wishList.setName("wishlist "+i);
                items = (List<Items>)  itemRepository.findAll();
                wishList.setItems(items);
                wishListRepository.save(wishList);
            }
        }
        return wishListRepository.findAll();
    }

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

    @PostMapping(path="/setPassword")
    public @ResponseBody String setPassword (@RequestParam String name, @RequestParam String newPassword){
        if(userRepository.existsByUsernameIgnoreCase(name)){
            userRepository.setPassword(newPassword);

            return "password set";
        }

        return "username not found";
    }

    @GetMapping(path="/findByName")
    public @ResponseBody
    List<User> findUserByName(@RequestParam(defaultValue = "jojo99") String name){
        return userRepository.findUserByName(name);
    }
}
