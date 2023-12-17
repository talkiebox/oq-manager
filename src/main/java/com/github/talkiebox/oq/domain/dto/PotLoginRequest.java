package com.github.talkiebox.oq.domain.dto;

import com.github.talkiebox.oq.domain.entity.LoginLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PotLoginRequest {

    private String loginId;
    private String password;
    private String ipAddr;
    private String autopotVersion;
}
