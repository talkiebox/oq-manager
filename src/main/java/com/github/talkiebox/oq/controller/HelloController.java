package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.AccountTest;
import com.github.talkiebox.oq.service.AccountTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HelloController {

    private final AccountTestService accountTestService;

    public HelloController(AccountTestService accountTestService) {
        this.accountTestService = accountTestService;
    }

    @PostMapping("accountList")
    public ResponseEntity<List<AccountTest>> selectAccountTestList() {
        List<AccountTest> resultList = accountTestService.selectAccountTestList();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public String main(Model model) {
//        model.addAttribute("data", "Hello");
        return "index";
    }

}
