package com.github.talkiebox.oq.domain.entity;

import com.github.talkiebox.oq.domain.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;
    private String nickname;
    private String userRole;
    private LocalDate createDate;
    private LocalDate expireDate;

    public UserAccount(String loginId, String nickname) {
        this.loginId = loginId;
        this.nickname = nickname;
    }
}
