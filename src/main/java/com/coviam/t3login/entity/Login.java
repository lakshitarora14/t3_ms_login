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
    private String Uid;
    private String  email;
    private String password;
    private boolean Cust_or_Merc;
//    private String cartId;





}
