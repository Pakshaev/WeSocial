package com.example.WeSocial.domain;

import com.example.WeSocial.domain.util.MessageHelper;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long(text must not exceed 2048 characters)")
    private String text;
    @Length(max = 255, message = "Tag too long(text must not exceed 255 characters)")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    @ManyToMany
    @JoinTable(
            name = "message_likes",
            joinColumns = { @JoinColumn(name = "message_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> likes = new HashSet<>();

    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }
}