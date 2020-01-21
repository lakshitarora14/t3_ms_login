package com.coviam.t3login.service;

import com.coviam.t3login.entity.LoginHistory;
import org.springframework.cache.annotation.Cacheable;


public interface LoginHistoryService {

    void save(LoginHistory loginHistory);
}
