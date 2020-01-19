package com.coviam.t3login.service.impl;

import com.coviam.t3login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginHistoryServiceImpl {

    @Autowired
    private LoginRepository loginRepository;


}
