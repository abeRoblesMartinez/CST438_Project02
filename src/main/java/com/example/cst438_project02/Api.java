package com.example.cst438_project02;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(path = "/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @GetMapping(path = "/allUsers")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "allItems")
    public @ResponseBody Iterable<Items> getAllItems(){ return itemRepository.findAll();}

    @PostMapping(path = "/addUser")
    public @ResponseBody String addUser (@RequestParam String name, @RequestParam String password, @RequestParam String email){
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "saved";
    }

    @PostMapping(path = "/setPassword")
    public @ResponseBody String setPassword(@RequestParam String username, @RequestParam String password, @RequestParam String newPassword){
        User user1 = userRepository.findByUsernameLikeIgnoreCase(username);
        if (Objects.equals(user1.getPassword(), password)){
            user1.setPassword(newPassword);
            userRepository.save(user1);
            return "password set";
        } else {
            return "not found";
        }
    }

    @PostMapping(path = "/addItem")
    public @ResponseBody String addItem(@RequestParam String name, @RequestParam String info, @RequestParam float price, @RequestParam String imgUrl){
        Items item = new Items();
        item.setName(name);
        item.setPrice(price);
        item.setInfo(info);
        item.setImgurl(imgUrl);
        itemRepository.save(item);
        return "item saved";
    }

    @PostMapping(path = "/addWishList")
    public @ResponseBody String addWishList(@RequestParam String username, @RequestParam String listName){
        WishList list = new WishList();
        list.setName(listName);
        User user1 = userRepository.findByUsernameLikeIgnoreCase(username);
        user1.addWishList(list);
        userRepository.save(user1);
        wishListRepository.save(list);
        return "wishlist added";
    }


    @PostMapping(path = "/addItemToWishList")
    public @ResponseBody String addItemTouUserList(@RequestParam String username, @RequestParam String listName, @RequestParam String itemName){
        User user1 = userRepository.findByUsernameLikeIgnoreCase(username);
        WishList list1 = wishListRepository.findByNameLike(listName);
        if (itemRepository.existsByNameLikeIgnoreCase(itemName)) {
            Items item1 = itemRepository.findByNameLikeIgnoreCase(itemName);

            list1.addItem(item1);

            List<WishList> listOfLists = user1.getWishlists();

            if (listOfLists.contains(list1)) {
                list1.addItem(item1);
            }
            userRepository.save(user1);
            wishListRepository.save(list1);
        }
        return "item added";
    }


}
