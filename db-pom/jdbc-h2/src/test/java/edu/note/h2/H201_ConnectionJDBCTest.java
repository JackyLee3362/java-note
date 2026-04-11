package edu.note.h2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * h2 默认用户名是 sa(System Administrator)，密码为空
 * 
 * @author jackylee
 * @date 2025-09-30 08:38
 */
@Slf4j
public class H201_ConnectionJDBCTest {

    @Test
    @DisplayName("测试使用JDBC H2 内存数据库")
    void testH2() throws ClassNotFoundException, SQLException {
        // 1. 加载驱动(使用 SPI 机制自动加载)
        Class.forName(Driver.class.getName());
        // 2. 获取连接(内存数据库)
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pwd = "";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        // 3. 操作数据库
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE IF EXISTS user");
        stmt.execute("CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY, name VARCHAR(32), city VARCHAR(32))");
        stmt.execute("INSERT INTO user (id, name, city) VALUES (1 ,'Foo', 'SH'),(2, 'Bar', 'BJ'),(3, 'Baz', 'SH')");
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        // 4. 处理数据
        while (rs.next()) {
            log.info("{} {} {}", rs.getInt(1), rs.getString("name"), rs.getString("city"));
        }
        // 5. 关闭资源: Statement 是数据库资源，使用完毕后需要关闭
        // Q: 为什么使用数据库客户端没有显示关闭 Statement 资源?
        // A: 因为数据库客户端会自动管理资源，执行完 SQL 语句后会自动关闭 Statement 资源，而在 JDBC 中需要手动关闭。
        // Q: 如果不释放 Statement 资源会有什么问题?
        // A: 如果不释放 Statement 资源，可能会导致内存泄漏和数据库连接池资源耗尽，最终导致应用程序崩溃或无法连接数据库。
        stmt.close();
        // 6. 关闭连接
        conn.close();
    }

}
