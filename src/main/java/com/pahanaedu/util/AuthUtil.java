package com.pahanaedu.util;

import com.pahanaedu.business.user.model.User;

import jakarta.servlet.http.HttpSession;

public class AuthUtil {

    public static boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && "admin".equalsIgnoreCase(user.getRole());
    }
}
