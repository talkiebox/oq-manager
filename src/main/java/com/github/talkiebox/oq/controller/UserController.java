package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.DefaultRes;
import com.github.talkiebox.oq.domain.LoginReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @PostMapping(path = "login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        return new ResponseEntity(DefaultRes.res(200, "Success", loginReq), HttpStatus.OK);
    }
}
