package com.zyf.springbootredisson.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@RestController
@Api(tags = "reidsson客户端")
public class RedissonClientTestController {


    @Autowired
    @Qualifier("redissonClinet")
    private RedissonClient redissonClient;

    @ApiOperation(value = "测试1", tags = "reidsson客户端")
    @GetMapping("test1")
    private Map<Object, Object> test1(@ApiParam(value = "参数1") String param1) {
        RMap<Object, Object> map = redissonClient.getMap(param1);
        return map;
    }

    @ApiOperation("测试2")
    @GetMapping("test2")
    private Set<Object> test2(@ApiParam(value = "集合key") String key,
                              @ApiParam(value = "获取元素个数") int count) {
        // 通过key获取set集合
        RSet<Object> ids = redissonClient.getSet(key);
        System.out.println("ids=" + ids);

        // 随机获取n个元素
        Set<Object> random = ids.random(count);
        System.out.println("random=" + random);

        // 随机获取n个元素，并从redis移除
        Set<Object> removeRandom = ids.removeRandom(count);
        System.out.println("removeRandom=" + removeRandom);

        return ids;
    }

    @ApiOperation("测试3")
    @GetMapping("test3")
    private Set<Object> test3(@ApiParam(value = "参数1") String param1) {
        // 创建（如果没有）集合
        RSet<Object> ids = redissonClient.getSet("ids2");
        // 添加元素
        ids.add("21");
        ids.add("22");
        ids.add("23");
        ids.add("024");
        ids.addAll(Arrays.asList("a01", "a02", "a03"));
        RSet<Object> ids2 = redissonClient.getSet("ids2");
        return ids2;
    }

    @ApiOperation("测试4")
    @GetMapping("test4")
    private Set<Object> test4(@ApiParam(value = "集合key") String key,
                              @ApiParam(value = "获取元素个数") int count) {
        // 通过key获取set集合
        RSet<Object> ids = redissonClient.getSet(key);
        System.out.println("ids=" + ids);

        // 随机获取n个元素
        Set<Object> random = ids.random(count);
        System.out.println("random=" + random);

        // 随机获取n个元素，并从redis移除
        Set<Object> removeRandom = ids.removeRandom(count);
        System.out.println("removeRandom=" + removeRandom);
        return ids;
    }

    @ApiOperation("添加元素")
    @GetMapping("add")
    private Set<Object> add(String key, int count) {
        // 通过key获取set集合
        RSet<Object> ids = redissonClient.getSet(key);
        System.out.println("ids=" + ids);
        int tempc = 1;
        while (tempc <= count) {
            ids.add("a" + tempc);
            tempc++;
        }
        return redissonClient.getSet(key);
    }
}
