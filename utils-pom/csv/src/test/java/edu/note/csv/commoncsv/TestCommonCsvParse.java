package edu.note.csv.commoncsv;

import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.Data;

/**
 * 测试解析 csv 文件
 *
 * @author jackylee
 * @date 2026-04-13 17:16
 */
public class TestCommonCsvParse {

    @Data
    public static class User {
        private String id;
        private String name;
        private int age;
    }

    @Test
    @DisplayName("测试 csv reader")
    void test_basic_01() throws IOException {
        // given:
        CSVFormat format = CSVFormat.DEFAULT.builder()
                // 如果不设置 header，默认是没有 header 的，第一行会被当做数据行解析，如果设置了 header，第一行会被当做 header 解析，默认是允许
                // header 中有列在 csv 中没有的，如果 csv 中有 header 中没有的列，会抛出异常，设置允许缺失列后，get 方法会返回 null
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .get();
        // when:
        String csv = "id,name,age\n1,Alice,18\n2,Bob,20\n";
        StringReader reader = new StringReader(csv);
        CSVParser csvParser = format.parse(reader);
        // then:
        csvParser.forEach(record -> {
            User user = new User();
            user.setId(record.get("id"));
            user.setName(record.get("name"));
            user.setAge(Integer.parseInt(record.get("age")));
            // System.out.println("数据行号(不含header)" record.get+ user);
        });
    }

    @Test
    @DisplayName("测试 csv reader")
    void test_basic_02() throws IOException {
        // given:
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .setHeader("id", "name", "age", "comment")
                .setIgnoreHeaderCase(false)
                // 默认是不允许，如果 csv 中有 header 中没有的列，会抛出异常，设置允许缺失列后，get 方法会返回 null
                .setAllowMissingColumnNames(true)
                // 忽略 header 大小写，默认是 false，设置为 true 后，get 方法会忽略大小写
                .setIgnoreHeaderCase(false)
                .setTrim(true)
                .get();
        // when:
        String csv = "id,NAME,AGE\n1,  Alice  ,18\n2,   Bob  ,20\n";
        StringReader reader = new StringReader(csv);
        CSVParser csvParser = format.parse(reader);
        // then:
        csvParser.forEach(record -> {
            User user = new User();
            user.setId(record.get("id"));
            user.setName(record.get("name"));
            user.setAge(Integer.parseInt(record.get("age")));
            System.out.println(user);
        });
    }

    @Test
    @DisplayName("测试 csv reader 无 header 枚举")
    void test_basic_03() throws IOException {
        // given:
        CSVFormat format = CSVFormat.DEFAULT.builder()
                // 需要配合 setHeader 使用，表示第一行是 header，不需要解析
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(false)
                .setTrim(true)
                .get();
        String csv = "1  ,  Alice  ,18\n2,   Bob  ,20\n";
        StringReader reader = new StringReader(csv);
        // when:
        CSVParser csvParser = format.parse(reader);
        // then:
        csvParser.forEach(record -> {
            User user = new User();
            user.setId(record.get(0));
            user.setName(record.get(1));
            user.setAge(Integer.parseInt(record.get(2)));
            System.out.println(user);
        });
    }

    @Test
    @DisplayName("测试 bom")
    void test_basic_0() throws IOException {
        // given:
        CSVFormat format = CSVFormat.DEFAULT.builder()
                // 需要配合 setHeader 使用，表示第一行是 header，不需要解析
                .setTrim(true)
                .get();
        String csv = "1  ,  Alice  ,18\n2,   Bob  ,20\n";
        StringReader reader = new StringReader(csv);
        // when:
        CSVParser csvParser = format.parse(reader);
        // then:
        csvParser.forEach(record -> {
            User user = new User();
            user.setId(record.get(0));
            user.setName(record.get(1));
            user.setAge(Integer.parseInt(record.get(2)));
            System.out.println(user);
        });
    }
}
