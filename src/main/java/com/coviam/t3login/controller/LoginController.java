package com.coviam.t3login.controller;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;
import com.coviam.t3login.entity.LoginHistory;
import com.coviam.t3login.service.LoginHistoryService;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.redis.core.RedisTemplate;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.sun.tools.doclint.Entity.exist;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    RedisTemplate<SignupDto1, String> template;

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginHistoryService loginHistoryService;
    private static String pass(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

//    public void islogin(){
//
//        if (template.hasKey(lakshitarora@gmail.com)) {
//            System.out.println(“exist”);
//        } else {
//            System.out.println(“doesnt ex”);
//        }
//
//    }

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
//    @PostMapping(value = "/signout")
//    @Cacheable("deleteCustomer")
//    public void deleteCustomer() {
//
//        String id =  "lakshitarora@gmail.com";
//        template.execute(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.del(template.getStringSerializer().serialize(String.valueOf(id)));
//                return null;
//            }
//        });
//    }
//    @PostMapping(value = "/signout")
//    @CacheEvict(value = "userSubscription",key = "#login.email")
//    public void evictAllCacheValues() {}

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginDto loginDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Login login = new Login();
        String email = loginDto.getEmail();

        String newPass = loginDto.getPassword();
        System.out.println(loginDto);
        List<Login> list=loginService.getAll();
        list = list.stream().filter(login1 -> login1.getEmail().equals(loginDto.getEmail())).collect(Collectors.toList());
        if (list.size()!=0) {
            //String uid=list.stream().collect(Collectors.toList()).get(0).getUid();
            Login loginServicePass = loginService.findPass(email);
//        System.out.println(newPass);
//        System.out.println(loginServicePass.getPassword());
            if(passwordEncoder.matches(newPass,loginServicePass.getPassword())){
                LoginHistory loginHistory=new LoginHistory();
                loginHistory.setUid(loginServicePass.getUid());
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date dateobj = new Date();
                loginHistory.setTimeStamp(df.format(dateobj));
                loginHistoryService.save(loginHistory);
                return "Success";
            }
            return "Wrong Password";

        }
        else {
           return "User Not Registered";
        }

    }


    @PostMapping
    public String testMethod(){
        return "Working";
    }
}
