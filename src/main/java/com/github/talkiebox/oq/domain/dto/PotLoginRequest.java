package com.github.talkiebox.oq.domain.dto;

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
}
