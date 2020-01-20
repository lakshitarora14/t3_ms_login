package com.coviam.t3login.service.impl;

import com.coviam.t3login.entity.Login;
import com.coviam.t3login.repository.LoginRepository;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    @Cacheable(value = "userSubscription", key = "#login.email")
    public Login save(Login login){
        return loginRepository.save(login);

    }

    @Override
    public ArrayList<Login> getAll() {
        return (ArrayList<Login>) loginRepository.findAll();
    }

    @Override
    public Login findPass(String email) {
        ArrayList<Login> loginArrayList=(ArrayList<Login>)loginRepository.findAll();
        return  loginArrayList.stream().filter(login -> email.equals(login.getEmail())).collect(Collectors.toList()).get(0);
    }
}
