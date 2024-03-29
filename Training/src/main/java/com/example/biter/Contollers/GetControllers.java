package com.example.biter.Contollers;

import com.example.biter.Domain.Message;
import com.example.biter.Domain.User;
import com.example.biter.Repos.MessageRepo;
import com.example.biter.Service.MessageService;
import com.example.biter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

@Controller
public class GetControllers {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Map<String, Object> model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
            ){
        Page<Message> page = messageService.findByTagLike("%" + filter + "%", pageable);

        model.put("page", page);
        model.put("url", "/main");
        model.put("filter", filter);
        return "main";
    }

    @GetMapping("/user-messages/{userId}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId,
            Model model,
            @RequestParam(required = false) Message message
    ){

        User user = userService.findById(userId);
        model.addAttribute("messages", user.getMessages());
        model.addAttribute("authorId", userId);
        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());

        boolean isSubsriber = false;
        for (User user1 : user.getSubscribers())
            if (Objects.equals(user1.getId(), currentUser.getId())) {
                isSubsriber = true;
                break;
            }
        model.addAttribute("isSubsriber", isSubsriber);

        if (message != null)
            model.addAttribute("writtenMessage", message);

        return "userMessages";
    }
}
