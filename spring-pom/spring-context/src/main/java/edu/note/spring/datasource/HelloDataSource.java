package edu.note.spring.datasource;

import lombok.Data;

/**
 * @author jackylee
 * @date 2025-10-05 12:45
 */
@Data
public class HelloDataSource {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

}
