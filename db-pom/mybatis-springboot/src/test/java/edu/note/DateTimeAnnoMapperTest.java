package edu.note;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.note.domain.DateTime;
import edu.note.mapper.DateTimeAnnoMapper;

/**
 * 测试时间数据
 *
 * @author jackylee
 * @date 2026-01-19 19:11
 */
@SpringBootTest(classes = StartApp.class)
public class DateTimeAnnoMapperTest {

    @Resource
    private DateTimeAnnoMapper dateTimeAnnoMapper;

    @Test
    @DisplayName("测试插入数据")
    void test01() {
        DateTime datetime = new DateTime();
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        // ZonedDateTime zonedDateTime2 = ZonedDateTime.ofInstant(instant, ZoneId.of("Europe/Paris"));


        datetime.setTimestamp(instant.toEpochMilli());
        datetime.setLocalDateTime(localDateTime);
        datetime.setZonedDateTime(zonedDateTime);
        datetime.setInstant(instant);
        dateTimeAnnoMapper.insert(datetime);
        
    }

}
