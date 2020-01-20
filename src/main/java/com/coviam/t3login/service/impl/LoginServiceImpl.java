package com.coviam.t3login.service.impl;

import com.coviam.t3login.entity.Login;
import com.coviam.t3login.repository.LoginRepository;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login save(Login login){
        return loginRepository.save(login);

    }

    @Override
    public ArrayList<Login> getAll() {
        return (ArrayList<Login>) loginRepository.findAll();
    }

    @Override
    public String findPass(String email) {
        Optional<Login>  optionalLogin= loginRepository.findById(email);
        Login login=optionalLogin.get();
        return login.getPassword();
    }
}
