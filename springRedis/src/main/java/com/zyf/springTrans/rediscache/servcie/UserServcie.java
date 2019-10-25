package com.zyf.springTrans.rediscache.servcie;

import java.util.List;

import com.zyf.springTrans.rediscache.pojo.User;

public interface UserServcie {

	User getUserById(int id);

	User insertUser(User ser);

	User updateUser(User ser);

	List<User> getUsers(User user);

	int deleteUserById(int id);
}
