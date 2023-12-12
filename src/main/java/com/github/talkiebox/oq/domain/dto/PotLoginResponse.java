package com.github.talkiebox.oq.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PotLoginResponse {

    private String loginId;
    private String nickname;
    private LocalDate expireDate;
    private String msg;
    private boolean loginAvailable;
}
