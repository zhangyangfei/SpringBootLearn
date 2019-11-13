package com.zyf.springSecurity.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zyf.springSecurity.security.config.EncryptUtil;

/** 用户详情实现类 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * 构建用户详情
	 * @param username 登录用户名
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 权限集合
		List<GrantedAuthority> authList = new ArrayList<>();
		// 数据库密码:根据用户名查询，此处省略该步骤
		String dbPassword = "1";
		// 明文密码需要加密
		UserDetails userDetails = new User(username, EncryptUtil.encrypt(dbPassword), authList);
		return userDetails;
	}

}
