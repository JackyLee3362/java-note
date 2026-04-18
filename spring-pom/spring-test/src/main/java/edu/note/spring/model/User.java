package edu.note.spring.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户领域对象
 * 
 * @author jackylee
 * @date 2025-12-08 16:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    private Integer age;

    private List<String> address;

    private Map<String, String> info;

}
