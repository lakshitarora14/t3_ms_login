package com.coviam.t3login.service.impl;

import com.coviam.t3login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginHistoryServiceImpl {

    @Autowired
    private LoginRepository loginRepository;

    private void pass(){
        String password = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
    }


    public static void main(String[] args) {

    LoginHistoryServiceImpl l = new LoginHistoryServiceImpl();
    l.pass();
    }
}
