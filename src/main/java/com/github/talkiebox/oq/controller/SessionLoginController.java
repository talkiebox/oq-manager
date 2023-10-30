package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.dto.LoginRequest;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import com.github.talkiebox.oq.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class SessionLoginController {

    private final UserAccountService userAccountService;

    @GetMapping(value = "/")
    public String home(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageNmae", "세션 로그인");

        UserAccount loginUser = userAccountService.getLoginUserById(userId);

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
        }

        return "home";
    }

    @GetMapping(value = "login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageNmae", "세션 로그인");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }


}
