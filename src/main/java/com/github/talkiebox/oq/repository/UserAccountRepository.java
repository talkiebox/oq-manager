package com.github.talkiebox.oq.repository;

import com.github.talkiebox.oq.domain.entity.UserAccount;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
    Optional<UserAccount> findByLoginId(String loginId);

    @Query(value = "select * from user_account where id = :id", nativeQuery = true)
    UserAccount findByLoginIdNoOpt(@Param("id") Long id);
}
