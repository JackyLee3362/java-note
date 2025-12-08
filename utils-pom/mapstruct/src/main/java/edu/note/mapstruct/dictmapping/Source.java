package edu.note.mapstruct.dictmapping;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/1 17:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {

    private Long id;
    private Map<String, String> map;

}
