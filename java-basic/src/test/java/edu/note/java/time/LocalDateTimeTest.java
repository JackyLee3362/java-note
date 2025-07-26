package edu.note.java.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

/**
 * @author jackylee
 * @date 2025/7/9 15:41
 */
public class LocalDateTimeTest {

    @Test
    // NOTE 本地时间
    public void test_create_local_date_time() {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(10);
        Date startDate = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(startDate);
        System.out.println(endDate);
        // System.out.println(startTime.toLocalDate());
        // System.out.println(startTime.toLocalTime());
        // System.out.println(endTime.toLocalDate());
        // System.out.println(endTime.toLocalTime());
    }

    @Test
    public void test_get_current_timestamp(){
        System.out.println(System.currentTimeMillis());
    }

}
