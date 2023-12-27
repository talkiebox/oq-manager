package com.github.talkiebox.oq.service;

import com.github.talkiebox.oq.domain.dto.PotLogRequest;
import com.github.talkiebox.oq.domain.entity.LoginLog;
import com.github.talkiebox.oq.domain.entity.UserAccount;
import com.github.talkiebox.oq.repository.LoginLogRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;
    private final EntityManager entityManager;

    public void saveLog(PotLogRequest req) {
        loginLogRepository.save(req.toEntity());
    }

    public List<LoginLog> logs() {
        return loginLogRepository.findAllByOrderByIdDesc();
    }
}
