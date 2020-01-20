package com.coviam.t3login.entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "LOGIN")
public class Login {

    @GeneratedValue(generator = "uid")
    @GenericGenerator(
            name = "uid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Id
    @Column(name = "uid")
    private String uId;
    private String email;
    private boolean customerOrMerchant;
    private String name;
    private String phone;
    private String password;
    private String pincode;
//    private String cartId;





}
