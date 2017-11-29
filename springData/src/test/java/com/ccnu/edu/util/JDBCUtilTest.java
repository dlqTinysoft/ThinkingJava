package com.ccnu.edu.util;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class JDBCUtilTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection conn = JDBCUtil.getConnection();
        Assert.assertNotNull(conn);
    }














}
