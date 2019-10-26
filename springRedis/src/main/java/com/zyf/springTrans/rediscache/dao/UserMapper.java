package com.zyf.springTrans.rediscache.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.zyf.springTrans.rediscache.pojo.User;

@Repository
public interface UserMapper {
	User getUserById(int id);

	int insertUser(User user);

	int updateUser(User user);

//	List<User> getUsers(User user);
	
	int deleteUserById(int id);
}
