package com.github.talkiebox.oq.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoginResponse {

    private String loginId;
    private String nickname;
    private LocalDate expireDate;
    private String msg;
    private boolean loginAvailable;
}
