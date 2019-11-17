package com.zyf.springSecurity.security.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**自定义权限决策处理类*/
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 判断当前用户角色是否可以访问请求路径
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (authentication == null) {
			throw new AccessDeniedException("permission denied");
		}
		// 当前用户拥有的角色集合
		List<String> roleCodes = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		// 访问路径所需要的角色集合（数据已经由FilterInvocationSecurityMetadataSource获得）
		List<String> configRoleCodes = configAttributes.stream().map(ConfigAttribute::getAttribute)
				.collect(Collectors.toList());
		
		// 判断是否可以访问
		for (String roleCode : roleCodes) {
			if (configRoleCodes.contains(roleCode)) {
				return;
			}
		}
		throw new AccessDeniedException("permission denied");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
