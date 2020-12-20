package com.zyf.springbootvue.vue;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vue")
public class VueController {

    @RequestMapping("/test1")
    public Map test1(ModelAndView mv) {
        return getMap(mv);
    }

    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    public Map test2(ModelAndView mv) {
        return getMap(mv);
    }

    private Map getMap(ModelAndView mv) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 18);
        mv.addAllObjects(map);
        return map;
    }

    @RequestMapping(value = "/test3",method = RequestMethod.POST)
    public Map test3(ModelAndView mv, @RequestBody Map<String, Object> paramMap) {
        System.out.println("请求参数：" + paramMap);
        if(true){
            throw new RuntimeException("报错了...");
        }
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
