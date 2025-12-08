package edu.note;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @author jackylee
 * @date 2025-12-08 16:50
 */
@Getter
@Component
public class MyDataBaseConfig {

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

}
