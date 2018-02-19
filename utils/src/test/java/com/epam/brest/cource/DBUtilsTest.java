package com.epam.brest.cource;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DBUtilsTest {

    @Test
    public void getConnection() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }
}