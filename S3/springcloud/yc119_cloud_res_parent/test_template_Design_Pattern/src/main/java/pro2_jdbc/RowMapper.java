package pro2_jdbc;

import java.sql.ResultSet;

/**
 * ORM映射定制化的接口
 */
public interface RowMapper<T> {

    /**
     * 从resultset中取出一行，第rowNum行     rs.next()  -> rowNum次，行到这一行.
     * ，将这一行转为一个T
     * @param rs
     * @param rowNum
     * @return
     * @throws Exception
     */
    T mapRow(ResultSet rs,int rowNum) throws Exception;
}
