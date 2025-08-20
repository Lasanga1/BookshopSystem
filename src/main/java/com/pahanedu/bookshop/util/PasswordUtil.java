package com.pahanedu.bookshop.util;

public class PasswordUtil {

    public static String hashPassword(String password) {
        // No hashing, just return the plain password
        return password;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        // Direct comparison
        return password != null && password.equals(hashedPassword);
    }
}