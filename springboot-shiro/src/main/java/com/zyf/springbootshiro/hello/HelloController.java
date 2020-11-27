package com.zyf.springbootshiro.hello;

import com.zyf.springbootshiro.shiro.CustomRealm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/sh1")
    public String sh1() {
        return "hello/sh1";
    }

    @RequestMapping("/sh2")
    public String sh2() {
        return "hello/sh1";
    }

    @RequestMapping("/sh3")
    public String sh3() {
        return "hello/sh1";
    }

    @RequestMapping("/sh4")
    // 注解配置权限：登录用户需要hello:sh4权限才能访问
    @RequiresPermissions("hello:sh4")
    public String sh4() {
        return "hello/sh1";
    }

    @RequestMapping("/sh5")
    // 注解配置权限：登录用户需要hello:sh5权限才能访问
    @RequiresPermissions("hello:sh5")
    public String sh5() {
        return "hello/sh1";
    }

    @RequestMapping("/sh6")
    // 注解配置权限：登录用户需要role1角色才能访问
    @RequiresRoles("role1")
    public String sh6() {
        return "hello/sh1";
    }

    @RequestMapping("/sh7")
    // 注解配置权限：登录用户需要role2角色才能访问
    @RequiresRoles("role2")
    public String sh7() {
        return "hello/sh1";
    }

    @RequestMapping("/sh8")
    // 注解配置权限：登录用户需要role3角色才能访问
    @RequiresRoles("role3")
    public String sh8() {
        return "hello/sh1";
    }

    @RequestMapping("/anonymous")
    public String anonymous() {
        return "anonymous/sa1";
    }

    @Autowired
    private CustomRealm customRealm;

    @RequestMapping("/clearShiroCache")
    public String clearShiroCache(){
        customRealm.clearCached();
        return "hello/clearcache";
    }
}
