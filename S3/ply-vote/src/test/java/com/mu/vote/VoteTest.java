package com.mu.vote;

import com.mu.vote.domain.TpVote;
import com.mu.vote.mapper.TpVoteMapper;
import com.mu.vote.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-12-27 15:55
 **/

public class VoteTest {
    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        TpVoteMapper tpVoteMapper = sqlSession.getMapper(TpVoteMapper.class);
        List<TpVote> tpVotes = tpVoteMapper.selectAll();
        for (TpVote tpVote : tpVotes) {
            System.out.println(tpVote.getVname());
        }
    }
}
