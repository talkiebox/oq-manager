package com.github.talkiebox.oq.service;

import com.github.talkiebox.oq.domain.dto.JoinRequest;
import com.github.talkiebox.oq.domain.dto.LoginRequest;
import com.github.talkiebox.oq.domain.dto.UpdateRequest;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import com.github.talkiebox.oq.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final EntityManager entityManager;

    // Spring Security를 사용한 로그인 구현 시 사용
    // private final BCryptPasswordEncoder encoder;

    // id 중복체크
    public boolean checkLoginIdDuplicate(String loginId) {
        return userAccountRepository.existsByLoginId(loginId);
    }

    // nickname 중복체크
    public boolean checkNicknameDuplicate(String nickname) {
        return userAccountRepository.existsByNickname(nickname);
    }

    // 암호화 X 회원가입
    public void register(JoinRequest req) {
        userAccountRepository.save(req.toEntity());
    }

    // 암호화 회원가입
    public void join2(JoinRequest req) {
        // TODO: 암호화 회원가입 구현해야함
    }

    public List<UserAccount> users() {
        return userAccountRepository.findAll();
    }

    public UserAccount login(LoginRequest req) {
        Optional<UserAccount> optionalUser = userAccountRepository.findByLoginId(req.getLoginId());

        if (optionalUser.isEmpty()) {
            return null;
        }

        UserAccount userAccount = optionalUser.get();

        if (!userAccount.getPassword().equals(req.getPassword())) {
            return null;
        }

        return userAccount;
    }

    public UserAccount getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<UserAccount> optionalUser = userAccountRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public UserAccount getLoginUserByLoginId(String loginId) {
        if (loginId == null) {
            return null;
        }

        Optional<UserAccount> optionalUser = userAccountRepository.findByLoginId(loginId);
        if (optionalUser.isEmpty()) {
            return null;
        }

        return optionalUser.get();
    }

    public UserAccount update(UpdateRequest req) {

        System.out.println("getLoginId: " + req.getLoginId());
        System.out.println("getNickname: " + req.getNickname());

        UserAccount userAccount = userAccountRepository.findByLoginIdNoOpt(req.getId());
        userAccount.setLoginId(req.getLoginId());
        userAccount.setPassword(req.getPassword());
        userAccount.setNickname(req.getNickname());
        userAccount.setExpireDate(req.getExpireDate());

        return userAccount;
    }

}
