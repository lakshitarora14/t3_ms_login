package com.coviam.t3login.controller;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto;
import com.coviam.t3login.entity.Login;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {

        if (loginService.findEmail(signupDto.getEmail()) != null) {
            return null;
        }

        else {
            Login login = new Login();
            BeanUtils.copyProperties(signupDto, login);
            Login employeeCreated = loginService.save(login);

            return new ResponseEntity<String>(employeeCreated.getUId(), HttpStatus.CREATED);
        }




    }
}
