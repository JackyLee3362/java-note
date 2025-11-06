package edu.note.mybatis.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;

/**
 * @author jackylee
 * @date 2025-09-29 13:53
 */
public class ExampleDatabaseIdProvider implements DatabaseIdProvider {

    private String getDatabaseProductName(DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            return metaData.getDatabaseProductName();
        }
    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        return getDatabaseProductName(dataSource);
    }

}
