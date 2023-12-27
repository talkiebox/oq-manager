package com.github.talkiebox.oq.repository;

import com.github.talkiebox.oq.domain.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

    List<LoginLog> findAllByOrderByIdDesc();

}
