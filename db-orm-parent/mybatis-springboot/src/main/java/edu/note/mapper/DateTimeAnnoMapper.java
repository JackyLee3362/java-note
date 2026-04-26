package edu.note.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import edu.note.domain.DateTime;

/**
 * @author jackylee
 * @date 2026-01-19 19:06
 */
@Mapper
public interface DateTimeAnnoMapper {
    
    @Insert("INSERT INTO datetime(timestamp, local_date_time, zoned_date_time, instant) VALUES (#{timestamp}, #{localDateTime}, #{zonedDateTime}, #{instant})")
    int insert(DateTime po);
     
}
