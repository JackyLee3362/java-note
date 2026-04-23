package edu.note.csv.opencsv;

import java.io.StringReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.Data;

/**
 * @author jackylee
 * @date 2026-04-13 19:29
 */
public class TestCsvToBean {

    @Data
    public static class User {

        @CsvBindByName(column = "id")
        // @CsvBindByPosition(position = 0)
        private String id;

        @CsvBindByName(column = "name")
        // @CsvBindByPosition(position = 1)
        private String name;

        @CsvBindByName(column = "age")
        // @CsvBindByPosition(position = 2)
        private int age;
    }

    @Test
    @DisplayName("测试csv")
    void test_basic_01() {
        // given:
        String csv = "id,name,age\n1,  Alice  ,18\n2,   Bob  ,20";
        HeaderColumnNameMappingStrategy<User> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(User.class);
        StringReader reader = new StringReader(csv);
        CSVReader csvReader = new CSVReader(reader);

        // when:
        CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(csvReader)
                .withType(User.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        csvToBean.forEach(System.out::println);
        // then:
    }

}
