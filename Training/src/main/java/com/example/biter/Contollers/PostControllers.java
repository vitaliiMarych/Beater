package com.example.biter.Contollers;

import com.example.biter.Domain.Message;
import com.example.biter.Domain.User;
import com.example.biter.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class PostControllers {
    @Autowired
    private MessageService messageService;

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
            ) throws IOException
    {
        if (bindingResult.hasErrors()){
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            model.addAttribute("writtenMessage", message);
        }

        else {
            messageService.addMessage(message, user, file);
            model.addAttribute("writtenMessage", null);
        }

        Iterable<Message> messages = messageService.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("/user-messages/{userId}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId,
            @RequestParam("id") Long messageId,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = messageService.findById(messageId);

        if (message.getAuthor().equals(currentUser)){
            if (!text.isEmpty()) {
                message.setText(text);
            }

            if (!tag.isEmpty()) {
                message.setTag(tag);
            }
            messageService.saveFile(message, file);

            messageService.save(message);
        }


        return "redirect:/user-messages/" + userId;
    }

}
