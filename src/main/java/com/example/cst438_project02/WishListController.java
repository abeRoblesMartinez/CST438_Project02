package com.example.cst438_project02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WishListController {

    RedirectAttributes redirAttrs;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value="/addItem", method = RequestMethod.POST)
    public String addItem(@ModelAttribute (name="item") Items item, Model model){
        String name = item.getName();
        String info = item.getInfo();
        float price = item.getPrice();
        String imgurl = item.getImgurl();

        if (itemRepository.existsByNameLikeIgnoreCase(name)){
            //redirAttrs.addFlashAttribute("error", "The error XYZ occurred.");
            return "additem";
        } else {
            Items item1 = new Items();
            item1.setName(name);
            item1.setInfo(info);
            item1.setPrice(price);
            item1.setImgurl(imgurl);
            itemRepository.save(item1);
            return "wishlists";
        }

    }

    @GetMapping(value="/addItems")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "additem";
    }
}
