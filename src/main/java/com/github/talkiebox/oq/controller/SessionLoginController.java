package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.dto.LoginRequest;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import com.github.talkiebox.oq.service.UserAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class SessionLoginController {

    private final UserAccountService userAccountService;

    @GetMapping(value = "/")
    public String home(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "OQ");

        UserAccount loginUser = userAccountService.getLoginUserById(userId);

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
        } else {
            return "redirect:/login";
        }

        return "home";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "OQ");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "OQ");

        UserAccount userAccount = userAccountService.login(loginRequest);

        if (userAccount == null) {
            bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
        }

        if (bindingResult.hasErrors()) {
            return "login";
        }

        // 기존세션 파기
        httpServletRequest.getSession().invalidate();
        // 세션이 없으면 생성
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("userId", userAccount.getId());
        // 30분 유지
        session.setMaxInactiveInterval(1800);

        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "OQ");

        // Session이 없으면 null return
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }


}
