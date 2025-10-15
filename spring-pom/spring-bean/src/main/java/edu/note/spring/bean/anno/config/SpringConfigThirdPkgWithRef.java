package edu.note.spring.bean.anno.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

import edu.note.spring.bean.anno.HelloDao;

/**
 * @author jackylee
 * @date 2025-10-05 15:45
 */
@Configuration
@ComponentScan("edu.note.spring.bean.anno")
@PropertySource("jdbc.properties") // 配合 @Value("${name}") 使用
public class SpringConfigThirdPkgWithRef {

    @Bean
    public DataSource dataSource(HelloDao helloDao) {
        DruidDataSource ds = new DruidDataSource();
        System.out.println(helloDao);
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

}
