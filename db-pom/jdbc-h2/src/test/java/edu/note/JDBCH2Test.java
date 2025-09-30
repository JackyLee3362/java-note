package edu.note;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-09-30 08:38
 */
public class JDBCH2Test {
    public static void main(String[] args) throws Exception {
        JDBCH2Test jdbch2Test = new JDBCH2Test();
        jdbch2Test.testH2();
    }

    @Test
    @DisplayName("测试JDBC H2 数据库")
    void testH2() throws ClassNotFoundException, SQLException {
        // 1. 加载驱动
        Class.forName(Driver.class.getName());
        // 2. 获取连接
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pwd = "";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        // 3. 操作数据库
        Statement stmt = conn.createStatement();
        String create = "DROP TABLE IF EXISTS user;"
                + "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), city VARCHAR(255));"
                + "INSERT INTO user (name, city) VALUES ('Foo', 'SH'),('Bar', 'BJ'),('Baz', 'SH');";
        String query = "SELECT * FROM user;";
        stmt.execute(create);
        ResultSet rs = stmt.executeQuery(query);
        // 4. 处理数据
        while (rs.next()) {
            System.out.println(rs.getInt(1)
                    + rs.getString("name")
                    + rs.getString("city"));
        }
        // 5. 关闭数据库
        stmt.close();
        conn.close();

    }

}
