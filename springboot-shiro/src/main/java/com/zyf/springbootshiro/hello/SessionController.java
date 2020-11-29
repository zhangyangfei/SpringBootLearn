package com.zyf.springbootshiro.hello;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * shiro操作会话
 */
@Controller
@RequestMapping("/session")
public class SessionController {

    @RequestMapping("/getAttributeKeys")
    @ResponseBody
    public String getAttributeKeys(){
        Session session = this.getSession();
        Collection<Object> attributeKeys = session.getAttributeKeys();
        for(Object attributeKey : attributeKeys){
            System.out.println(attributeKey);
        }
        return "已打印到控制台";
    }

    @RequestMapping("/setAttribute")
    @ResponseBody
    public String setAttribute(){
        Session session = this.getSession();;
        session.setAttribute("mobile","18611112222" );
        return "成功";
    }

    @RequestMapping("/getAttribute")
    @ResponseBody
    public String getAttribute(){
        Session session = this.getSession();
        if (session.getAttribute("mobile") == null){
            return "mobile=空";
        }
        String mobile = session.getAttribute("mobile").toString();
        return "mobile=" + mobile;
    }

    /**
     * 获取session
     */
    /*
    返回值	方法名	描述
    Object	getAttribute(Object key)	根据key标识返回绑定到session的对象
    Collection	getAttributeKeys()	获取在session中存储的所有的key
    String	getHost()	获取当前主机ip地址，如果未知，返回null
    Serializable	getId()	获取session的唯一id
    Date	getLastAccessTime()	获取最后的访问时间
    Date	getStartTimestamp()	获取session的启动时间
    long	getTimeout()	获取session失效时间，单位毫秒
    void	setTimeout(long maxIdleTimeInMillis)	设置session的失效时间
    Object	removeAttribute(Object key)	通过key移除session中绑定的对象
    void	setAttribute(Object key, Object value)	设置session会话属性
    void	stop()	销毁会话
    void	touch()	更新会话最后访问时间
     */
    private Session getSession(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        System.out.println("session.getHost()=" + session.getHost());
        System.out.println("session.getId()=" + session.getId());
        System.out.println("session.getLastAccessTime()=" + session.getLastAccessTime());
        return session;
    }
}
