package com.github.talkiebox.oq.service;

import com.github.talkiebox.oq.domain.dto.PotLogRequest;
import com.github.talkiebox.oq.repository.LoginLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;

    public void saveLog(PotLogRequest req) {
        loginLogRepository.save(req.toEntity());
    }
}
