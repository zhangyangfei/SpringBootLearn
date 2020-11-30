package com.zyf.springbootshiro.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据用户名从数据库获取密码
        String password = "123";
        if (userName == null) {
            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(password)) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(userName, password, getName());
    }

    /**
     * 权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------权限认证方法--------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permsSet = new HashSet<>();
        // 根据username查出用户的权限，模拟查询
        permsSet.add("hello:sh1");
        permsSet.add("hello:sh2");
        permsSet.add("hello:sh4");
        info.setStringPermissions(permsSet);

        info.addRole("role1");
        return info;
    }

    /**
     * 清除shiro缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}
