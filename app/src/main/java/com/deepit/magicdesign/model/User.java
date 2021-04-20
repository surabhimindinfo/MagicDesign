package com.deepit.magicdesign.model;

public class User {

    private static  User user = null;
    private User() {
    }
    // static method to create instance of Singleton class
    public static User getInstance()
    {
        if (user == null)
            user = new User();

        return user;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }
}
