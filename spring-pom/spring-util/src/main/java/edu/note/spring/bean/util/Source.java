package edu.note.spring.bean.util;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025-12-08 16:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    private Long id;
    private String name;
    private Integer age;
    private List<String> address;
    private Map<String, String> info;
}
