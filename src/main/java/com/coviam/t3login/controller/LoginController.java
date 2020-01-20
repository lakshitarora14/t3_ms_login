package com.coviam.t3login.controller;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static String pass(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }



    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto1 signupDto1) {
        Login login=new Login();
        List<Login> list=loginService.getAll();

        if (list.size()!=0) {
            String uid=list.stream().filter(login1 -> login1.getEmail()==signupDto1.getEmail()).map(login1 -> login1.getUId()).collect(Collectors.toList()).get(0);
            return new ResponseEntity<String>(uid, HttpStatus.OK);
        }

        else {
            signupDto1.setPassword(pass(signupDto1.getPassword()));
            BeanUtils.copyProperties(signupDto1, login);
            Login userCreated = loginService.save(login);

            return new ResponseEntity<String>(userCreated.getUId(), HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/loginabc")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        /*if (loginService.findEmail(loginDto.getEmail()) == null) {
            return null;
        }*/

        //else {
            Login login = new Login();
            String email = loginDto.getEmail();
            String newPass = pass(loginDto.getPassword());
            String fetchPass = loginService.findPass(email);
            System.out.println(newPass);
            System.out.println(fetchPass);
            if(newPass.equals(fetchPass))
                return new ResponseEntity<String>(HttpStatus.OK);
            return null;


        //}
    }


    @PostMapping
    public String testMethod(){
        return "Working";
    }
}
