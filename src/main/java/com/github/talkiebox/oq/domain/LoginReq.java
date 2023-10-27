package com.github.talkiebox.oq.domain;

import lombok.Data;

@Data
public class LoginReq {
    private String userId;
    private String userPassword;
}
