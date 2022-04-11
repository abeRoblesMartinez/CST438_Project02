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

    @GetMapping(path="/allLists")
    public @ResponseBody Iterable<WishList> getAllLists(){return wishListRepository.findAll();}

    @GetMapping(path = "/findUser")
    public @ResponseBody User findUser(String username){
        if (userRepository.existsByUsernameLikeIgnoreCase(username)){
            return userRepository.findByUsernameLikeIgnoreCase(username);
        } else{
            return null;
        }
    }

    @PostMapping(path = "/addUser")
    public @ResponseBody String addUser (@RequestParam String username, @RequestParam String user_password, @RequestParam String email){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(user_password);
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
        if (userRepository.existsByUsernameLikeIgnoreCase(username)) {
            User user1 = userRepository.findByUsernameLikeIgnoreCase(username);
            user1.addWishList(list);
            userRepository.save(user1);
            wishListRepository.save(list);
            return "wishlist added";
        } else {
            return "username not found";
        }
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

                userRepository.save(user1);
            } else {
                return "list not found";
            }
        } else {
            return "username not found";
        }
        return "item added";
    }


}
