package com.example.biter.Domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 1024, message = "Message too long, length is more 1024 characters")
    private String text;

    @NotBlank(message = "Please fill the tag")
    @Length(max = 1024, message = "Tag too long, length is more 1024 characters")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Message(){

    }

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.author = user;

    }

    public String getAuthorName(){
        if (author == null)
            return "<none>";
        return author.getUsername();
    }

}
