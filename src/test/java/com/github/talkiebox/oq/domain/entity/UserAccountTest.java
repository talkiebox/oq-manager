package com.github.talkiebox.oq.domain.entity;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class UserAccountTest {

    @Test
    @DisplayName("UserAccount 생성 테스트")
    void createUserAccount() {
        /*
        given
         */
        UserAccount userAccount = UserAccount.builder().loginId("eee").nickname("EEE").build();

        /*
        when, then
         */
        Assertions.assertThat(userAccount.getLoginId()).isEqualTo("eee");
        Assertions.assertThat(userAccount.getNickname()).isEqualTo("EEE");
    }

}