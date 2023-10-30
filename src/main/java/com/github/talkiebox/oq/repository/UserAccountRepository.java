package com.github.talkiebox.oq.repository;

import com.github.talkiebox.oq.domain.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
    Optional<UserAccount> findByLoginId(String loginId);
}
