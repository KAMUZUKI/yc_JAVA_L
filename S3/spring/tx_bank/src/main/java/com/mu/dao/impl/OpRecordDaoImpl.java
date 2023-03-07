package com.mu.dao.impl;

import com.mu.dao.OpRecordDao;
import com.mu.pojo.OpRecord;
import com.mu.pojo.OpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 19:11
 **/
@Repository
public class OpRecordDaoImpl implements OpRecordDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord(accountid,opmoney,opTime,optype,transferid) values(?,?,now(),?,?)";
        this.jdbcTemplate.update(sql,opRecord.getAccountid(),opRecord.getOpmoney(),opRecord.getOpType().getKey(),opRecord.getTransferid());
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid) {
        List<OpRecord> list = this.jdbcTemplate.query(
                "select * from oprecord where accountid=? order by optime desc",
                (resultSet, rowNum) -> {
                    OpRecord opRecord = new OpRecord();
                    opRecord.setId(resultSet.getInt("id"));
                    opRecord.setAccountid(resultSet.getInt("accountid"));
                    opRecord.setOpmoney(resultSet.getDouble("opmoney"));
                    opRecord.setOptime(resultSet.getString("optime"));
                    opRecord.setTransferid(resultSet.getInt("transferid"));
                    String optype = resultSet.getString("optype");
                    if (optype.equalsIgnoreCase("withdraw")){
                        opRecord.setOpType(OpType.WITHDRAW);
                    }else if (optype.equalsIgnoreCase("deposite")) {
                        opRecord.setOpType(OpType.DEPOSITE);
                    }else{
                        opRecord.setOpType(OpType.TRANSFER);
                    }
                    opRecord.setTransferid(resultSet.getInt("transferid"));
                    return opRecord;
                }, accountid);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid, String opType) {
        List<OpRecord> list = this.jdbcTemplate.query(
                "select * from oprecord where accountid=? and optype=? order by optime desc",
                (resultSet, rowNum) -> {
                    OpRecord opRecord = new OpRecord();
                    opRecord.setId(resultSet.getInt("id"));
                    opRecord.setAccountid(resultSet.getInt("accountid"));
                    opRecord.setOpmoney(resultSet.getDouble("opmoney"));
                    opRecord.setOptime(resultSet.getString("optime"));
                    opRecord.setTransferid(resultSet.getInt("transferid"));
                    String optype = resultSet.getString("optype");
                    if ("withdraw".equalsIgnoreCase(optype)){
                        opRecord.setOpType(OpType.WITHDRAW);
                    }else if ("deposite".equalsIgnoreCase(optype)) {
                        opRecord.setOpType(OpType.DEPOSITE);
                    }else{
                        opRecord.setOpType(OpType.TRANSFER);
                    }
                    opRecord.setTransferid(resultSet.getInt("transferid"));
                    return opRecord;
                }, accountid,opType);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(OpRecord opRecord) {
        return null;
    }
}
