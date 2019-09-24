package com.zyf.springDb.jdbctemplate.service;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.pool2.UsageTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zyf.springDb.jdbctemplate.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserRowMapperService userRowMapperService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获得用户列表，返回map，map的key就是表字段名
	 * @return
	 */
	public List<Map<String, Object>> getUserList(){
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_user");
		System.out.println(list);
		return list;
	}
	
	/**
	 * 根据主键id查询用户
	 * @param id
	 * @return
	 */
	public User getUser(int id) {
		String sql = "select id,name,sex,note from t_user where id = ?";
		return jdbcTemplate.queryForObject(sql, userRowMapperService.getRowMapper(),id);
	}

	/**
	 * 存入用户(更新操作与本方基本相同，只是sql中改为update语句)
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {
		String sql = "insert into t_user(name,sex,note) values (?,?,?)";
		// 参数多时可以通过数据传递，有序
		Object[] param = new Object[] { user.getName(), user.getSex(), user.getNote()};
		return jdbcTemplate.update(sql, param);
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUser(int id){
		String sql = "delete from t_user where id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	/**
	 * 模糊查询，获得用户列表
	 * @param user
	 * @return
	 */
	public List<User> getUsers(User user){
		String sql = "select id,name,sex,note from t_user "
				+ "where name like concat('%',?,'%')";
		return jdbcTemplate.query(sql, userRowMapperService.getRowMapper(),user.getName());
	}
	
	/**
	 * 获得用户列表，返回map，map的key就是表字段名
	 * @return
	 */
	public List<User> getUserList2(User user){
		List<User> result = jdbcTemplate.execute((Statement st)->{
			String sql = "select * from t_user";
			ResultSet resultSet = st.executeQuery(sql);
			List<User> userList = new ArrayList<User>();
			while(resultSet.next()){
				userList.add(userRowMapperService.getRowMapper().mapRow(resultSet, resultSet.getRow()));
			}
			return userList;
		});
		return result;
	}
}
