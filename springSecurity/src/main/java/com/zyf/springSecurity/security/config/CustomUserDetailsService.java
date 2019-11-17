package com.zyf.springSecurity.security.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zyf.springSecurity.security.utils.EncryptUtil;


/** 用户详情实现类 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private  HttpServletRequest request;
	
	/**
	 * 构建用户详情（用户名、密码、角色）
	 * @param username 登录用户名
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("密码不能为空！");
		}
		if (!"1".equals(password)) {
			throw new BadCredentialsException("密码不正确，请重新输入");
		}
		if (!"z".equals(username)) {
			throw new BadCredentialsException("账号不存在，请重新输入！");
		}

		// 角色集合
		List<GrantedAuthority> authList = new ArrayList<>();
		List<String> roles = getRolesByUsername(username);
		GrantedAuthority grantedAuthority;
		for (String role : roles) {
			grantedAuthority = new SimpleGrantedAuthority(role);
			authList.add(grantedAuthority);
		}

		// 数据库密码:根据用户名查询，此处省略该步骤
		String dbPassword = "1";
		// 明文密码需要加密
		UserDetails userDetails = new User(username, EncryptUtil.encrypt(dbPassword), authList);
		return userDetails;
	}

	//springsecurity角色前缀
	private static String ROLE_PREFIX = "ROLE_";
	
	// 获取用户角色集合
	private List<String> getRolesByUsername(String username){
		List<String> roles = new ArrayList<String>();
		roles.add(ROLE_PREFIX+"TEST");
//		roles.add(ROLE_PREFIX+"USER");
		roles.add(ROLE_PREFIX+"ADMIN");
		return roles;
	}
}
