package com.coviam.t3login.service;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public interface LoginService {
    Login save(Login login);

    Login findPass(String passwordHash);
    ArrayList<Login> getAll();


    void evictCacheForKey(String email);


    String login(LoginDto loginDto);

    String signup(SignupDto1 signupDto1);
}
