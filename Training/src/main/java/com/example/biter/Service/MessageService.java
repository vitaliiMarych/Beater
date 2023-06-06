package com.example.biter.Service;

import com.example.biter.Domain.Message;
import com.example.biter.Domain.User;
import com.example.biter.Repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + fileName));

            message.setFilename(fileName);
        }
    }

    public void addMessage(Message message, User user, MultipartFile file) throws IOException {

        message.setAuthor(user);

        saveFile(message, file);

        messageRepo.save(message);
    }

    public Iterable<Message> findAll() {
        return messageRepo.findAll();
    }

    public Page<Message> findByTagLike(String tag, Pageable pageable) {
        return messageRepo.findByTagLike(tag, pageable);
    }

    public Message findById(Long id){
        return messageRepo.findById(id).orElse(null);
    }

    public void save(Message message) {
        messageRepo.save(message);
    }
}
