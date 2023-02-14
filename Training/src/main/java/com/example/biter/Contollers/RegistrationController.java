package com.example.biter.Contollers;

import com.example.biter.Domain.User;
import com.example.biter.InfoMessages.InputInfoMessages;
import com.example.biter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Map<String, Object> model){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        int code = userService.addUser(user);
        if (code != InputInfoMessages.getGoodInputCode()){
            model.put("message", InputInfoMessages.getInfo(code));
            return "registration";
        }

        return "redirect:/login";
    }


    @GetMapping("/activate/{code}")
    public String activateCode(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if (isActivated)
            model.addAttribute("message", "Пошту успішно активовано");
        else
            model.addAttribute("message", "Активаційний код не є дійсний");

        return "login";
    }
}
