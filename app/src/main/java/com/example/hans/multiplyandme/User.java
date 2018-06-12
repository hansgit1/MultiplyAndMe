package com.example.hans.multiplyandme;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {


    public String username;
    public int score;

    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public User() {

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("score", score);
        return result;
    }

}
