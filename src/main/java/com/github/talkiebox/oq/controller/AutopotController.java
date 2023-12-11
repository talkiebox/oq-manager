package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/autopot")
@RestController
@RequiredArgsConstructor
public class AutopotController {

    @PostMapping(value = "/login")
    public UserAccount login(@RequestBody UserAccount userAccount) {
        System.out.println(userAccount.getLoginId());
        System.out.println(userAccount.getPassword());

        return userAccount;
    }

}
