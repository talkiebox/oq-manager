package com.github.talkiebox.oq.controller;

import com.github.talkiebox.oq.domain.dto.JoinRequest;
import com.github.talkiebox.oq.domain.dto.LoginRequest;
import com.github.talkiebox.oq.domain.dto.UpdateRequest;
import com.github.talkiebox.oq.domain.entity.LoginLog;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import com.github.talkiebox.oq.service.LoginLogService;
import com.github.talkiebox.oq.service.UserAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/oq")
@Controller
@RequiredArgsConstructor
public class SessionLoginController {

    private final UserAccountService userAccountService;
    private final LoginLogService loginLogService;

    @GetMapping(value = {"", "/"})
    public String home(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("pageName", "OQ");

        UserAccount loginUser = userAccountService.getLoginUserById(userId);

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
            model.addAttribute("userRole", loginUser.getUserRole());
        } else {
            return "redirect:/oq/login";
        }

        return "home";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("pageName", "OQ");
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest httpServletRequest, Model model) {
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

        return "redirect:/oq";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model) {
        model.addAttribute("pageName", "OQ");

        // Session이 없으면 null return
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/oq";
    }

    @GetMapping(value = "/users")
    public String usersPage(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("pageName", "OQ");

        UserAccount loginUser = userAccountService.getLoginUserById(userId);

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
            model.addAttribute("userRole", loginUser.getUserRole());
        } else {
            return "redirect:/oq/login";
        }

        List<UserAccount> users = userAccountService.users();

        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping(value = "/logs")
    public String logsPage(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("pageName", "OQ");

        UserAccount loginUser = userAccountService.getLoginUserById(userId);

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
            model.addAttribute("userRole", loginUser.getUserRole());
        } else {
            return "redirect:/oq/login";
        }

        List<LoginLog> logs = loginLogService.logs();

        model.addAttribute("logs", logs);

        return "logs";
    }

    @GetMapping(value = "/register")
    public String registerPage(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("pageName", "OQ");
        model.addAttribute("joinRequest", new JoinRequest());

        UserAccount loginUser = userAccountService.getLoginUserById(userId);

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
            model.addAttribute("userRole", loginUser.getUserRole());
        } else {
            return "redirect:/oq/login";
        }

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@Valid @ModelAttribute JoinRequest joinRequest, BindingResult bindingResult, Model model) {
        model.addAttribute("pageName", "OQ");

        // loginId 중복 체크
        if(userAccountService.checkLoginIdDuplicate(joinRequest.getLoginId())) {
            bindingResult.addError(new FieldError("joinRequest", "loginId", "로그인 아이디가 중복됩니다."));
        }
        // 닉네임 중복 체크
        if(userAccountService.checkNicknameDuplicate(joinRequest.getNickname())) {
            bindingResult.addError(new FieldError("joinRequest", "nickname", "닉네임이 중복됩니다."));
        }

        if(bindingResult.hasErrors()) {
            return "redirect:/oq/register";
        }

        userAccountService.register(joinRequest);
        return "redirect:/oq/users";
    }

    @GetMapping(value = "/update")
    public String updatePage(@SessionAttribute(name = "userId", required = false) Long userId, @RequestParam Long id, Model model) {
        model.addAttribute("pageName", "OQ");

        UserAccount loginUser = userAccountService.getLoginUserById(userId);
//        model.addAttribute("updateRequest", new UpdateRequest());

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
            model.addAttribute("userRole", loginUser.getUserRole());
        } else {
            return "redirect:/oq/login";
        }

        UserAccount updateUser = userAccountService.getLoginUserById(id);

        System.out.println(updateUser.getNickname());
        model.addAttribute("updateUser", updateUser);

        return "update";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute UpdateRequest updateRequest) {

        UserAccount userAccount;

        userAccount = userAccountService.update(updateRequest);

        return "redirect:/oq/users";
    }

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute UpdateRequest updateRequest) {

        UserAccount userAccount;
        userAccountService.deleteById(updateRequest.getId());

        return "redirect:/oq/users";
    }


}
