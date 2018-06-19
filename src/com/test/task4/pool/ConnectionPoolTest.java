package com.test.task4.pool;

import com.epam.task4.pool.ConnectionPool;
import com.epam.task4.pool.SafeConnection;
import org.testng.annotations.Test;

public class ConnectionPoolTest {

    @Test
    public void testConnectionPoolWork(){
        SafeConnection connection = ConnectionPool.INSTANCE.takeConnection();
        System.out.println(connection);
        ConnectionPool.INSTANCE.returnConnection(connection);
        ConnectionPool.INSTANCE.closeAll();
    }
}
