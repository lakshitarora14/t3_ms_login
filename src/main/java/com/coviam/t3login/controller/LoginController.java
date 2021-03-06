package com.coviam.t3login.controller;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto;
import com.coviam.t3login.entity.Login;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
//@RequestMapping("/book")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static String pass(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {

//        if (loginService.findEmail(signupDto.getEmail()) != null) {
//            return null;
//        }
//
//        else {
            signupDto.setPassword(pass(signupDto.getPassword()));
            Login login = new Login();
            BeanUtils.copyProperties(signupDto, login);
            Login userCreated = loginService.save(login);

            return new ResponseEntity<String>(userCreated.getUId(), HttpStatus.CREATED);
//        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        if (loginService.findEmail(loginDto.getEmail()) == null) {
            return null;
        }

        else {
            Login login = new Login();
            String email = loginDto.getEmail();
            String newPass = pass(loginDto.getPassword());
            String fetchPass = loginService.findPass(email);
            System.out.println(newPass);
            System.out.println(fetchPass);
            if(newPass.equals(fetchPass))
                return new ResponseEntity<String>(HttpStatus.OK);
            return null;


        }
    }

    @PostMapping
    public String testMethod(){
        return "Working";
    }
}
