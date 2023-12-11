package com.github.talkiebox.oq.repository;

import com.github.talkiebox.oq.domain.entity.UserAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserAccountRepositoryTest {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    @DisplayName("유저 만들기")
    void createUser() {
        /*
        given
         */
        UserAccount userAccount1 = UserAccount.builder().loginId("eee").nickname("EEE").build();
        UserAccount userAccount2 = UserAccount.builder().loginId("hhh").nickname("HHHHHHH").build();

        /*
        when
         */
        UserAccount result1 = userAccountRepository.save(userAccount1);
        UserAccount result2 = userAccountRepository.save(userAccount2);

        /*
        then
         */
        Assertions.assertThat(result1.getNickname()).isEqualTo(userAccount1.getNickname());
        Assertions.assertThat(result2.getNickname()).isEqualTo(userAccount2.getNickname());
    }

}