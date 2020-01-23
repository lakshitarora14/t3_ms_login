package com.coviam.t3login.service.impl;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;
import com.coviam.t3login.entity.LoginHistory;
import com.coviam.t3login.repository.LoginRepository;
import com.coviam.t3login.service.LoginHistoryService;
import com.coviam.t3login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginHistoryService loginHistoryService;

    private static String pass(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


//    @Cacheable
    @Override
    public Login signup(@RequestBody SignupDto1 signupDto1) {
        Login login=new Login();
//        List<Login> list=(ArrayList<Login>)loginRepository.findAll();
//        list = list.stream().filter(login1 -> login1.getEmail().equals(signupDto1.getEmail())).collect(Collectors.toList());
//        if (list.size()!=0) {
//            String uid=list.stream().collect(Collectors.toList()).get(0).getUid();
//            return null;   //false
//        }
//        else {
        BeanUtils.copyProperties(signupDto1, login);
        login.setPassword(pass(signupDto1.getPassword()));
            System.out.println("-------------------"+signupDto1.getLoginType());
            return loginRepository.save(login);
//        }
    }
    @CacheEvict(value = "user",key = "#email")
    public void evictCacheForKey(@RequestParam("email") String email) {
    }

    @Cacheable(value = "user", key = "#loginDto.email")
    @Override
    public String login(@RequestBody LoginDto loginDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Login login = new Login();
        String email = loginDto.getEmail();

        String newPass = loginDto.getPassword();
        System.out.println(loginDto);
        List<Login> list=(ArrayList<Login>)loginRepository.findAll();
        list = list.stream().filter(login1 -> login1.getEmail().equals(loginDto.getEmail())).collect(Collectors.toList());
        if (list.size()!=0) {
            Login loginServicePass = loginRepository.findPasswordByEmail(email);
            if(passwordEncoder.matches(newPass,loginServicePass.getPassword())){
                LoginHistory loginHistory=new LoginHistory();
                loginHistory.setUid(loginServicePass.getUid());
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date dateobj = new Date();
                loginHistory.setTimeStamp(df.format(dateobj));
                loginHistoryService.save(loginHistory);
                return list.get(0).getUid();
            }
            return "Wrong Password";

        }
        else {
            return "Not registered";
        }

    }


    @PostMapping
    public String testMethod(){
        return "Working";
    }

    @Override
    @Cacheable(value = "user", key = "#login.email")
    public Login save(Login login){
        return loginRepository.save(login);

    }

    @Override
    public ArrayList<Login> getAll() {
        return (ArrayList<Login>) loginRepository.findAll();
    }

//    @Override
//    @CacheEvict(value = "userSubscription",key = "#email")
//    public void evictCacheForKey(String email) {
//    }

    @Override
    public Login findPass(String email) {

        System.out.println("----------"+email);
        ArrayList<Login> loginArrayList=(ArrayList<Login>)loginRepository.findAll();
        return  loginArrayList.stream().filter(login -> email.equals(login.getEmail())).collect(Collectors.toList()).get(0);
    }
}
