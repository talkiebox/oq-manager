package com.github.talkiebox.oq.repository;

import com.github.talkiebox.oq.model.AccountTestVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTestMapper {
    List<AccountTestVO> selectAccountTestList();
}
