package com.coviam.t3login.controller;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;
import com.coviam.t3login.entity.LoginHistory;
import com.coviam.t3login.service.LoginHistoryService;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginHistoryService loginHistoryService;
    private static String pass(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }



    @PostMapping(value = "/signup")
    public String signup(@RequestBody SignupDto1 signupDto1) {
        Login login=new Login();
        List<Login> list=loginService.getAll();
        list = list.stream().filter(login1 -> login1.getEmail().equals(signupDto1.getEmail())).collect(Collectors.toList());
        if (list.size()!=0) {
            //String uid=list.stream().collect(Collectors.toList()).get(0).getUid();
            return null;   //false
        }
        else {
            signupDto1.setPassword(pass(signupDto1.getPassword()));
            BeanUtils.copyProperties(signupDto1, login);
            login.setCust_or_Merc(signupDto1.isCust_or_Merc());
            System.out.println("-------------------"+signupDto1.isCust_or_Merc());
            Login userCreated = loginService.save(login);
            return userCreated.getUid();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
    /*if (loginService.findEmail(loginDto.getEmail()) == null) {
        return null;
    }*/
        //else {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Login login = new Login();
        String email = loginDto.getEmail();

        String newPass = loginDto.getPassword();

        Login loginServicePass = loginService.findPass(email);
        System.out.println(newPass);
        System.out.println(loginServicePass.getPassword());
        if(passwordEncoder.matches(newPass,loginServicePass.getPassword())){
//        if(newPass.equals(loginServicePass.getPassword())) {
            LoginHistory loginHistory=new LoginHistory();
            loginHistory.setUid(loginServicePass.getUid());
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date dateobj = new Date();
            //System.out.println(df.format(dateobj));
            loginHistory.setTimeStamp(df.format(dateobj));
            loginHistoryService.save(loginHistory);
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        }
        else
            return null;
        //}
    }


    @PostMapping
    public String testMethod(){
        return "Working";
    }
}
