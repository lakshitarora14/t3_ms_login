package com.coviam.t3login.service.impl;

import com.coviam.t3login.entity.LoginHistory;
import com.coviam.t3login.repository.LoginHistoryRepository;
import com.coviam.t3login.repository.LoginRepository;
import com.coviam.t3login.service.LoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Cacheable;


@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Override
    public void save(LoginHistory loginHistory) {
        loginHistoryRepository.save(loginHistory);
    }
}
