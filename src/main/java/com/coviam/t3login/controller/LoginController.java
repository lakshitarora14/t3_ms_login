package com.coviam.t3login.controller;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;
import com.coviam.t3login.entity.LoginHistory;
import com.coviam.t3login.service.LoginHistoryService;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginHistoryService loginHistoryService;

//    private static String pass(String password){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.encode(password);
//    }



    @PostMapping(value = "/signup")
    public String signup(@RequestBody SignupDto1 signupDto1) {
        return loginService.signup(signupDto1);
    }
    @PostMapping(value = "/signout")
    public void evictAllCacheValues(@RequestParam("email") String email) {
        loginService.evictCacheForKey(email);
    }
    @PostMapping(value = "/login")
    public String login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }
//
//    @PostMapping(value = "/signup")
//    public String signup(@RequestBody SignupDto1 signupDto1) {
//        Login login=new Login();
//        List<Login> list=loginService.getAll();
//        list = list.stream().filter(login1 -> login1.getEmail().equals(signupDto1.getEmail())).collect(Collectors.toList());
//        if (list.size()!=0) {
//            //String uid=list.stream().collect(Collectors.toList()).get(0).getUid();
//            return null;   //false
//        }
//        else {
//            signupDto1.setPassword(pass(signupDto1.getPassword()));
//            BeanUtils.copyProperties(signupDto1, login);
//            login.setLoginType(signupDto1.getLoginType());
//            System.out.println("-------------------"+signupDto1.getLoginType());
//            Login userCreated = loginService.save(login);
//            return userCreated.getUid();
//        }
//    }
//
//    @PostMapping(value = "/signout")
//    public void evictAllCacheValues(@RequestParam("email") String email) {
//        loginService.evictCacheForKey(email);
////        Boolean aBoolean = template.hasKey(email);
//    }
//
//    @PostMapping(value = "/login")
//    public String login(@RequestBody LoginDto loginDto) {
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        Login login = new Login();
//        String email = loginDto.getEmail();
//
//        String newPass = loginDto.getPassword();
//        System.out.println(loginDto);
//        List<Login> list=loginService.getAll();
//        list = list.stream().filter(login1 -> login1.getEmail().equals(loginDto.getEmail())).collect(Collectors.toList());
//        if (list.size()!=0) {
//            //String uid=list.stream().collect(Collectors.toList()).get(0).getUid();
//            Login loginServicePass = loginService.findPasswordByEmail(email);
////        System.out.println(newPass);
////        System.out.println(loginServicePass.getPassword());
//            if(passwordEncoder.matches(newPass,loginServicePass.getPassword())){
//                LoginHistory loginHistory=new LoginHistory();
//                loginHistory.setUid(loginServicePass.getUid());
//                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//                Date dateobj = new Date();
//                loginHistory.setTimeStamp(df.format(dateobj));
//                loginHistoryService.save(loginHistory);
//                return "{\"response\":\""+list.get(0).getUid()+"\"}";
//            }
//            return "{\"response\":\"Wrong Password\"}";
//
//        }
//        else {
//           return "{\"response\":\"Not registered\"}";
//        }
//
//    }
//
//
//    @PostMapping
//    public String testMethod(){
//        return "Working";
//    }
}
