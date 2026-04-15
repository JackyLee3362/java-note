package edu.note.csv.commoncsv;

import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author jackylee
 * @date 2026-04-13 20:00
 */
public class TestCommonCsvWithEnumHeader {

    public static enum Header {
        ID, NAME, AGE
    }

    @Data
    public static class User {
        private String id;
        private String name;
        private int age;
    }

    @Test
    @DisplayName("测试 csv reader 枚举 header")
    void test_basic_01() throws IOException {
        // given:
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .setHeader(Header.class)
                .setIgnoreHeaderCase(false)
                .setTrim(true)
                .get();
        String csv = "id,NAME,AGE\n1,  Alice  ,18\n2,   Bob  ,20\n";
        StringReader reader = new StringReader(csv);
        // when:
        CSVParser csvParser = format.parse(reader);
        // then:
        csvParser.forEach(record -> {
            User user = new User();
            user.setId(record.get(Header.ID));
            user.setName(record.get(Header.NAME));
            user.setAge(Integer.parseInt(record.get(Header.AGE)));
            System.out.println(user);
        });
    }

    @Getter
    @AllArgsConstructor
    public static enum Header2 {
        ID("id", "用户 id"),
        NAME("name", "用户姓名"),
        AGE("age", "用户年龄"),
        COMMENT("comment", "备注");

        final private String name;
        final private String description;

    }

    @Test
    @DisplayName("测试 csv reader 枚举 header")
    void test_basic_02() throws IOException {
        // given:
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .setHeader(Header2.class)
                .setIgnoreHeaderCase(false)
                .setTrim(true)
                .get();
        String csv = "id,NAME,AGE,COMMENT\n1,  Alice  ,18,  Good  \n2,   Bob  ,20,  Bad  \n";
        StringReader reader = new StringReader(csv);
        // when:
        CSVParser csvParser = format.parse(reader);
        // then:
        csvParser.forEach(record -> {
            User user = new User();
            user.setId(record.get(Header2.ID));
            user.setName(record.get(Header2.NAME));
            user.setAge(Integer.parseInt(record.get(Header2.AGE)));
            if (record.get(Header2.COMMENT) != null) {
                System.out.println("comment: " + record.get(Header2.COMMENT));
            }
            System.out.println(user);
        });
    }

    @Test
    @DisplayName("测试 csv reader 枚举 header")
    void test_basic_03() throws IOException {
        // given:
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .get();
        String csv = "id,name,age,comment\n1,  Alice  ,18,  Good  \n2,   Bob  ,20,  Bad  \n";
        StringReader reader = new StringReader(csv);
        // when:
        CSVParser csvParser = format.parse(reader);
        // then:
        csvParser.forEach(record -> {
            User user = new User();
            user.setId(record.get(Header2.ID.name));
            user.setName(record.get(Header2.NAME.name));
            user.setAge(Integer.parseInt(record.get(Header2.AGE.name)));
            if (record.get(Header2.COMMENT.name) != null) {
                System.out.println("comment: " + record.get(Header2.COMMENT.name));
            }
            System.out.println(user);
        });
    }
}
