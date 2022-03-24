
package com.example.cst438_project02;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class UserController
{
    @RequestMapping("/index")
    public String index()
    {
        return"index";
    }


    @RequestMapping(value="/save", method=RequestMethod.POST)
    public ModelAndView save(@ModelAttribute User user)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userdata");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}