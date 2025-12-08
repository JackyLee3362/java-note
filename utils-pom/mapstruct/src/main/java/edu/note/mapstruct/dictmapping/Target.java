package edu.note.mapstruct.dictmapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/1 17:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Target {

    private Long id;
    private String host;
    private String port;

}
