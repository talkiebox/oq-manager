package com.github.talkiebox.oq.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRequest {

    private String loginId;
    private String password;
    private String nickname;
    private LocalDate expireDate;
}
