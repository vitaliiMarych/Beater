package com.example.biter.Contollers;

import com.example.biter.Domain.Role;
import com.example.biter.Domain.User;
import com.example.biter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Map<String, Object> model){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User foundUser = userRepo.findByUsername(user.getUsername());

        if (foundUser != null){
            model.put("message", "Користувач вже існує");
            return "registration";
        }

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            model.put("message", "Тупі дані");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);

        return "redirect:/login";
    }

}
