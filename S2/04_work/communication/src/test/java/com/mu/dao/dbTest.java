package com.mu.dao;

import org.junit.jupiter.api.Test;

/**
 * @author : MUZUKI
 * @date : 2022-10-24 19:50
 **/

public class dbTest {
    @Test
    public void test() {
        DbHelper db = new DbHelper();
        db.select("select * from testuser");
    }
}
