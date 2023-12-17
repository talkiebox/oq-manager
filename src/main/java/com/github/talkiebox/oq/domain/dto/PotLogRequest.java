package com.github.talkiebox.oq.domain.dto;

import com.github.talkiebox.oq.domain.entity.LoginLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PotLogRequest {

    private String loginId;
    private String nickname;
    private String ipAddr;
    private boolean loginSuccess;
    private String msg;
    private String autopotVersion;
    private LocalDateTime loginDate;

    public LoginLog toEntity() {
        return LoginLog.builder()
                .loginId(this.loginId)
                .nickname(this.nickname)
                .ipAddr(this.ipAddr)
                .loginSuccess(this.loginSuccess)
                .msg(this.msg)
                .autopotVersion(this.autopotVersion)
                .loginDate(this.loginDate)
                .build();
    }
}
