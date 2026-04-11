package edu.note.java.reflect;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-04-04 17:04
 */
public class Reflect01_forName {

    public static interface Cloud {
    }

    private static class FooCloud implements Cloud {
    }

    private static class BarCloud implements Cloud {
    }

    @Test
    @DisplayName("测试 forName")
    void test_basic_01() throws ClassNotFoundException {
        // given:
        // when:
        Class<?> cloud = Class.forName(Cloud.class.getName());
        Class<?> fooCloud = Class.forName(FooCloud.class.getName());
        Class<?> barCloud = Class.forName(BarCloud.class.getName());
        // then:
        System.out.println(cloud);
        System.out.println(fooCloud);
        System.out.println(barCloud);
    }

}
