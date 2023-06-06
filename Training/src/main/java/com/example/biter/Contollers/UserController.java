package com.example.biter.Contollers;

import com.example.biter.Domain.Role;
import com.example.biter.Domain.User;
import com.example.biter.Repos.UserRepo;
import com.example.biter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ADMINISTRATOR')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());

        return "users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ADMINISTRATOR')")
    @GetMapping("{user}")
    public String userEditForm(
            @AuthenticationPrincipal User mainUser,
            @PathVariable Long user,
            Model model
    ) {
        User findedUser = userService.findById(user);

        if (!Objects.equals(mainUser.getId(), findedUser.getId()))
            if (mainUser.getRoles().size() <= findedUser.getRoles().size())
                return "redirect:/user";

        model.addAttribute("roles", Role.values());
        model.addAttribute("user", findedUser);


        return "userEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ADMINISTRATOR')")
    @PostMapping("saveUser")
    public String saveUser(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam Long userId
    ){
        userService.saveUser(userId, username, form);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String saveProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ){
        userService.saveProfile(user, password, email);

        return "redirect:/user/profile";
    }

    @GetMapping("subscribe/{userId}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId
    ) {
        User user = userService.findById(userId);

        userService.subscribe(currentUser, user);

        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("unsubscribe/{userId}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId
    ) {
        User user = userService.findById(userId);

        userService.unsubscribe(currentUser, user);

        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("{type}/{user}/list")
    public String userList(
            Model model,
            @PathVariable String type,
            @PathVariable Long user

    ) {
        User findedUser = userService.findById(user);

        model.addAttribute("userChannel", findedUser);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type)){
            model.addAttribute("users", findedUser.getSubscriptions());
        }
        else {
            model.addAttribute("users", findedUser.getSubscribers());
        }

        return "subscriptions";
    }

}
