package com.example.biter.Contollers;

import com.example.biter.Domain.User;
import com.example.biter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
    public String addUser(@RequestParam("password2") String passwordConfirm, @Valid User user,
                          BindingResult bindingResult, Model model){

        boolean isConfirmEmpty = passwordConfirm.isEmpty();

        if (isConfirmEmpty){
            model.addAttribute("password2Error", "Please fill the password confirmation");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Password are different");
            return "registration";
        }

        if (isConfirmEmpty || bindingResult.hasErrors()){
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            return "registration";
        }

        if (!userService.addUser(user)){
            model.addAttribute("usernameError", "User already exists");
            return "registration";
        }

        return "redirect:/login";
    }


    @GetMapping("/activate/{code}")
    public String activateCode(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "Mail has been successfully activated");
            model.addAttribute("messageType", "success");
        }
        else {
            model.addAttribute("message", "The activation code is not valid");
            model.addAttribute("messageType", "danger");
        }

        return "login";
    }
}
