package com.github.talkiebox.oq.service;

import com.github.talkiebox.oq.model.AccountTestVO;
import com.github.talkiebox.oq.repository.AccountTestMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountTestServiceImpl implements AccountTestService {

    private final SqlSession sqlSession;

    public AccountTestServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Transactional(readOnly = true)
    public List<AccountTestVO> selectAccountTestList() {
        AccountTestMapper atm = sqlSession.getMapper(AccountTestMapper.class);
        return atm.selectAccountTestList();
    }
}
