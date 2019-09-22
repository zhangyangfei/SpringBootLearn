package com.zyf.springDb;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDbApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void contextLoads() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_user");
		System.out.println(list);	
	}
	
}
