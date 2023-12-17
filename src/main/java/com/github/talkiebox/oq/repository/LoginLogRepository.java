package com.github.talkiebox.oq.repository;

import com.github.talkiebox.oq.domain.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

}
