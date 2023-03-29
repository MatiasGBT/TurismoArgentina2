package com.mgbt.turismoargentina_backend.utility_classes;

import com.mgbt.turismoargentina_backend.model.entity.User;

public class JsonUserMessage {
    private String message;
    private User user;

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
