package com.example.demo;

import com.example.demo.bean.Students;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@SpringBootTest
class BackendApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
		log.info("this is my test");
//		Long aLong = jdbcTemplate.queryForObject("select count(*) from info", Long.class);
//		log.info("记录数是{}", aLong);
//
//		List<Students> students = jdbcTemplate.query(
//				"SELECT id,name from info", new RowMapper<Students>() {
//					@Override
//					public Students mapRow(ResultSet rs, int rowNum) throws SQLException {
//						Students student = new Students();
//						student.setId(rs.getInt("id"));
//						student.setName(rs.getString("name"));
//						return student;
//					}
//				}
//		);
//
//		log.info("success");
//		for(Students s : students){
//			log.info(s.toString());
//		}
	}

}
