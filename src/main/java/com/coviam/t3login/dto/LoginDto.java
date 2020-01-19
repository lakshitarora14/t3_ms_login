package com.coviam.t3login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {


    private String  email;
    private String password;
    private String cartId;
    private boolean customerOrMerchant;



}
