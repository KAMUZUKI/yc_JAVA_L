package com.mu.dao;

import com.mu.pojo.OpRecord;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 16:32
 **/

public interface OpRecordDao {
    void insertOpRecord(OpRecord opRecord);

    /**
     * 查询用户的所有操作记录，根据时间排序
     * @param accountid
     * @return
     */
    List<OpRecord> findOpRecord(int accountid);

    List<OpRecord> findOpRecord(int accountid, String opType);

    List<OpRecord> findOpRecord(OpRecord opRecord);
}
