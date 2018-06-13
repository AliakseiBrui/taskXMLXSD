package com.test.task4.pool;

import com.epam.task4.pool.ConnectionPool;
import org.testng.annotations.Test;

import java.sql.Connection;

public class ConnectionPoolTest {

    @Test
    public void testConnectionPoolWork(){
        ConnectionPool.INSTANCE.init();
        Connection connection = ConnectionPool.INSTANCE.takeConnection();
        System.out.println(connection);
        ConnectionPool.INSTANCE.returnConnection(connection);
        ConnectionPool.INSTANCE.closeAll();
    }
}
