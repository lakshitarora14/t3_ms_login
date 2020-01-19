package com.coviam.t3login.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "LOGINHISTORY")
@Getter
@Setter
public class LoginHistory {

    @Id
    @Column(name = "uid")
    private String uId;
    private Date timeStamp;

}
