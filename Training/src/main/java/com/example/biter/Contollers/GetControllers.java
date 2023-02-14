package com.example.biter.Contollers;

import com.example.biter.Domain.Message;
import com.example.biter.Repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GetControllers {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Map<String, Object> model
    ){
        Iterable<Message> messages = messageRepo.findByTagLike("%" + filter + "%");

        model.put("messages", messages);
        model.put("filter", filter);
        return "main";
    }
}
