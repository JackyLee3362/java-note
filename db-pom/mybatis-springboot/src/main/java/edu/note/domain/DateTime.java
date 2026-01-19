package edu.note.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2026-01-19 19:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateTime {

    private Integer id;

    private Long timestamp;

    private LocalDateTime localDateTime;
    
    private Instant zonedDateTime;
    
    private Instant instant;

}