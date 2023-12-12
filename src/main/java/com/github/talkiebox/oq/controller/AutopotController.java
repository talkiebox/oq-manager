package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.dto.PotLoginRequest;
import com.github.talkiebox.oq.domain.dto.PotLoginResponse;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/autopot")
@RestController
@RequiredArgsConstructor
public class AutopotController {

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody PotLoginRequest potLoginRequest) {
        System.out.println(potLoginRequest.getLoginId());
        System.out.println(potLoginRequest.getPassword());

        PotLoginResponse potLoginResponse = new PotLoginResponse();
        potLoginResponse.setMsg("pot response test messsage.");

        return ResponseEntity.ok(potLoginResponse);
    }

}
