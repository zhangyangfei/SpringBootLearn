package com.zyf.springTrans.rediscache.servcie;

import java.util.List;

import com.zyf.springTrans.rediscache.pojo.User;

public interface UserServcie {

	User getUserById(int id);

	User insertUser(User user);

	User updateUser(User user);

	List<User> getUsers(User user);

	int deleteUserById(int id);
}
