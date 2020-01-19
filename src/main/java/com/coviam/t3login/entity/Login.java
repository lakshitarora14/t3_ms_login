package com.coviam.t3login.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN")
@Getter
@Setter
public class Login {



    @Id
    @Column(name = "uid")
    private String uId;
    private String email;
    private String passwordHash;
    private boolean customerOrMerchant;
    private String cartId;


}
