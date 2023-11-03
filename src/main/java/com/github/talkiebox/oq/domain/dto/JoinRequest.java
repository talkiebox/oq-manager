package com.github.talkiebox.oq.domain.dto;

import com.github.talkiebox.oq.domain.UserRole;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String loginId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    private LocalDate createDate;
    private LocalDate expireDate;

    // 비밀번호 암호화 X
    public UserAccount toEntity() {
        return UserAccount.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .createDate(LocalDate.now())
                .expireDate(LocalDate.now().plusMonths(1))
                .userRole("USER")
                .build();
    }

    // 비밀번호 암호화
    public UserAccount toEntity(String encodedPassword) {
        return UserAccount.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .userRole("USER")
                .build();
    }
}
