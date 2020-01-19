package com.coviam.t3login.service;

import com.coviam.t3login.entity.Login;

import java.util.Optional;

public interface LoginService {
    Login save(Login login);

    String findEmail(String email);
    String findPass(String passwordHash);



}
