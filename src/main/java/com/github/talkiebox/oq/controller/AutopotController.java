package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.dto.LoginRequest;
import com.github.talkiebox.oq.domain.dto.PotLogRequest;
import com.github.talkiebox.oq.domain.dto.PotLoginRequest;
import com.github.talkiebox.oq.domain.dto.PotLoginResponse;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import com.github.talkiebox.oq.service.LoginLogService;
import com.github.talkiebox.oq.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequestMapping(value = "/autopot")
@RestController
@RequiredArgsConstructor
public class AutopotController {

    private final UserAccountService userAccountService;
    private final LoginLogService loginLogService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody PotLoginRequest potLoginRequest) {

        System.out.println("LoginId: " + potLoginRequest.getLoginId());
        System.out.println("Password: " + potLoginRequest.getPassword());
        System.out.println("IpAddr: " + potLoginRequest.getIpAddr());
        System.out.println("AutopotVersion: " + potLoginRequest.getAutopotVersion());
        System.out.println("LocalDateTime: " + LocalDateTime.now());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLoginId(potLoginRequest.getLoginId());
        loginRequest.setPassword(potLoginRequest.getPassword());

        UserAccount userAccount = userAccountService.login(loginRequest);

        PotLoginResponse potLoginResponse = new PotLoginResponse();
        PotLogRequest potLogRequest = new PotLogRequest();

        if (userAccount == null) {
            potLoginResponse.setLoginAvailable(false);
            potLoginResponse.setMsg("아이디 또는 비밀번호가 틀렸습니다.");

            potLogRequest.setLoginSuccess(false);
            potLogRequest.setMsg("아이디 또는 비밀번호가 틀렸습니다.");
        } else if (userAccount.getExpireDate() != null && userAccount.getExpireDate().compareTo(LocalDate.now()) < 0) {
            potLoginResponse.setLoginAvailable(false);
            potLoginResponse.setMsg("사용기간이 만료되었습니다.");

            potLogRequest.setLoginSuccess(false);
            potLogRequest.setNickname(userAccount.getNickname());
            potLogRequest.setMsg("사용기간이 만료되었습니다.");
        } else {
            potLoginResponse.setLoginId(userAccount.getLoginId());
            potLoginResponse.setNickname(userAccount.getNickname());
            potLoginResponse.setExpireDate(userAccount.getExpireDate());
            potLoginResponse.setLoginAvailable(true);

            potLogRequest.setLoginSuccess(true);
            potLogRequest.setNickname(userAccount.getNickname());
        }

        potLogRequest.setLoginId(potLoginRequest.getLoginId());
        potLogRequest.setIpAddr(potLoginRequest.getIpAddr());
        potLogRequest.setAutopotVersion(potLoginRequest.getAutopotVersion());
        potLogRequest.setLoginDate(LocalDateTime.now());

        loginLogService.saveLog(potLogRequest);

        return ResponseEntity.ok(potLoginResponse);
    }

}
