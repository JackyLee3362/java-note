package edu.note.spring.bean.anno.importer;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author jackylee
 * @date 2025-10-05 15:26
 */
public class JdbcConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
}
