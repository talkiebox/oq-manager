package com.github.talkiebox.oq.model;

import lombok.Data;

import java.util.Date;

@Data
public class AccountTestVO {
    private int userId;
    private String username;
    private String userPwd;
    private Date createDate;
    private Date expireDate;
    private Date lastLogin;
}
