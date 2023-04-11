package com.example.WeSocial.domain.util;

import com.example.WeSocial.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User user) {
        return user != null ? user.getUsername() : "<none>";
    }
}
