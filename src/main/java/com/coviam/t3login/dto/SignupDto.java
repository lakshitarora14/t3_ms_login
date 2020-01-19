package com.coviam.t3login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
//    private String uId;
    private String email;
    private String passwordHash;
    private boolean customerOrMerchant;
//    private String cartId;

}
