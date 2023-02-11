package com.example.biter.Contollers;

import com.example.biter.Domain.Role;
import com.example.biter.Domain.User;
import com.example.biter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN', 'ADMINISTRATOR')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());

        return "users";
    }

    @GetMapping("{user}")
    public String userEditForm(
            @AuthenticationPrincipal User mainUser,
            @PathVariable Integer user,
            Model model
    ) {

        User findedUser = userRepo.findById(user);

        if (!Objects.equals(mainUser.getId(), findedUser.getId()))
            if (mainUser.getRoles().size() <= findedUser.getRoles().size())
                return "redirect:/user";

        model.addAttribute("roles", Role.values());
        model.addAttribute("user", findedUser);


        return "userEdit";
    }

    @PostMapping("saveUser")
    public String saveUser(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam Integer userId
    ){
        User findedUser = userRepo.findById(userId);

        findedUser.setUsername(username);

        Set<String> newRoles = new HashSet<>();

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        for (String role : form.keySet()){
            if (roles.contains(role) && !role.equals(Role.USER.name()))
                newRoles.add(role);
        }

        findedUser.getRoles().clear();
        findedUser.getRoles().add(Role.USER);
        for (String role : newRoles){
            findedUser.getRoles().add(Role.valueOf(role));
        }

        userRepo.save(findedUser);
        return "redirect:/user";
    }
}
