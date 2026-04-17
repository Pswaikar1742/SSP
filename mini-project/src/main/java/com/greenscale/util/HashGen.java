package com.greenscale.util;

import org.mindrot.jbcrypt.BCrypt;

public class HashGen {
    public static void main(String[] args) {
        String plain = "adminpass";
        String hash = BCrypt.hashpw(plain, BCrypt.gensalt(12));
        System.out.println(hash);
    }
}
